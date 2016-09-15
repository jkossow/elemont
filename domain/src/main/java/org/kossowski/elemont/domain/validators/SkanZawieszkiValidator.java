/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.domain.validators;

import org.kossowski.elemont.domain.Odcinek;

/**
 *
 * @author jkossow
 */
public interface SkanZawieszkiValidator {
    
    public static boolean  ZNACZNIKI_ROSNACO = true;
    public static boolean  ZNACZNIKI_MALEJACO = false;
    
    public boolean zajety();
    public boolean dobraSekwencja( Odcinek o );
    
    public static SkanZawieszkiValidator getInstance( boolean znaczniki ) {
        if( ZNACZNIKI_ROSNACO )
            return new SkanZawieszkiValidatorZnacznikiRosnacoImpl();
        else 
            return new SkanZawieszkiValidatorZnacznikiMalejacoImpl();
    }
    
}
