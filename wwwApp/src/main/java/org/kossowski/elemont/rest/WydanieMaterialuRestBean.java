/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.rest;

import java.util.Date;
import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
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
@RequestMapping("sendScan")
@Scope("request")
public class WydanieMaterialuRestBean {
    
    @ResponseBody
    @RequestMapping( value = "/{kmId}/{userQR}/{date}/1/{login}", method = RequestMethod.PUT, produces = "application/json")
    public String perform( 
            @PathVariable(value="kmId")  Long kmId,
            @PathVariable(value="userQR") String userQR,
            @PathVariable(value="date") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date,
            @PathVariable(value="login") String login ) {
        
        System.out.println("kmId " + kmId);
        System.out.println("userQR " + userQR);
        System.out.println("date " + date);
        System.out.println("login " + login);
        
        return "wydanieRestBean\n";
    }
    
}
