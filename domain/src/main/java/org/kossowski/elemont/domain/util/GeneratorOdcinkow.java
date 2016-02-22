/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.domain.util;

import java.util.ArrayList;
import java.util.List;
import org.kossowski.elemont.domain.Odcinek;

/**
 *
 * @author jkossow
 */
public class GeneratorOdcinkow {
    
    private  long id = 0;
    private  List<Odcinek> lista = new ArrayList<>(); 
    
    public GeneratorOdcinkow() {
        
    }
        
    
    public  Odcinek nowyOdcinek() {
            Odcinek o = new Odcinek();
            o.setId( ++id );
            lista.add(o);
            return o;
    }
    
    
    
}
