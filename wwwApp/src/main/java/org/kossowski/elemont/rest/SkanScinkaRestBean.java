/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.rest;

import java.math.BigDecimal;
import org.kossowski.elemont.domain.Odcinek;
import org.kossowski.elemont.repositories.OdcinekRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
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
@RequestMapping("getScinka")
@Scope("request")
public class SkanScinkaRestBean {

    @Autowired
    protected OdcinekRepository odcRepo;
    
    @ResponseBody
    @RequestMapping( value="/{qrkod}/{tryb}/{user}", method = RequestMethod.GET, produces = "application/json" )
    public String getScinka( @PathVariable(value="qrkod") String qrkod ) {
        //System.out.println("przed koretą " + qrkod);
        //qrkod = qrkod.substring(qrkod.length() -3 );
        //System.out.println( "po korekcie " + qrkod);
        String id1 = qrkod.substring(0, qrkod.length() - 2 );
        String selector = qrkod.substring( qrkod.length() - 2);

        System.out.println( id1 );
        System.out.println( selector );

        Odcinek o = odcRepo.findOne( new Long(id1));
        if( o == null )
            return "0";
        
        BigDecimal dlScinka;
        if( selector.equals("A1") )
            dlScinka = o.spodzScinekA1();
        else
            dlScinka = o.spodzScinekB1();
        System.out.println( dlScinka );
        return dlScinka.toString();
    }
}
