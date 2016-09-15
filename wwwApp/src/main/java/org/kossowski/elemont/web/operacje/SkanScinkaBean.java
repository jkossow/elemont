/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.web.operacje;

import java.math.BigDecimal;
import java.util.GregorianCalendar;
import org.kossowski.elemont.domain.KartaMagazynowa;
import org.kossowski.elemont.domain.Odcinek;
import org.kossowski.elemont.domain.operacje.SkanScinka;
import org.kossowski.elemont.domain.operacje.SkanZawieszki;
import org.kossowski.elemont.repositories.KartaMagazynowaRepository;
import org.kossowski.elemont.repositories.OdcinekRepository;
import org.kossowski.elemont.repositories.OperacjaRepository;
import org.kossowski.elemont.security.SecurityController;
import org.kossowski.elemont.utils.JSFUtils;
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
    
    @Autowired
    protected SecurityController secController;
    
    private String QRCode2;
    private BigDecimal znacznik;
    
    private BigDecimal spodzScA1 = null;
    private BigDecimal spodzScB1 = null;
    private String nazwaMaterialu;
    
    
    
    public String perform() {
        
        String s = QRCode2.substring(0, QRCode2.length() -2 );
        Long idOdc = new Long(s);
        
        
        try {
            idOdc = new Long(s);
        } catch (Exception e) {
            JSFUtils.addMessage("Błąd w QR kodzie");
            return null;
        }
        
        Odcinek o = odcRepo.findOne(idOdc);
        
        if( o == null ) {
            JSFUtils.addMessage("nie ma takiego odcinka");
            return null;
        }
        
        KartaMagazynowa km = o.getKartaMagazynowa();
        
        SkanScinka skanScinka = new SkanScinka( secController.getUser(), 
                GregorianCalendar.getInstance().getTime() , QRCode2, znacznik );
        skanScinka = opRepo.save( skanScinka );
        km.addOperation( skanScinka );
        
        try {
            skanScinka.accept();
        } catch (Exception e) { 
            km.removeOperation(skanScinka);
            opRepo.delete(skanScinka);
            JSFUtils.addMessage(e.getMessage());
            return null;
        };
        
        kmRepo.save( km );
        return "";
    } 
    
    public void onQR2CodeComplete() {
        System.out.println("hello "  + QRCode2);
        String s = QRCode2.substring(0, QRCode2.length() - 2 );
        System.out.println(s);
        
        Long idOdc = 0L;
        
        if( s.length()  < 2 )
            return;
        
        try {
            idOdc = new Long(s);
        } catch (NumberFormatException nfe) {
        };
        Odcinek o = odcRepo.findOne( idOdc );
        System.out.println( "Odcinek o: " + o );
        
        if( o == null ) {
            
            setSpodzScA1(null);
            setSpodzScB1(null);
            setNazwaMaterialu(null);
            return;
        }
        
        setSpodzScA1( o.spodzScinekA1());
        setSpodzScB1( o.spodzScinekB1());
        setNazwaMaterialu(o.getKartaMagazynowa().getMaterial().getNazwa());
        
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

    public BigDecimal getSpodzScA1() {
        return spodzScA1;
    }

    public void setSpodzScA1(BigDecimal spodzScA1) {
        this.spodzScA1 = spodzScA1;
    }

    public BigDecimal getSpodzScB1() {
        return spodzScB1;
    }

    public void setSpodzScB1(BigDecimal spodzScB1) {
        this.spodzScB1 = spodzScB1;
    }

    public String getNazwaMaterialu() {
        return nazwaMaterialu;
    }

    public void setNazwaMaterialu(String nazwaMaterialu) {
        this.nazwaMaterialu = nazwaMaterialu;
    }
    
    
    
    
    
}
