/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.rest;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.kossowski.elemont.domain.Odcinek;
import org.kossowski.elemont.repositories.OdcinekRepository;
import org.kossowski.elemont.repositories.UserRepository;
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
@RequestMapping("getHistoria")
@Scope("request")
public class HistoriaRestBean {
    
    @Autowired
    protected OdcinekRepository odcRepo;
    
    @Autowired
    protected UserRepository userRepo;
    
    @ResponseBody
    @RequestMapping( value="/{login}", method = RequestMethod.GET, produces = "application/json" )
    public List<HistoriaItem> getHistoria( @PathVariable("login") String login ) {
        
        ArrayList<HistoriaItem> historia = new ArrayList<>();
        //historia.add(new HistoriaItem( 1L,  123L, new BigDecimal("50"), new BigDecimal("48"), new BigDecimal("2") ));
        //historia.add(new HistoriaItem( 2L,  234L, new BigDecimal("34"), null, null ));
        //historia.add(new HistoriaItem( 3L,  545L, new BigDecimal("33"), null, null ));
        
        long i = 1;
        List<Odcinek> l = odcRepo.findByOwner( userRepo.findOne( login ));
        for( Odcinek o : l) {
            historia.add( new HistoriaItem( i, o.getId(), o.getUlozone(), 
                    o.getPodlaczone() , o.sumaScinek() ));
            i++;
         }
        
        return historia;
    }
}
