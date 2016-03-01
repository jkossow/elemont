/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.rest;

import java.util.HashMap;
import java.util.Map;
import org.kossowski.elemont.domain.Jm;
import org.kossowski.elemont.domain.User;
import org.kossowski.elemont.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jkossow
 */

//@Controller
@RestController
@RequestMapping( "jm")
public class Rest1 {
    
    @Autowired
    protected UserRepository userRepo;
    
    @RequestMapping( value="/{name}", method=RequestMethod.GET, produces = "application/json" )
    @ResponseBody
    public User r1( @PathVariable(value="name") String name) {
        System.out.println("Name " + name );
        User u = userRepo.findOne(name);
        return u;
        //return "hello";
    }
    
    
    @RequestMapping( value="xml", method=RequestMethod.GET, produces = "application/xml" )
    @ResponseBody
    public Jm r2() {
        Jm jm = new Jm();
        jm.setId(Long.MIN_VALUE);
        jm.setNazwa("ffff");
        jm.setSymbol("gggg");
        System.out.println("jestem w  kontro xml");
        Map<Integer,Jm> map = new HashMap<Integer, Jm>();
        map.put( 9999, jm);
        return jm;
    }
    
}
