/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.qr;

import org.kossowski.elemont.domain.Odcinek;
import org.kossowski.elemont.domain.SelektorZawieszki;

/**
 *
 * @author jkossow
 */
public class QR2 extends QR {

    private static final String QR2Prefix = "02;";
    
    private Long id_odcinka;
    private SelektorZawieszki selektor;
    
    public QR2( Odcinek odcinek, SelektorZawieszki selektor ) {
        this.id_odcinka = odcinek.getId();
        this.selektor = selektor;
    }

    @Override
    public String encode() {
        return QR2Prefix + id_odcinka + selektor;
    }
    
    public Long getId_odcinka() {
        return id_odcinka;
    }

    public void setId_odcinka(Long id_odcinka) {
        this.id_odcinka = id_odcinka;
    }

    public SelektorZawieszki getSelektor() {
        return selektor;
    }

    public void setSelektor(SelektorZawieszki selektor) {
        this.selektor = selektor;
    }
    
    
    
}
