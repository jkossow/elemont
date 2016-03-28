/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import org.kossowski.elemont.domain.Projekt;
import org.kossowski.elemont.repositories.ProjektRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/**
 *
 * @author jkossow
 */


@Service
public class ProjektConverter implements Converter  {

    @Autowired
    private ProjektRepository projRepo;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        if( value.isEmpty() )
            return null;
        
        Long id = Long.valueOf( value );
        Projekt p;
        if( id != 0L) 
            p = projRepo.findOne(id);
        else
            p = null; //p = new Projekt();
        return p;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       
        Projekt p;
        try {
            p = (Projekt)value;
        } catch ( ClassCastException e) {
            p = new Projekt();
        }
        
        return p != null ? p.getId().toString() : "0";
        
    }

    
    
    
    
    
}
