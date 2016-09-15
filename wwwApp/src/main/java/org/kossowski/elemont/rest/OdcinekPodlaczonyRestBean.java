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
import org.kossowski.elemont.domain.Status;
import org.kossowski.elemont.domain.User;
import org.kossowski.elemont.domain.operacje.SkanZawieszki;
import org.kossowski.elemont.repositories.KartaMagazynowaRepository;
import org.kossowski.elemont.repositories.OdcinekRepository;
import org.kossowski.elemont.repositories.OperacjaRepository;
import org.kossowski.elemont.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.BindingResult;
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
@RequestMapping("sendScan")
@Scope("request")
public class OdcinekPodlaczonyRestBean {
    
   @Autowired 
    protected UserRepository userRepo;
    
    @Autowired
    protected KartaMagazynowaRepository kmRepo;
    
    @Autowired
    protected OperacjaRepository opRepo;
    
    @Autowired
    protected OdcinekRepository odcRepo;
    
    @ResponseBody
    @RequestMapping( value = "/{odcId}/{znacznik}/{date}/3/{login}", method = RequestMethod.PUT, produces = "application/json")
    public byte[] perform( 
            @PathVariable(value="odcId")  String strOdcinek,
            @PathVariable(value="znacznik") String strZnacznikPoczatkowy,
            @PathVariable(value="date") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date,
            @PathVariable(value="login") String login ) throws Exception {
        
        System.out.println("odcId " + strOdcinek);
        System.out.println("znacznikPoczatkowy " + strZnacznikPoczatkowy);
        System.out.println("date " + date);
        System.out.println("login " + login);
        
        BigDecimal znacznikPoczatkowy;
        String s = strOdcinek.substring(0, strOdcinek.length() -2 );
        
        String suffix = strOdcinek.substring( strOdcinek.length() - 2, strOdcinek.length() );
        if( suffix.equalsIgnoreCase("A1") || suffix.equalsIgnoreCase("B1") )
            return "Error. Za późno".getBytes("utf-8");
        
        Long idOdc;
        
        try {
            idOdc = new Long(s);
        } catch (Exception e ) {
            return "Id odcinka - błąd konwersji".getBytes("utf-8");
        }
        
        
        try {
            znacznikPoczatkowy = new BigDecimal(strZnacznikPoczatkowy);
        } catch( Exception e ) {
            return "Znacznik - błąd konwersji".getBytes("utf-8");
        }
        
        User utworzyl = userRepo.findOne(login);
        Odcinek odc  = odcRepo.findOne(idOdc);
        
         if( utworzyl == null ) {
            return "Brak utykownika".getBytes("utf-8");
        }
         
        if( odc == null ) {
            return "Nie ma takiego odcinka".getBytes("utf-8");
        }
        
        KartaMagazynowa km = odc.getKartaMagazynowa();
        
        SkanZawieszki skanZaw = new SkanZawieszki( utworzyl,
                GregorianCalendar.getInstance().getTime(), 
                strOdcinek, 
                znacznikPoczatkowy );
        
        skanZaw = opRepo.save( skanZaw );
        km.addOperation(skanZaw);
        
        try {
            skanZaw.accept();
        } catch (Exception e) { 
            km.removeOperation(skanZaw);
            opRepo.delete(skanZaw);
            return e.getMessage().getBytes("utf-8");
        };
        
        kmRepo.save( km );
        if( odc.getStatus() == Status.S6 ) 
            return ("ok|" + odc.getA2().subtract(odc.getB2()).abs()).getBytes("utf-8");
        else
            return "ok".getBytes("utf-8");
        
    }
    
}
