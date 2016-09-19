/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.domain.validators;

import java.math.BigDecimal;

/**
 *
 * @author jkossow
 */
public interface ArytmetykaOznacznikow {
    
    public  BigDecimal ilosc( BigDecimal ilosc, BigDecimal pocz, BigDecimal koniec,
            boolean koniecDostepny );
        
    
    
    
}
