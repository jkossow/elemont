/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;
import org.kossowski.elemont.domain.KartaMagazynowa;
import org.kossowski.elemont.domain.Odcinek;
import org.kossowski.elemont.domain.SelektorZawieszki;
import org.kossowski.elemont.domain.User;
import org.kossowski.elemont.domain.operacje.NowyOdcinek;
import org.kossowski.elemont.qr.Etykieta;
import org.kossowski.elemont.qr.EtykietaQR3;
import org.kossowski.elemont.qr.EtykietaQR4;
import org.kossowski.elemont.repositories.KartaMagazynowaRepository;
import org.kossowski.elemont.repositories.OdcinekRepository;
import org.kossowski.elemont.repositories.OperacjaRepository;
import org.kossowski.elemont.repositories.UserRepository;
import org.kossowski.elemont.utils.JSFUtils;
import org.kossowski.elemont.utils.Printer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class GenerowanieOdcinkowRestBean {
    
    @Autowired
    protected KartaMagazynowaRepository kmRepo;
    
    @Autowired
    protected OperacjaRepository opRepo;
    
    @Autowired
    protected UserRepository userRepo;
    
    @Autowired
    protected OdcinekRepository odcRepo;
    
    @Autowired
    protected Printer printer;
    
    @ResponseBody
    @RequestMapping( value = "/{kmId}/{znacznikPoczatkowy}/{iloscOdcinkow}/{date}/9/{login}", method = RequestMethod.PUT, produces = "application/json")
    public byte[] perform( 
            @PathVariable(value="kmId")  Long kmId,
            @PathVariable(value="znacznikPoczatkowy") String strZnacznikPoczatkowy,
            @PathVariable(value="iloscOdcinkow") String strIloscOdcinkow,
            @PathVariable(value="date") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date,
            @PathVariable(value="login") String login ) throws Exception {
        
        System.out.println("kmId " + kmId);
        System.out.println("znacznikPoczatkowy " + strZnacznikPoczatkowy);
        System.out.println("iloscOdcinkow " + strIloscOdcinkow);
        System.out.println("date " + date);
        System.out.println("login " + login);
        
        BigDecimal znacznikPoczatkowy;
        int iloscOdcinkow;
        
        try {
            znacznikPoczatkowy = new BigDecimal(strZnacznikPoczatkowy);
        } catch( Exception e ) {
            return "Znacznik początkowy - błąd konwersji na liczbę".getBytes("utf-8");
        }
        
        try {
            iloscOdcinkow = Integer.parseInt(strIloscOdcinkow);
        } catch( Exception e ) {
            return "Ilość odcinków - błąd konwersji na liczbę".getBytes("utf-8");
        }
        
        User utworzyl = userRepo.findOne(login);
        KartaMagazynowa km  = kmRepo.findOne(kmId);
        
         if( utworzyl == null ) {
            return "Brak utykownika".getBytes("utf-8");
        }
         
        if( km == null ) {
            return "Nie ma takiej partii magazynowej".getBytes("utf-8");
        }
        
        for( int i = 0; i < iloscOdcinkow; i++ ) {
            Odcinek odc = new Odcinek();
            odc = odcRepo.save(odc);
            NowyOdcinek no = new NowyOdcinek( utworzyl,
                    GregorianCalendar.getInstance().getTime(), 
                    odc, znacznikPoczatkowy, utworzyl);
            
            no = opRepo.save( no );
            km.addOcinek(odc);
            km.addOperation(no);
            
            try {
                no.accept();
            } catch (Exception e ) {
                no.setOdcinek(null);
                opRepo.save(no);
                km.removeOperation(no);
                km.removeOdcinek(odc);
                odcRepo.delete(odc);
                opRepo.delete(no);
                
                return e.getMessage().getBytes("utf-8");
            };
            
            km = kmRepo.save(km);
            print(odc);
            System.out.println("drukuje");
        }
       
        return "ok".getBytes("utf-8");
    }
    
    @Value("${print.filePath}")
    public String filePath; //= "/tmp/";
    
    @Value("${print.printString}") 
    public String printString; //= "lp -d awlosz " ;
    
    public void print( Odcinek o ) {
        System.out.println("printuję");
        
        
        System.out.println("Print path " + filePath );
        
        Etykieta etA1 = new EtykietaQR3( o, SelektorZawieszki.A1);
        Etykieta etA2 = new EtykietaQR4( o, SelektorZawieszki.A2);
        Etykieta etB1 = new EtykietaQR3( o, SelektorZawieszki.B1);
        Etykieta etB2 = new EtykietaQR4( o, SelektorZawieszki.B2);
        
        Etykieta[] kolejnoscEtykiet = { etA1, etA2, etB1, etB2 };
        
        StringBuilder sb = new StringBuilder();
        for( Etykieta e : kolejnoscEtykiet)
            sb.append( e.printerString() ).append("\n");
        
        
        
        try {
            //File file = File.createTempFile("zpl", ".zpl");
            
            File file = new File( filePath + "odc"+o.getId() );
            
            FileOutputStream fout = new FileOutputStream(file);
            fout.write( sb.toString().getBytes());
            fout.flush();
            fout.close();
            
            //java.lang.Runtime.getRuntime().exec("lp -d cab_EOS1_300 /Users/jkossow/Downloads/qr1.zpl");
            //String s = "lp -d cab_EOS1_300 \"" + file.getCanonicalPath() +"\"";
            //String s = "lp -d kuba /tmp/odc"+o.getId();
            
            printer.auth();
            
            String s = printString + " " + filePath + "odc"+o.getId();
            System.out.println( "pol drukowania " +  s );
            java.lang.Runtime.getRuntime().exec( s );
            Thread.sleep(2000);
            
            //file.delete();
            //System.out.println( et.printerString() );
        } catch ( Exception e) {
            e.printStackTrace();
        }
        
        
    }
    
}
