/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author jkossow
 */

@Component
public class Printer {
    
    @Value("${print.server}")
    public String server;
    
    @Value("${print.share}")
    public String share;
    
    @Value("${print.user}")
    public String user;
    
    @Value("${print.password}")
    public String password;
    
    //@Value("print.command")
    //public String command;
    
    public void auth() {
        // String s = "net use \\\\"+server+"\\"+share+ " /u:" + user + " " + password;
        // 
        // try {
        //    Runtime.getRuntime().exec( s );
        // } catch (Exception e ) { e.printStackTrace(); };
        //    
        //    System.out.println( s );
        
    }
    
    public void print( String stringToPrint ) {
        
    }
    
}
