/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import org.kossowski.elemont.domain.Umowa;
import org.kossowski.elemont.repositories.UmowaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author jkossow
 */


@Controller
public class UmowaConverter implements Converter  {

    @Autowired
    private UmowaRepository umRepo;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        if( value.isEmpty() )
            return null;
        
        Long id = Long.valueOf( value );
        Umowa u;
        if( id != 0L) 
            u = umRepo.findOne(id);
        else
            u = new Umowa();
        return u;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
       
        Umowa u ;
        try {
            u = (Umowa)value;
        } catch ( ClassCastException e) {
            u = new Umowa();
        }
        return u != null ? u.getId().toString() : "0";
    }
    
    
    
    
}
