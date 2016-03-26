/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.rest;

import java.util.List;
import org.kossowski.elemont.domain.User;
import org.kossowski.elemont.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jkossow
 */

@RestController
@RequestMapping("user")
public class UserRestBean {
    
    @Autowired
    protected UserRepository userRepo;
    
    @ResponseBody
    @RequestMapping( value="/", method = RequestMethod.GET, produces = "application/json")
    public List<User> getAllUsers() {
        
        
        return userRepo.findAll();
    }
    
    @ResponseBody
    @RequestMapping( value="/checkPass/{name}/{password}", method = RequestMethod.GET, produces = "application/json")
    public Boolean checkPass( @PathVariable(value="name") String name,
            @PathVariable(value="password") String password ) {
        
        System.out.println(name);
        System.out.println(password);
        User u = userRepo.findOne(name);
        if( u == null)
            return false;
        return u.getPassword().equals(password);
    }
    
    
    @ResponseBody
    @RequestMapping( value="/{name}", method = RequestMethod.GET, produces = "application/json" )
    public User getUser( @PathVariable(value = "name") String login) {
        
        System.out.println("UserRestBean Name " + login);
        User u = userRepo.findOne(login);
        if( u == null )
            return null;
        System.out.println( u.getNazwisko() );
        u.setPassword("zatarte");
        return u;
        
    }
    
}
