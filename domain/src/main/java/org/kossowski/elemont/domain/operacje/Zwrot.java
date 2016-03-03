/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.domain.operacje;

import java.math.BigDecimal;
import org.kossowski.elemont.domain.Operacja;

/**
 *
 * @author jkossow
 */
public class Zwrot extends Operacja {
    
    private BigDecimal ilosc;
    
    public Zwrot( BigDecimal ilosc ) {
        this.ilosc = ilosc;
    }
    
}
