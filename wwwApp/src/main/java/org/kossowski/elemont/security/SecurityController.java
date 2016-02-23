/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.security;

import java.io.Serializable;
import java.security.Principal;
import org.kossowski.elemont.domain.User;
import org.kossowski.elemont.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
 
@Controller
@Scope("session")
public class SecurityController implements  Serializable {
 
    @Autowired
    protected UserRepository userRepo;
    
    
    protected User user = null;
    
    public String getUserName() {
        
        if( user == null ) {
            String s = SecurityContextHolder.getContext().getAuthentication().getName();
            user = userRepo.findOne(s);
        }         
        return user.getLogin();
        
    }
    
    public String getNazwisko() {
        if( user == null ) {
            String s = SecurityContextHolder.getContext().getAuthentication().getName();
            user = userRepo.findOne(s);
        }         
        return user.getNazwisko();
    }
    
    public String getImie() {
        if( user == null ) {
            String s = SecurityContextHolder.getContext().getAuthentication().getName();
            user = userRepo.findOne(s);
        }         
        return user.getImie();
    }
    
    
    
}