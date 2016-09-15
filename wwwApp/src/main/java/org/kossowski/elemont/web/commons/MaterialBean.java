/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.web.commons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import org.kossowski.elemont.domain.Grupa;
import org.kossowski.elemont.domain.Jm;
import org.kossowski.elemont.domain.Material;
import org.kossowski.elemont.repositories.GrupaRepository;
import org.kossowski.elemont.repositories.JmRepository;
import org.kossowski.elemont.repositories.MaterialRepository;
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
public class MaterialBean implements Serializable{
    
    @Autowired
    private MaterialRepository matRepo;
    
    @Autowired
    protected JmRepository jmRepo;
    
    @Autowired
    protected GrupaRepository grupaRepo;
    
    private Material material = new Material();
    
    
    public List<Material> getFindAll() {
        return matRepo.findAll();
    }
    
    public String  predAdd() {
        return "add.xhtml";
        
    }
    
    public String prepEdit() {
        
        String s = JSFUtils.getRequestParam("id");
        Long id = new Long(s);
        
        material = matRepo.findOne(id);
        
        return "edit.xhtml";
    }
    
    public String list() {
        return "list.xhtml";
    }
    
    public String save() {
        matRepo.save( material );
        return "list.xhtml";
    }

    public List<SelectItem> getGrupaSelectItems() {
        
        
        List<SelectItem> si = new ArrayList<>();
        
        si.add( new SelectItem( null , "---", "", false, false, true ));
        for( Grupa  g: grupaRepo.findAll() ) 
            si.add( new SelectItem( g, g.getSymbol() ) );
        
        return si;
    }
    
    
    public List<SelectItem> getJmSelectItems() {
        
        
        List<SelectItem> si = new ArrayList<>();
        
        si.add( new SelectItem( null , "---", "", false, false, true ));
        for( Jm jm : jmRepo.findAll() ) 
            si.add( new SelectItem( jm, jm.getSymbol()) );
        
        return si;
    }
    
    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
    
    
}
