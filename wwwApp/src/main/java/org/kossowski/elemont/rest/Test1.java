/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class Test1 {
    
    
    @RequestMapping( value="/", method = RequestMethod.GET, produces = "application/json" )
    public @ResponseBody byte[] perform( HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        System.out.println( request.getQueryString() );
        
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        return "bean test1 złafaza ęóąśłżźćńĘÓĄŚŁŻŹĆŃ".getBytes("utf-8");
    }
    
}
