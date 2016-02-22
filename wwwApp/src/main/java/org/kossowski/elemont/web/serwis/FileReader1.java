/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.web.serwis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;
import org.kossowski.elemont.domain.Material;

/**
 *
 * @author jkossow
 */
public class FileReader1 {

    public static void main(String[] args) {
        //try {
        //    BufferedReader br = new BufferedReader( new FileReader("/material.txt"));
        //    String s;
        //    while( ( s = br.readLine()) != null )
        //        System.out.println(s);
        //  
        //} catch ( Exception e ){ e.printStackTrace(); };
        
        try {
            
            List<String> l = Files.readAllLines( Paths.get( "/material.txt"),Charset.forName("cp1250"));
            for( String s: l) {
                System.out.println(s);
        
            }
        } catch ( IOException e ){ e.printStackTrace(); };
        
    }

    
}
