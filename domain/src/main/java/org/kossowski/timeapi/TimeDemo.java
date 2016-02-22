/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.timeapi;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 *
 * @author jkossow
 */
public class TimeDemo {
    
    public static void main(String[] args) {
        
        Instant i = Instant.now();
        LocalDate ld = LocalDate.now();
        LocalTime lt = LocalTime.now();
        LocalDateTime ldt = LocalDateTime.now();
        
        System.out.println( ld );
        System.out.println( lt );
        System.out.println( ldt );
        
    }
    
}
