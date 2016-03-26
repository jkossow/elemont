/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.rest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
@RequestMapping("getDlugosc")
@Scope("request")
public class DlugoscRestBean {
    
    @ResponseBody
    @RequestMapping( value="/{q1}/{login}", method = RequestMethod.GET, produces = "application/json" )
    public String getDlugosc() {
        
        
        return "500;45";
    }
}
