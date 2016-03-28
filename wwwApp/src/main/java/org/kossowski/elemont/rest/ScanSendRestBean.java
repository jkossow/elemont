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
    @RequestMapping( value="/{data1}/{data2}/{date}/NA/{login}", method = RequestMethod.PUT, produces = "application/json" )
    public String skanZawieszki(
        @PathVariable(value="data1") String data1,
        @PathVariable(value="data2") String data2,
        @PathVariable(value="date")  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date,
        @PathVariable(value="tryb") int tryb,
        @PathVariable(value="login") String login )
    {
            
            System.out.println("date1 " + data1);
            System.out.println("date2 " + data2);
            System.out.println("date2 " + date);
            System.out.println("tryb " +  tryb);
            System.out.println("login " + login);
   
        String s = "not";  //wartosc odpowiedzi    
        switch (tryb) {
            case 1:  
                s = wydanieNaBudowe(data1,data2,date, login); // obsługa login
                break;
            case 2: 
                s = skanZawieszki(data1,data2, date, login ); 
                break;
            case 3:
                s = skanZawieszki(data1,data2,date, login );
                break;
            case 6:
                s = skanScinka( data1, data2, date, login );
                break;
            case 5: 
                s = zwrotNaMagazyn( data1, date, login );
                break;
                
        }
        return s;
    }
    
    
    @ResponseBody
    @RequestMapping( value="/{data1}/{data2}/{date}/NA/{login}/{6}/{7}", method = RequestMethod.PUT, produces = "application/json" )
    public String skanTryb5 (
        @PathVariable(value="data1") String data1,
        @PathVariable(value="data2") String data2,
        @PathVariable(value="date")  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date,
        @PathVariable(value="tryb") int tryb,
        @PathVariable(value="login") String login,
        @PathVariable(value="6") String six,
        @PathVariable(value="7") String seven )
    {
            
            System.out.println("date1 " + data1);
            System.out.println("date2 " + data2);
            System.out.println("date2 " + date);
            System.out.println("tryb " +  tryb);
            System.out.println("login " + login);
            System.out.println("six " +  six);
            System.out.println("seven " + seven);
    
            String s = "not";
            
            switch (tryb) {
            
            case 6:
                s = skanScinka( data1, data2, date ,login);
                break;
            
                
        }
        return s;
            
    };
    
    
    
    
    private String wydanieNaBudowe( String idKm, String userQR, Date date, String login ) {
        
        KartaMagazynowa km = kmRepo.findOne( new Long(idKm));
        User user = userRepo.findFirstByKodQR( userQR );
        User operator = userRepo.findOne( login );
        
        if( km == null ) {
            System.out.println("zły id partii materiałowej");
            return "zły id partii materiałowej";
        };
        
        if( user == null ) {
            System.out.println("Brak uzytkownika o takim QR");
            return "Brak uzytkownika o takim QR";
        }
        
        if( operator == null ) {
            System.out.println("Brak uzytkownika o loginie");
            return "Brak uzytkownika o loginie";
        }
        
        Operacja o = new WydanieNaBudowe( km.getStanIl().getIValue( Stan.IL_W_MAG_GL), user) ;
        o.setCzasUtworzenia(date);
        o = opRepo.save(o);
        km.addOperation(o);
        o.setKartaMagazynowa(km);
        
        try {
            o.accept();
            
        } catch ( Exception e) {
            km.removeOperation(o);
            opRepo.delete(o);
            System.out.println("nie można zatwierdzić " + e.getMessage());
            return e.getMessage();           
        };
        
        kmRepo.save(km);        
        return "ok";
    }
    
    //data1 - odcinek
    //data2 - znacznik
    private String skanZawieszki( String data1, String data2, Date date, String login ) {
        
        String s = data1.substring(0, data1.length() -2 );
        BigDecimal znacznik = new BigDecimal( data2 );
        User operator = userRepo.findOne( login );
        
        Long idOdc;
        
        if( operator == null ) {
            System.out.println("Brak uzytkownika o loginie");
            return "Brak uzytkownika o loginie";
        }
        
        try {
            idOdc = new Long(s);
        } catch (Exception e) {
            System.out.println("Błąd w QR kodzie");
            return "Błąd w QR kodzie";
        }
        
        Odcinek o = odcRepo.findOne(idOdc);
        
        if( o == null ) {
            System.out.println("nie ma takiego odcinka");
            return "nie ma takiego odcinka";
        }
        
        KartaMagazynowa km = o.getKartaMagazynowa();
        
        SkanZawieszki skanZaw = new SkanZawieszki(data1, znacznik, operator);
        skanZaw.setCzasUtworzenia(date);
        skanZaw = opRepo.save( skanZaw );
        km.addOperation(skanZaw);
        
        try {
            skanZaw.accept();
        } catch (Exception e) { 
            km.removeOperation(skanZaw);
            opRepo.delete(skanZaw);
            System.out.println("bład akceptacji " + e.getMessage());
            return e.getMessage();
        };
        System.out.println("Skan zarejestrowany");
        kmRepo.save( km );
        return "ok";
        
    }
    
    private String skanScinka( String data1, String data2,  Date date, String login ) {
        
        
        String s = data1.substring(0, data1.length() -2 );
        
        Long idOdc;
        
        User operator = userRepo.findOne( login );
        
        if( operator == null ) {
            System.out.println("Brak uzytkownika o loginie");
            return "Brak uzytkownika o loginie";
        }
        
        try {
            idOdc = new Long(s);
        } catch (Exception e) {
            System.out.println("Błąd w QR kodzie");
            return "Błąd w QR kodzie";
        }
        
        Odcinek o = odcRepo.findOne(idOdc);
        
        if( o == null ) {
            System.out.println("nie ma takiego odcinka");
            return "nie ma takiego odcinka";
        }
        
        KartaMagazynowa km = o.getKartaMagazynowa();
        
        SkanScinka skanScinka = new SkanScinka(data1, new BigDecimal(data2), operator);
        skanScinka.setCzasUtworzenia(date);
        skanScinka = opRepo.save( skanScinka );
        km.addOperation(skanScinka);
        
        try {
            skanScinka.accept();
        } catch (Exception e) { 
            km.removeOperation(skanScinka);
            opRepo.delete(skanScinka);
            System.out.println("bład akceptacji " + e.getMessage());
            return e.getMessage();
        };
        
        System.out.println("Skan zarejestrowany");
        kmRepo.save( km );
        return "ok";
    }
    
    private String zwrotNaMagazyn( String idKm,  Date date, String login) {
        
        KartaMagazynowa km = kmRepo.findOne( new Long(idKm));
        
        User operator = userRepo.findOne( login );
        
        if( operator == null ) {
            System.out.println("Brak uzytkownika o loginie");
            return "Brak uzytkownika o loginie";
        }
        
        if( km == null ) {
            System.out.println("zły id partii materiałowej");
            return "zły id partii materiałowej";
        };
        
        Operacja o = new Zwrot( km.getStanIl().getIValue( Stan.IL_STAN_BIEZ), operator) ;
        o.setCzasUtworzenia(date);
        o = opRepo.save(o);
        km.addOperation(o);
        o.setKartaMagazynowa(km);
        
        try {
            o.accept();
            
        } catch ( Exception e) {
            km.removeOperation(o);
            opRepo.delete(o);
            System.out.println("nie można zatwierdzić " + e.getMessage());
            return e.getMessage();           
        };
        
        kmRepo.save(km);        
        return "ok";
    }
}