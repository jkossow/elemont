/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author jkossow
 */

@Controller
public class LoginController {
    
    
    @RequestMapping("/login")
    private String login() {
        
        return "login";
    }
    
}
