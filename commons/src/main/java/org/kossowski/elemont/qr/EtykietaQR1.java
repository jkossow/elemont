/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.qr;

import org.kossowski.elemont.domain.KartaMagazynowa;

/**
 *
 * @author jkossow
 */
public class EtykietaQR1 extends Etykieta {

    private QR1 qr1;
    private Long id_partii;
    private String nazwa;
    
    
    
    public EtykietaQR1( KartaMagazynowa km ) {
        this.qr1 = new QR1( km );
        this.id_partii = km.getId();
        this.nazwa = km.getMaterial().getNazwa();
    }

    @Override
    public String printerString() {
        return  "^XA\n" +
                "^LL800\n" +
                "^FWR,0\n" +
                "^FO140,200,0^AE^FD"+ id_partii +"^FS\n" +
                "^FO70,200,0^AE^" + nazwa + "^FS\n" +
                "^FO50,30^BQN,2,6^FDMA," + qr1.encode() + "^FS\n" +
                "^XZ";
    };
    
    /*
    ^XA
    ^LL800
    ^FWR,0
    ^FO140,200,0^AE^FD1234567890^FS
    ^FO70,200,0^AE^FDYKY_03 15mm2^FS
    ^FO50,30^BQN,2,6^FDMA,01;1234567890^FS
    ^XZ
    */
    
}
