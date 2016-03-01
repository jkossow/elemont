/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.rest;

import java.math.BigDecimal;
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
@RequestMapping("skanScinek")
public class SkanScinekRestBean {

    @ResponseBody
    @RequestMapping( value="/{skanOdcinka}/{tryb}", method = RequestMethod.POST, produces = "application/json" )
    public Boolean skanScinek(
        @PathVariable(value="skanOdcinka") String skanOdcinka,
        @PathVariable(value="tryb") int tryb ) {
            
            System.out.println("skan Odcinka " + skanOdcinka);
            System.out.println("tryb " + tryb);
   
            
        return false;
    }
}