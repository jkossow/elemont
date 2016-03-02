/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.web.operacje;

import java.math.BigDecimal;
import org.kossowski.elemont.domain.KartaMagazynowa;
import org.kossowski.elemont.domain.Odcinek;
import org.kossowski.elemont.domain.operacje.SkanScinka;
import org.kossowski.elemont.domain.operacje.SkanZawieszki;
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
public class SkanScinkaBean {
    
    @Autowired
    protected OperacjaRepository opRepo;
    
    @Autowired
    protected OdcinekRepository odcRepo;
    
    @Autowired
    protected KartaMagazynowaRepository kmRepo;
    
    private String QRCode2;
    private BigDecimal znacznik;
    
    private BigDecimal spodzScA = null;
    private BigDecimal spodzScB = null;
    
    
    public String perform() {
        
        String s = QRCode2.substring(0, QRCode2.length() -1 );
        Long idOdc = new Long(s);
        
        Odcinek o = odcRepo.findOne(idOdc);
        //tymczasowo
        //Odcinek o = new Odcinek();
        System.out.println("isset A " + o.isSetN("A") + " " + o.getN("A") + " " + o.getA() );
        System.out.println("isset B " + o.isSetN("B") + " " + o.getN("B") + " " + o.getB() );
        System.out.println("isset C " + o.isSetN("C") + " " + o.getN("C") + " " + o.getC() );
        System.out.println("isset D " + o.isSetN("D") + " " + o.getN("D") + " " + o.getD() );
        
        KartaMagazynowa km = o.getKartaMagazynowa();
        
        SkanScinka skanScinka = new SkanScinka(QRCode2, znacznik);
        skanScinka = opRepo.save( skanScinka );
        km.addOperation( skanScinka );
        
        try {
            skanScinka.accept();
        } catch (Exception e) { e.printStackTrace();};
        
        kmRepo.save( km );
        return "";
    } 
    
    public void onQR2CodeComplete() {
        System.out.println("hello "  + QRCode2);
        String s = QRCode2.substring(0, QRCode2.length() -1 );
        Long idOdc = new Long(s);
        
        Odcinek o = odcRepo.findOne( idOdc );
        System.out.println( "Odcinek o: " + o );
        
        if( o == null ) {
            
            setSpodzScA(null);
            setSpodzScB(null);
            return;
        }
        
        setSpodzScA( o.spodzScinekA());
        setSpodzScB( o.spodzScinekB());
        
    }
    
    public String getQRCode2() {
        return QRCode2;
    }
    
    public void setQRCode2(String QRCode2) {
        this.QRCode2 = QRCode2;
    }

    public BigDecimal getZnacznik() {
        return znacznik;
    }

    public void setZnacznik(BigDecimal znacznik) {
        this.znacznik = znacznik;
    }

    public BigDecimal getSpodzScA() {
        return spodzScA;
    }

    public void setSpodzScA(BigDecimal spodzScA) {
        this.spodzScA = spodzScA;
    }

    public BigDecimal getSpodzScB() {
        return spodzScB;
    }

    public void setSpodzScB(BigDecimal spodzScB) {
        this.spodzScB = spodzScB;
    }
    
    
    
    
    
}
