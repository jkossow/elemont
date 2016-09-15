/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import org.kossowski.elemont.domain.Grupa;
import org.kossowski.elemont.domain.Projekt;
import org.kossowski.elemont.repositories.GrupaRepository;
import org.kossowski.elemont.repositories.ProjektRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/**
 *
 * @author jkossow
 */


@Service
public class GrupaCoverter implements Converter  {

    @Autowired
    private GrupaRepository grupaRepo;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        if( value.isEmpty() )
            return null;
        
        Long id = Long.valueOf( value );
        Grupa g;
        if( id != 0L) 
            g = grupaRepo.findOne(id);
        else
            g = null; //p = new Projekt();
        return g;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       
        Grupa g;
        try {
            g = (Grupa)value;
        } catch ( ClassCastException e) {
            g = new Grupa();
        }
        
        return g != null ? g.getId().toString() : "0";
        
    }

    
    
    
    
    
}
