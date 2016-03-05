/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.rest;

import java.math.BigDecimal;
import java.util.Date;
import org.kossowski.elemont.domain.KartaMagazynowa;
import org.kossowski.elemont.domain.Odcinek;
import org.kossowski.elemont.domain.Operacja;
import org.kossowski.elemont.domain.Stan;
import org.kossowski.elemont.domain.User;
import org.kossowski.elemont.domain.operacje.SkanScinka;
import org.kossowski.elemont.domain.operacje.SkanZawieszki;
import org.kossowski.elemont.domain.operacje.WydanieNaBudowe;
import org.kossowski.elemont.domain.operacje.Zwrot;
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
@RequestMapping("sendScan")
@Scope("request")
public class ScanSendRestBean {

    @Autowired
    protected KartaMagazynowaRepository kmRepo;
    
    @Autowired
    protected UserRepository userRepo;
    
    @Autowired
    protected OperacjaRepository opRepo;
    
    @Autowired
    protected OdcinekRepository odcRepo;
    
    public ScanSendRestBean() {
        System.out.println("konstruktor ScanSendRestBean");
    }
    
    @ResponseBody
    @RequestMapping( value="/{data1}/{data2}/{date}/{tryb}", method = RequestMethod.PUT, produces = "application/json" )
    public String skanZawieszki(
        @PathVariable(value="data1") String data1,
        @PathVariable(value="data2") String data2,
        @PathVariable(value="date")  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date,
        @PathVariable(value="tryb") int tryb ) {
            
            System.out.println("date1 " + data1);
            System.out.println("date2 " + data2);
            System.out.println("date2 " + date);
            System.out.println("tryb " +  tryb);
   
        String s = "not";  //wartosc odpowiedzi    
        switch (tryb) {
            case 1:  
                s = wydanieNaBudowe(data1,data2,date);
                break;
            case 2: 
                s = skanZawieszki(data1,data2,date );
                break;
            case 3:
                s = skanZawieszki(data1,data2,date );
                break;
            case 4:
                s = skanScinka( data1, date );
                break;
            case 5: 
                s = zwrotNaMagazyn( data1, date );
                break;
                
        }
        return s;
    }
    
    
    private String wydanieNaBudowe( String idKm, String userQR, Date date) {
        
        KartaMagazynowa km = kmRepo.findOne( new Long(idKm));
        User user = userRepo.findFirstByKodQR( userQR );
        
        if( km == null ) {
            System.out.println("zły id partii materiałowej");
            return "not";
        };
        
        if( user == null ) {
            System.out.println("Brak uzytkownika o takim QR");
            return "not";
        }
        
        Operacja o = new WydanieNaBudowe( user,  km.getStanIl().getIValue( Stan.IL_W_MAG_GL)) ;
        o.setCreationTime(date);
        o = opRepo.save(o);
        km.addOperation(o);
        o.setKartaMagazynowa(km);
        
        try {
            o.accept();
            
        } catch ( Exception e) {
            km.removeOperation(o);
            opRepo.delete(o);
            System.out.println("nie można zatwierdzić " + e.getMessage());
            return "not";           
        };
        
        kmRepo.save(km);        
        return "ok";
    }
    
    //data1 - odcinek
    //data2 - znacznik
    private String skanZawieszki( String data1, String data2, Date date ) {
        
        String s = data1.substring(0, data1.length() -2 );
        BigDecimal znacznik = new BigDecimal( data2 );
        
        Long idOdc;
        
        try {
            idOdc = new Long(s);
        } catch (Exception e) {
            System.out.println("Błąd w QR kodzie");
            return "not";
        }
        
        Odcinek o = odcRepo.findOne(idOdc);
        
        if( o == null ) {
            System.out.println("nie ma takiego odcinka");
            return "not";
        }
        
        KartaMagazynowa km = o.getKartaMagazynowa();
        
        SkanZawieszki skanZaw = new SkanZawieszki(data1, znacznik);
        skanZaw.setCreationTime(date);
        skanZaw = opRepo.save( skanZaw );
        km.addOperation(skanZaw);
        
        try {
            skanZaw.accept();
        } catch (Exception e) { 
            km.removeOperation(skanZaw);
            opRepo.delete(skanZaw);
            System.out.println("bład akceptacji " + e.getMessage());
            return "not";
        };
        System.out.println("Skan zarejestrowany");
        kmRepo.save( km );
        return "ok";
        
    }
    
    private String skanScinka( String data1, Date date ) {
        
        String s = data1.substring(0, data1.length() -2 );
        
        Long idOdc;
        
        try {
            idOdc = new Long(s);
        } catch (Exception e) {
            System.out.println("Błąd w QR kodzie");
            return "not";
        }
        
        Odcinek o = odcRepo.findOne(idOdc);
        
        if( o == null ) {
            System.out.println("nie ma takiego odcinka");
            return "not";
        }
        
        KartaMagazynowa km = o.getKartaMagazynowa();
        
        SkanScinka skanScinka = new SkanScinka(data1, null);
        skanScinka.setCreationTime(date);
        skanScinka = opRepo.save( skanScinka );
        km.addOperation(skanScinka);
        
        System.out.println("Skan zarejestrowany");
        kmRepo.save( km );
        return "ok";
    }
    
    private String zwrotNaMagazyn( String idKm,  Date date) {
        
        KartaMagazynowa km = kmRepo.findOne( new Long(idKm));
        
        if( km == null ) {
            System.out.println("zły id partii materiałowej");
            return "not";
        };
        
        Operacja o = new Zwrot( km.getStanIl().getIValue( Stan.IL_STAN_BIEZ)) ;
        o.setCreationTime(date);
        o = opRepo.save(o);
        km.addOperation(o);
        o.setKartaMagazynowa(km);
        
        try {
            o.accept();
            
        } catch ( Exception e) {
            km.removeOperation(o);
            opRepo.delete(o);
            System.out.println("nie można zatwierdzić " + e.getMessage());
            return "not";           
        };
        
        kmRepo.save(km);        
        return "ok";
    }
}