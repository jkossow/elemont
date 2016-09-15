/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.web.serwis;

import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;

/**
 *
 * @author jkossow
 */
public class GregorianCal {
    public static void main(String[] args) {
        System.out.println( GregorianCalendar.getInstance(TimeZone.getTimeZone("pl_PL")).getTime());
        
        Set<String> calSet = GregorianCalendar.getAvailableCalendarTypes();
        
        for( String s : calSet )
            System.out.println( s );
        
        Locale[] loc = GregorianCalendar.getAvailableLocales();
        
         
        for( Locale l : loc )
            System.out.println( l );
        
        
        String s = "2054A2";
        System.out.println( s.substring( s.length()-2, s.length() ));
    }
}
