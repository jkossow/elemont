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
@RequestMapping("skanZawieszki")
public class SkanZawieszekRestBean {

    @ResponseBody
    @RequestMapping( value="/{skanOdcinka}/{znacznik}/{tryb}", method = RequestMethod.POST, produces = "application/json" )
    public Boolean skanZawieszki(
        @PathVariable(value="skanOdcinka") String skanOdcinka,
        @PathVariable(value="znacznik") BigDecimal znacznik,
        @PathVariable(value="tryb") int tryb ) {
            
            System.out.println("skan Odcinka " + skanOdcinka);
            System.out.println("znacznik " + znacznik);
            System.out.println("tryb " + tryb);
   
            
        return false;
    }
}