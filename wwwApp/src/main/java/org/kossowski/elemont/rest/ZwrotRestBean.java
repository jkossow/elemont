/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.rest;

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
@RequestMapping("zwrot")
public class ZwrotRestBean {
    
    @ResponseBody
    @RequestMapping( value = "/{kmId}/{tryb}", method = RequestMethod.POST, produces = "application/json")
    public Boolean wydanie( 
            @PathVariable(value="kmId")  Long kmId,
            @PathVariable( value="tryb") int tryb ) {
        
        System.out.println("kmId " + kmId);
        System.out.println("tryb " + tryb);
        
        return false;
    }
    
}
