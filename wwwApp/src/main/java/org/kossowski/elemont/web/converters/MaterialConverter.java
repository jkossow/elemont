/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.kossowski.elemont.domain.Material;
import org.kossowski.elemont.repositories.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/**
 *
 * @author jkossow
 */


@Service
public class MaterialConverter implements Converter  {

    @Autowired
    private MaterialRepository matRepo;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        if( value.isEmpty() )
            return null;
        
              
        Long id = Long.valueOf( value );
        Material m;
        if( id != 0L) 
            m = matRepo.findOne(id);
        else
            m = new Material();
        return m;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        
        Material m;
        try {     
            m = (Material)value;
        } catch ( ClassCastException e ) { 
            m = new Material();
        }
                
        return m != null ? m.getId().toString() : "0";
    }
    
    
    
    
}
