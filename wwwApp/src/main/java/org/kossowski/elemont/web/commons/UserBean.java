/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.web.commons;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import org.kossowski.elemont.domain.Grupa;
import org.kossowski.elemont.domain.Umowa;
import org.kossowski.elemont.domain.User;
import org.kossowski.elemont.repositories.GrupaRepository;
import org.kossowski.elemont.repositories.UmowaRepository;
import org.kossowski.elemont.repositories.UserRepository;
import org.kossowski.elemont.utils.JSFUtils;
import org.primefaces.model.DualListModel;
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
public class UserBean {
    
    @Autowired
    private UserRepository userRepo;
    
    private User user = new User();
    
    
    private DualListModel<String> pickData;
    
    public List<User> getFindAll() {
        return userRepo.findAll();
    }
    
    public String prepAdd() {
        user = new User();
        
        List<String> source = roles();
        List<String> target = user.getRole();
        
        pickData = new DualListModel<String>( source, target );
        
        return "add.xhtml";
    }
    
    public String edit() {
        
        System.out.println("edit");
        
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> pm = fc.getExternalContext().getRequestParameterMap();
        
        String login = pm.get("login");
        
        user = userRepo.findOne(login);
        
        List<String> source = roles();
        List<String> target = user.getRole();
        
        source.removeAll(target);
        
        pickData = new DualListModel<String>( source, target );
        
        return "edit.xhtml";
        //return "/commons/user/edit.xhtml";
    }
    
    public String save() {
       
        user.setRole( pickData.getTarget() );
        try {
            userRepo.save( user );
        } catch ( Exception e ) {
            JSFUtils.addMessage(  e.getMessage() );
            return null;
        }
                
        return "list.xhtml";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DualListModel<String> getPickData() {
        return pickData;
    }

    public void setPickData(DualListModel<String> pickData) {
        this.pickData = pickData;
    }
    
    private List<String> roles() {
        
        List<String> l = new ArrayList<>();
        l.add( "ROLE_MAGAZYN");
        l.add( "ROLE_PROJEKT");
        l.add( "ROLE_ADMIN");
        l.add( "ROLE_SERWIS");
        l.add( "ROLE_BUDOWA");
        
        return l;
    } 
    
}
