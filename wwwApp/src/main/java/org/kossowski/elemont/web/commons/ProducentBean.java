/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.web.commons;

import java.io.Serializable;
import java.util.List;
import org.kossowski.elemont.domain.Grupa;
import org.kossowski.elemont.domain.Producent;
import org.kossowski.elemont.repositories.GrupaRepository;
import org.kossowski.elemont.repositories.ProducentRepository;
import org.kossowski.elemont.utils.JSFUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/**
 *
 * @author jkossow
 */

@Service
@Scope("request")
public class ProducentBean implements Serializable {
    
    @Autowired
    private ProducentRepository producentRepo;
    
    
    private Producent producent = new Producent();
    
    public String prepAdd() {
        
        
        return "add.xhtml";
    }
    
    public String list() {
       return "list.xhtml"; 
    }
    
    public String prepEdit() {
        String s = JSFUtils.getRequestParam("id");
        Long id = new Long(s);
        
        producent = producentRepo.findOne(id);
        return "edit.xhtml";
    }
    
    public String save() {
        
        producentRepo.save( producent );
        return "list.xhtml";
    }
    
    
    public List<Producent> getFindAll() {
        return producentRepo.findAll();
    }

    public Producent getProducent() {
        return producent;
    }

    public void setProducent(Producent producent) {
        this.producent = producent;
    }
    
    
    
    
}
