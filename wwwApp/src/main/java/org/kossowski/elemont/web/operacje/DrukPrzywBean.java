/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.web.operacje;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.GregorianCalendar;
import org.kossowski.elemont.domain.KartaMagazynowa;
import org.kossowski.elemont.domain.Odcinek;
import org.kossowski.elemont.domain.Operacja;
import org.kossowski.elemont.domain.SelektorZawieszki;
import org.kossowski.elemont.domain.User;
import org.kossowski.elemont.domain.operacje.NowyOdcinek;
import org.kossowski.elemont.qr.Etykieta;
import org.kossowski.elemont.qr.EtykietaQR1;
import org.kossowski.elemont.qr.EtykietaQR3;
import org.kossowski.elemont.qr.EtykietaQR4;
import org.kossowski.elemont.repositories.KartaMagazynowaRepository;
import org.kossowski.elemont.repositories.OdcinekRepository;
import org.kossowski.elemont.repositories.OperacjaRepository;
import org.kossowski.elemont.security.SecurityController;
import org.kossowski.elemont.utils.JSFUtils;
import org.kossowski.elemont.utils.Printer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 *
 * @author jkossow
 */

@Service
@Scope("request")
public class DrukPrzywBean {
    
    @Autowired
    protected KartaMagazynowaRepository kmRepo;
    
    @Autowired
    protected OperacjaRepository opRepo;
    
    @Autowired
    protected OdcinekRepository odcRepo;
    
    @Autowired
    protected SecurityController securityController;
    
    @Autowired
    protected Printer printer;
    
    private KartaMagazynowa km;
    private int ilosc = 1;
    private User owner; 
    private BigDecimal znacznikPoczatkowy;
    private Long kartaId = 0L;
    
    
    public String preDruk() {
        return "/commons/operacje/drukPrzyw.xhtml";
    }
    
    public String drukuj() {
        
        if( km == null ) {
            JSFUtils.addMessage("Nie ma partii o takim identyfikatorze");
            return null;
        }
        
         if( owner == null ) {
            JSFUtils.addMessage("Błędny QR-kod pracownika");
            return null;
        }
        
        for( int i = 0; i < ilosc; i++ ) {
            Odcinek odc = new Odcinek();
            odc = odcRepo.save(odc);
            NowyOdcinek no = new NowyOdcinek( securityController.getUser(),
                    GregorianCalendar.getInstance().getTime(), 
                    odc, znacznikPoczatkowy, owner);
            
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
                JSFUtils.addMessage( e.getMessage() ); 
                return null;
            };
            
            km = kmRepo.save(km);
            print(odc);
            System.out.println("drukuje");
        }
        JSFUtils.addMessage("Zawieszki wygenerowane");
        return "";
    };

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }

    public KartaMagazynowa getKm() {
        return km;
    }

    public void setKm(KartaMagazynowa km) {
        this.km = km;
    }

    public Long getKartaId() {
        return kartaId;
    }

    public void setKartaId(Long kartaId) {
        this.kartaId = kartaId;
    }

    public BigDecimal getZnacznikPoczatkowy() {
        return znacznikPoczatkowy;
    }

    public void setZnacznikPoczatkowy(BigDecimal znacznikPoczatkowy) {
        this.znacznikPoczatkowy = znacznikPoczatkowy;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
    
    @Value("${print.filePath}")
    public String filePath;
    
    @Value("${print.printString}") 
    public String printString;
    
    public void print( Odcinek o ) {
        System.out.println("printuję");
        
        
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
            System.out.println( s );
            java.lang.Runtime.getRuntime().exec( s );
            Thread.sleep(2000);
            
            //file.delete();
            //System.out.println( et.printerString() );
        } catch ( Exception e) {
            e.printStackTrace();
        }
        
        
    }
    
    
}
