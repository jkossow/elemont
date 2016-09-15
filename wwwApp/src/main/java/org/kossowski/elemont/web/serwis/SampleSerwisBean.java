/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.web.serwis;


import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 *
 * @author jkossow
 */

@Controller
public class SampleSerwisBean {


    public void listFilesFromDirectory() throws Exception {
        
        File file = new File("/");
        File[] dirContent = file.listFiles();
        
        for( File f : dirContent )
            System.out.println( f.getName() );
        
        URL url = this.getClass().getClassLoader().getResource("/org/kossowski/elemont/initdata/dostawcy.txt");
        System.out.println( url );
        File ff = new File(url.toURI() );
        System.out.println( ff );
        System.out.println( ff.canRead() );
        
        Paths.get(url.toURI());
        
        //ClassPathResource s = new ClassPathResource(".");
        //s.
        
        
    }
}
