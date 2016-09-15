/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.domain.validators;

import java.math.BigDecimal;
import java.util.ArrayList;
import org.kossowski.elemont.domain.Odcinek;

/**
 *
 * @author jkossow
 */
public class SkanZawieszkiValidatorZnacznikiMalejacoImpl implements SkanZawieszkiValidator {

    @Override
    public boolean zajety() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean dobraSekwencja(Odcinek o) {
        
        ArrayList<BigDecimal>  list = new ArrayList<>();
        if( o.getA1() != null) list.add(o.getA1());
        if( o.getA2() != null) list.add(o.getA2());
        if( o.getB2() != null) list.add(o.getB2());
        if( o.getB1() != null) list.add(o.getB1());
        
        boolean result = true;
        
        for( int i = 0; i < list.size() - 1; 
                 result = result && list.get(i).compareTo( list.get(i+1)) >=0 , i++ );
        
        return result &&
               (o.getA2() == null || o.getB2() == null || o.getA2().compareTo(o.getB2()) >  0 );
               
               
   
    }
    
    
    
}
