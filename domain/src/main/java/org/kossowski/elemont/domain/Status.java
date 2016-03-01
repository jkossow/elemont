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
