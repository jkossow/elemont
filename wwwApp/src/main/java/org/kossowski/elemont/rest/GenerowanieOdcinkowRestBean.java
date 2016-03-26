/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.rest;

import java.math.BigDecimal;
import java.util.Date;
import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.BindingResult;
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
public class GenerowanieOdcinkowRestBean {
    
    @ResponseBody
    @RequestMapping( value = "/{kmId}/{znacznikPoczatkowy}/{iloscOdcinkow}/{date}/9/{login}", method = RequestMethod.PUT, produces = "application/json")
    public String perform( 
            @PathVariable(value="kmId")  Long kmId,
            @PathVariable(value="znacznikPoczatkowy") BigDecimal znacznikPoczatkowy,
            @PathVariable(value="iloscOdcinkow") BigDecimal iloscOdcinkow,
            @PathVariable(value="date") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date,
            @PathVariable(value="login") String login ) {
        
        System.out.println("kmId " + kmId);
        System.out.println("znacznikPoczatkowy " + znacznikPoczatkowy);
        System.out.println("iloscOdcinkow " + iloscOdcinkow);
        System.out.println("date " + date);
        System.out.println("login " + login);
        
        return "geerowanieOdcinkow\n";
    }
    
}
