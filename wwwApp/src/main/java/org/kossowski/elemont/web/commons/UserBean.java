/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.web.commons;

import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import org.kossowski.elemont.domain.Grupa;
import org.kossowski.elemont.domain.Umowa;
import org.kossowski.elemont.domain.User;
import org.kossowski.elemont.repositories.GrupaRepository;
import org.kossowski.elemont.repositories.UmowaRepository;
import org.kossowski.elemont.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author jkossow
 */

@Controller
@Scope("request")
public class UserBean {
    
    @Autowired
    private UserRepository userRepo;
    
    private User user = new User();
    
    public List<User> getFindAll() {
        return userRepo.findAll();
    }
    
    public String edit() {
        
        System.out.println("edit");
        
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> pm = fc.getExternalContext().getRequestParameterMap();
        
        String login = pm.get("login");
        
        user = userRepo.findOne(login);
        return "edit.xhtml";
        //return "/commons/user/edit.xhtml";
    }
    
    public String save() {
        System.out.println("user qr" + user.getKodQR() );
        System.out.println("user login" + user.getLogin() );
        userRepo.save( user );
        return "list.xhtml";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
    
}
