/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.rest;

import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;
import org.kossowski.elemont.domain.KartaMagazynowa;
import org.kossowski.elemont.domain.Odcinek;
import org.kossowski.elemont.domain.Operacja;
import org.kossowski.elemont.domain.User;
import org.kossowski.elemont.domain.operacje.SkanScinka;
import org.kossowski.elemont.repositories.KartaMagazynowaRepository;
import org.kossowski.elemont.repositories.OdcinekRepository;
import org.kossowski.elemont.repositories.OperacjaRepository;
import org.kossowski.elemont.repositories.UserRepository;
import org.kossowski.elemont.utils.JSFUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jkossow
 */
@RestController
@RequestMapping("/")
@Scope("request")
public class SkanScinkaRestBean {

    @Autowired
    protected OdcinekRepository odcRepo;
    
    @Autowired 
    protected UserRepository userRepo;
    
    @Autowired
    protected OperacjaRepository opRepo;
    
    @Autowired
    protected KartaMagazynowaRepository kmRepo;
    
    @ResponseBody
    @RequestMapping( value="/getScinka/{qrkod}/{user}", method = RequestMethod.GET, produces = "application/json" )
    public byte[] getScinka( @PathVariable(value="qrkod") String qrkod ) throws Exception {
        //System.out.println("przed koretą " + qrkod);
        //qrkod = qrkod.substring(qrkod.length() -3 );
        //System.out.println( "po korekcie " + qrkod);
        String id1 = qrkod.substring(0, qrkod.length() - 2 );
        String selector = qrkod.substring( qrkod.length() - 2);

        System.out.println( id1 );
        System.out.println( selector );

        Odcinek o = odcRepo.findOne( new Long(id1));
        if( o == null )
            return "0".getBytes("utf-8");
        
        BigDecimal dlScinka;
        if( selector.equals("A1") )
            dlScinka = o.spodzScinekA1();
        else
            dlScinka = o.spodzScinekB1();
        System.out.println( dlScinka );
        return dlScinka.toString().getBytes("utf-8");
    }
    
    
    @ResponseBody
    @RequestMapping( value = "/sendScan/{odcId}/{dlScinka}/{date}/6/{login}", method = RequestMethod.PUT, produces = "application/json")
    public byte[] perform( 
            @PathVariable(value="odcId")  String strOdcinek,
            @PathVariable(value="dlScinka") String strDlScinka,
            @PathVariable(value="date") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date,
            @PathVariable(value="login") String login ) throws Exception {
        
        System.out.println("odcId " + strOdcinek);
        System.out.println("dlScinka " +  strDlScinka);
        System.out.println("date " + date);
        System.out.println("login " + login);
        
        BigDecimal dlScinka;
        String s = strOdcinek.substring(0, strOdcinek.length() -2 );
        
        Long idOdc;
        
        try {
            idOdc = new Long(s);
        } catch (Exception e ) {
            return "Id odcinka - błąd konwersji".getBytes("utf-8");
        }
        
        
        try {
             dlScinka = new BigDecimal(strDlScinka);
        } catch( Exception e ) {
            return "Długość scinka - błąd konwersji".getBytes("utf-8");
        }
        
        User utworzyl = userRepo.findOne(login);
        Odcinek odc  = odcRepo.findOne(idOdc);
        
         if( utworzyl == null ) {
            return "Brak użytkownika".getBytes("utf-8");
        }
         
        if( odc == null ) {
            return "Nie ma takiego odcinka".getBytes("utf-8");
        }
        
        
        KartaMagazynowa km = odc.getKartaMagazynowa();
        
        SkanScinka skanScinka = new SkanScinka( utworzyl, 
                GregorianCalendar.getInstance().getTime() , strOdcinek, dlScinka );
        skanScinka = opRepo.save( skanScinka );
        km.addOperation( skanScinka );
        
        try {
            skanScinka.accept();
        } catch (Exception e) { 
            km.removeOperation(skanScinka);
            opRepo.delete(skanScinka);
            return e.getMessage().getBytes("utf-8");
        };
        
        kmRepo.save( km );
        return "ok".getBytes("utf-8");
    }
}
