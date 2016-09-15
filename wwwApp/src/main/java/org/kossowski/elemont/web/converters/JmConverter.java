/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import org.kossowski.elemont.domain.Jm;
import org.kossowski.elemont.domain.Projekt;
import org.kossowski.elemont.repositories.JmRepository;
import org.kossowski.elemont.repositories.ProjektRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/**
 *
 * @author jkossow
 */


@Service
public class JmConverter implements Converter  {

    @Autowired
    private JmRepository jmRepo;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        if( value.isEmpty() )
            return null;
        
        Long id = Long.valueOf( value );
        Jm jm;
        if( id != 0L) 
            jm = jmRepo.findOne(id);
        else
            jm = null; //p = new Projekt();
        return jm;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       
        Jm jm;
        try {
            jm = (Jm)value;
        } catch ( ClassCastException e) {
            jm = new Jm();
        }
        
        return jm != null ? jm.getId().toString() : "0";
        
    }

    
    
    
    
    
}
