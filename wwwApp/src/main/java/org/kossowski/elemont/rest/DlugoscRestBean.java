/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.rest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.kossowski.elemont.domain.KartaMagazynowa;
import org.kossowski.elemont.domain.Odcinek;
import org.kossowski.elemont.domain.Stan;
import org.kossowski.elemont.domain.User;
import org.kossowski.elemont.repositories.KartaMagazynowaRepository;
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
@RequestMapping("getDlugosc")
@Scope("request")
public class DlugoscRestBean {
    
    @Autowired 
    protected KartaMagazynowaRepository kmRepo;
    
    @Autowired
    protected OdcinekRepository odcRepo;
    
    @ResponseBody
    @RequestMapping( value="/{qr}/{login}", method = RequestMethod.GET, produces = "application/json" )
    public byte[] getDlugosc( @PathVariable(value="qr") Long kmId ) throws Exception {
        
        
        
        //User utworzyl = userRepo.findOne(login);
        KartaMagazynowa km  = kmRepo.findOne(kmId);
        
        BigDecimal ilosc = km.getStanIl().getIValue( Stan.IL_STAN_BIEZ );
        
        return (ilosc+";"+km.getZnacznikBiezacy()).getBytes("utf-8");
    }
}
