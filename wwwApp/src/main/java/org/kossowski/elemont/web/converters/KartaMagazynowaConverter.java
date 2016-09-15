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
import org.kossowski.elemont.domain.KartaMagazynowa;
import org.kossowski.elemont.domain.Material;
import org.kossowski.elemont.repositories.KartaMagazynowaRepository;
import org.kossowski.elemont.repositories.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/**
 *
 * @author jkossow
 */
@Service
public class KartaMagazynowaConverter implements Converter {

    @Autowired
    private KartaMagazynowaRepository kmRepo;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        if (value.isEmpty()) {
            return null;
        }
        Long id;
        try {
            id = Long.valueOf(value);
        } catch ( Exception e ) { return null; }
        KartaMagazynowa km;
        if (id != 0L) {
            km = kmRepo.findOne(id);
        } else {
            //km = new KartaMagazynowa();
            km = null;
        }
        return km;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        KartaMagazynowa km;
        try {
            km = (KartaMagazynowa) value;
        } catch (ClassCastException e) {
            km = new KartaMagazynowa();
        }

        return km != null ? km.getId().toString() : "0";
    }

}
