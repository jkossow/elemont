/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.web.commons;

import java.io.Serializable;
import java.util.List;
import org.kossowski.elemont.domain.Jm;
import org.kossowski.elemont.repositories.JmRepository;
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
public class JmBean implements Serializable {
    
    
    private Jm jm = new Jm();
    
    @Autowired
    private JmRepository jmRepo;
    
   
    
    public List<Jm> getFindAll() {
        return jmRepo.findAll();
    }

    public String prepAdd() {
        return "add.xhtml";
    }
    
    public String prepEdit() {
        
        String s = JSFUtils.getRequestParam("id");
        Long id = new Long( s );
        
        jm = jmRepo.findOne(id);
        
        return "edit.xhtml";
    }
    
    public String save() {
        jmRepo.save(jm);
        return "list.xhtml";
    }
    
    public Jm getJm() {
        return jm;
    }

    public void setJm(Jm jm) {
        this.jm = jm;
    }
    
    
    
}
