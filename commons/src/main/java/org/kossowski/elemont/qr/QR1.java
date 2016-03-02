/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.qr;

import org.kossowski.elemont.domain.KartaMagazynowa;
import org.kossowski.elemont.domain.Odcinek;
import org.kossowski.elemont.domain.SelektorZawieszki;

/**
 *
 * @author jkossow
 */
public class QR1 extends QR {
    
    private static final String QR1Prefix = "01;";
    
    private Long id_karty;
   
    
    public QR1 ( KartaMagazynowa km ) {
        
        this.id_karty = km.getId();
        
    }

    public QR1(Long id_karty) {
        this.id_karty = id_karty;
    }
    
    

    @Override
    public String encode() {
        return QR1Prefix + id_karty;
    }

    public Long getId_karty() {
        return id_karty;
    }

    public void setId_karty(Long id_karty) {
        this.id_karty = id_karty;
    }
    
    
    
    
}
