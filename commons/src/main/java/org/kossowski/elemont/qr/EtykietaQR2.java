/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.qr;

import org.kossowski.elemont.domain.User;

/**
 *
 * @author jkossow
 */
public class EtykietaQR2 extends Etykieta {
    
    private QR2 qr2;
    
    private String nazwisko;
    private String imie;

    public EtykietaQR2( User user ) {
        qr2 = new QR2( user);
        this.nazwisko = user.getNazwisko();
        this.imie = user.getImie();
    }

    @Override
    public String printerString() {
        return "^XA\n" +
                "^LL800\n" +
                "^FWR,0\n" +
                "^FO140,200,0^AE^FD" + nazwisko + "^FS\n" +
                "^FO70,200,0^AE^FD" + imie + "^FS\n" +
                "^FO50,30^BQN,2,6^FDMA," + qr2.encode() + "^FS\n" +
                "^XZ"; 
    }
   
    /*
    ^XA
    ^LL800
    ^FWR,0
    ^FO140,200,0^AE^FDnazwisko^FS
    ^FO70,200,0^AE^FDimie^FS
    ^FO50,30^BQN,2,6^FDMA,03;1234567890^FS
    ^XZ
    */
    
    
    
}
