/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author jkossow
 */

public enum Status implements Serializable {
    
    // S0 - stan początkowy  
    // S1 - przyjety z mag. głównego
    // S2 - wydany na budowę
    // S3 - przypisane kody QR2, wygenerowanodcinki
    // s4 - ułożony
    // S5 - podłączony pierwszy koniec
    // S6 - podłączony drugi koniec
    // S7 - (nie używamy)
    // S8 - wykorzystany
    // S9 - zwrócony do mag głównego po weryfikacji ścinek
    
    
    S0(0), S1(1), S2(2), S3(3), S4(4), S5(5), S6(6), S7(7), S8(8), S9(9);
    
   public final int statusCode;

    private Status(int statusCode) {
        
        this.statusCode = statusCode;
        
    }
   
    public static Collection<Status> naBudowie() {
         Collection<Status> ls = new ArrayList<>();
         
         ls.add( Status.S2 );
         return ls;
    }
    
}
