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
public class EtykietaQR3  extends Etykieta {

    private QR3 qr3;
    private SelektorZawieszki selektor;
    private String nazwa_odcinka;
    private String nazwa_materialu;
    
    public EtykietaQR3( Odcinek o, SelektorZawieszki selektor) {
        this.qr3 =  new QR3( o, selektor );
        this.selektor = selektor;
        this.nazwa_odcinka = o.getNazwa();
        this.nazwa_materialu = o.getKartaMagazynowa().getMaterial().getNazwa();
                
        
    }

    @Override
    public String printerString() {
        return  "^XA\n" +
                "^LL800\n" +
                "^FWR,0\n" +
                "^FO50,200,0^AG^FD" + selektor + "^FS\n" +
                "^FO140,300,0^AE^FD" + nazwa_odcinka +"^FS\n" +
                "^FO70,300,0^AE^FD" + nazwa_materialu + "^FS\n" +
                "^FO50,30^BQN,2,6^FDMA," + qr3.encode() + "^FS\n" +
                "^XZ";
        
        /*        
        ^XA
        ^LL800
        ^FWR,0
        ^FO50,200,0^AG^FDD^FS
        ^FO140,300,0^AE^FD1234567890^FS
        ^FO70,300,0^AE^FDYKY_03 15mm2^FS
        ^FO50,30^BQN,2,6^FDMA,02;1234567890^FS
        ^XZ
        */
    }
    
    
    
}
