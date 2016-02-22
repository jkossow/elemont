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
import org.kossowski.elemont.domain.User;
import org.kossowski.elemont.repositories.MaterialRepository;
import org.kossowski.elemont.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/**
 *
 * @author jkossow
 */


@Service
public class UserKodQRConverter implements Converter  {

    @Autowired
    private UserRepository userRepo;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        if( value.isEmpty() )
            return null;
        
      
        
        String kodQR =  value;
        User u;
        if( kodQR != "" ) 
            u = userRepo.findFirstByKodQR(kodQR );
        else
            u = new User();
        return u;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        
        User u;
        
        try {     
            u = (User)value;
        } catch ( ClassCastException e ) { 
            u = new User();
        }
                
        return u != null ? u.getKodQR(): "";
    }
    
    
    
    
}
