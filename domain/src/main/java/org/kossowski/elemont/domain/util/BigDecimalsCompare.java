/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.domain.util;

import java.math.BigDecimal;

/**
 *
 * @author jkossow
 */
public class BigDecimalsCompare {
    
    public static void main(String[] args) {
        
        BigDecimal b1 = new BigDecimal(1L);
        BigDecimal b2 = new BigDecimal(1L).setScale(2);
        
        System.out.println( b1.equals(b2));
        System.out.println( b1.compareTo(b2));
        
        System.out.println(b1.longValue()==b2.longValue());
        
        
        
    }
    
}
