/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.web.operacje;

import java.io.File;
import java.io.FileOutputStream;
import org.kossowski.elemont.domain.KartaMagazynowa;
import org.kossowski.elemont.domain.Odcinek;
import org.kossowski.elemont.domain.Operacja;
import org.kossowski.elemont.domain.SelektorZawieszki;
import org.kossowski.elemont.domain.operacje.NowyOdcinek;
import org.kossowski.elemont.qr.Etykieta;
import org.kossowski.elemont.qr.EtykietaQR1;
import org.kossowski.elemont.qr.EtykietaQR3;
import org.kossowski.elemont.qr.EtykietaQR4;
import org.kossowski.elemont.repositories.KartaMagazynowaRepository;
import org.kossowski.elemont.repositories.OdcinekRepository;
import org.kossowski.elemont.repositories.OperacjaRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    private KartaMagazynowa km;
    private int ilosc = 1;
    private Long kartaId = 0L;
    
    
    public String preDruk() {
        return "/commons/operacje/drukPrzyw.xhtml";
    }
    
    public String drukuj() {
        
        for( int i = 0; i < ilosc; i++ ) {
            Odcinek odc = new Odcinek();
            odc = odcRepo.save(odc);
            NowyOdcinek no = new NowyOdcinek( odc );
            no = opRepo.save( no );
            km.addOcinek(odc);
            km.addOperation(no);
            
            try {
                no.accept();
            } catch (Exception e ) { e.printStackTrace(); };
            
            km = kmRepo.save(km);
            print(odc);
            System.out.println("drukuje");
        }
        
        return "/commons/karta_mag/list.xhtml";
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
    
    public void print( Odcinek o ) {
        System.out.println("printuję");
        
        
        Etykieta et = new EtykietaQR3( o, SelektorZawieszki.A1);
        
        try {
            //File file = File.createTempFile("zpl", ".zpl");
            
            File file = new File("/tmp/plikjk1");
            
            FileOutputStream fout = new FileOutputStream(file);
            fout.write( et.printerString().getBytes());
            fout.flush();
            fout.close();
            
            //java.lang.Runtime.getRuntime().exec("lp -d cab_EOS1_300 /Users/jkossow/Downloads/qr1.zpl");
            //String s = "lp -d cab_EOS1_300 \"" + file.getCanonicalPath() +"\"";
            String s = "lp -d cab_EOS1_300 /tmp/plikjk1";
            System.out.println( s );
            java.lang.Runtime.getRuntime().exec( s );
            
            //file.delete();
            System.out.println( et.printerString() );
        } catch ( Exception e) {
            e.printStackTrace();
        }
        
        et = new EtykietaQR3( o, SelektorZawieszki.B1);
        
        try {
            //File file = File.createTempFile("zpl", ".zpl");
            
            File file = new File("/tmp/plikjk2");
            
            FileOutputStream fout = new FileOutputStream(file);
            fout.write( et.printerString().getBytes());
            fout.flush();
            fout.close();
            
            //java.lang.Runtime.getRuntime().exec("lp -d cab_EOS1_300 /Users/jkossow/Downloads/qr1.zpl");
            //String s = "lp -d cab_EOS1_300 \"" + file.getCanonicalPath() +"\"";
            String s = "lp -d cab_EOS1_300 /tmp/plikjk2";
            System.out.println( s );
            java.lang.Runtime.getRuntime().exec( s );
            
            //file.delete();
            System.out.println( et.printerString() );
        } catch ( Exception e) {
            e.printStackTrace();
        }
    
        et = new EtykietaQR4( o, SelektorZawieszki.A2);
        
        try {
            //File file = File.createTempFile("zpl", ".zpl");
            
            File file = new File("/tmp/plikjk3");
            
            FileOutputStream fout = new FileOutputStream(file);
            fout.write( et.printerString().getBytes());
            fout.flush();
            fout.close();
            
            //java.lang.Runtime.getRuntime().exec("lp -d cab_EOS1_300 /Users/jkossow/Downloads/qr1.zpl");
            //String s = "lp -d cab_EOS1_300 \"" + file.getCanonicalPath() +"\"";
            String s = "lp -d cab_EOS1_300 /tmp/plikjk3";
            System.out.println( s );
            java.lang.Runtime.getRuntime().exec( s );
            
            //file.delete();
            System.out.println( et.printerString() );
        } catch ( Exception e) {
            e.printStackTrace();
        }
        
        et = new EtykietaQR4( o, SelektorZawieszki.B2);
        
        try {
            //File file = File.createTempFile("zpl", ".zpl");
            
            File file = new File("/tmp/plikjk4");
            
            FileOutputStream fout = new FileOutputStream(file);
            fout.write( et.printerString().getBytes());
            fout.flush();
            fout.close();
            
            //java.lang.Runtime.getRuntime().exec("lp -d cab_EOS1_300 /Users/jkossow/Downloads/qr1.zpl");
            //String s = "lp -d cab_EOS1_300 \"" + file.getCanonicalPath() +"\"";
            String s = "lp -d cab_EOS1_300 /tmp/plikjk4";
            System.out.println( s );
            java.lang.Runtime.getRuntime().exec( s );
            
            //file.delete();
            System.out.println( et.printerString() );
        } catch ( Exception e) {
            e.printStackTrace();
        }
        
    }
    
    
}
