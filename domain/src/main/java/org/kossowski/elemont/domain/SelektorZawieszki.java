package org.kossowski.elemont.domain;


import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jkossow
 */
public enum SelektorZawieszki  implements Serializable {
    
    A1(0), A2(1), B1(2), B2(3);
    
    private final int selectorCode; 

    private SelektorZawieszki(int selectorCode) {
        this.selectorCode = selectorCode;
    }
    
    
    
}
