/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.timeapi;

import java.math.BigDecimal;

/**
 *
 * @author jkossow
 */
public class BigDecimalCompare {
    public static void main(String[] args) {
        
        BigDecimal a = new BigDecimal( 5 );
        BigDecimal b = new BigDecimal( 6 );
        System.out.println( b.compareTo(a));
    }
}
