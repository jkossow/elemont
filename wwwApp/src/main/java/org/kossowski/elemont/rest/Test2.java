/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.rest;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jkossow
 */

@RestController
@RequestMapping("test")
@Scope("request")
public class Test2 {
    
    
    @ResponseBody
    @RequestMapping( value="/{user}/2", method = RequestMethod.PUT, produces = "application/json" )
    public String perform() {
        return "bean test2";
    }
    
    
    
}
