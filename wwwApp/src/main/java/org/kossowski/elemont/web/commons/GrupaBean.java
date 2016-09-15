/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.web.commons;

import java.io.Serializable;
import java.util.List;
import org.kossowski.elemont.domain.Grupa;
import org.kossowski.elemont.repositories.GrupaRepository;
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
public class GrupaBean  implements Serializable {
    
    @Autowired
    private GrupaRepository grupaRepo;
    
    private Grupa grupa = new Grupa();
    
    
    public List<Grupa> getFindAll() {
        return grupaRepo.findAll();
    }
    
    public String prepAdd() {
        
        return "add.xhtml";
    };
    
    public String prepEdit() {
        
        String s = JSFUtils.getRequestParam("id");
        Long id = new Long(s);
        
        grupa = grupaRepo.findOne(id);
        
        return "edit.xhtml";
    }
    
    public String save() {
        grupaRepo.save( grupa );
        return "list.xhtml";
    }

    public Grupa getGrupa() {
        return grupa;
    }

    public void setGrupa(Grupa grupa) {
        this.grupa = grupa;
    }
    
    
    
}
