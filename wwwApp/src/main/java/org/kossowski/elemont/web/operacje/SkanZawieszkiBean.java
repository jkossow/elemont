/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.web.operacje;

import java.math.BigDecimal;
import org.kossowski.elemont.domain.KartaMagazynowa;
import org.kossowski.elemont.domain.Odcinek;
import org.kossowski.elemont.domain.operacje.SkanZawieszki;
import org.kossowski.elemont.repositories.KartaMagazynowaRepository;
import org.kossowski.elemont.repositories.OdcinekRepository;
import org.kossowski.elemont.repositories.OperacjaRepository;
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
public class SkanZawieszkiBean {
    
    @Autowired
    protected OperacjaRepository opRepo;
    
    @Autowired
    protected OdcinekRepository odcRepo;
    
    @Autowired
    protected KartaMagazynowaRepository kmRepo;
    
    
    
    private String QRCode2;
    private BigDecimal znacznik;

    public String getQRCode2() {
        return QRCode2;
    }

    public String perform() {
        
        String s = QRCode2.substring(0, QRCode2.length() -2 );
        Long idOdc;
        
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
        
        SkanZawieszki skanZaw = new SkanZawieszki(QRCode2, znacznik, null);
        skanZaw = opRepo.save( skanZaw );
        km.addOperation(skanZaw);
        
        try {
            skanZaw.accept();
        } catch (Exception e) { 
            km.removeOperation(skanZaw);
            opRepo.delete(skanZaw);
            JSFUtils.addMessage(e.getMessage());
            return null;
        };
        JSFUtils.addMessage("Skan zarejestrowany");
        kmRepo.save( km );
        return "";
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
    
    
    
    
    
}
