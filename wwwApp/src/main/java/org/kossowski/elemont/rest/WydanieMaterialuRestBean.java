/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.rest;

import java.math.BigDecimal;
import java.util.Date;
import org.kossowski.elemont.domain.KartaMagazynowa;
import org.kossowski.elemont.domain.Operacja;
import org.kossowski.elemont.domain.Stan;
import org.kossowski.elemont.domain.User;
import org.kossowski.elemont.domain.operacje.WydanieNaBudowe;
import org.kossowski.elemont.repositories.KartaMagazynowaRepository;
import org.kossowski.elemont.repositories.OperacjaRepository;
import org.kossowski.elemont.repositories.UserRepository;
import org.kossowski.elemont.utils.JSFUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @Autowired
    protected UserRepository userRepo;
    
    @Autowired
    protected KartaMagazynowaRepository kmRepo;
    
    @Autowired
    protected OperacjaRepository opRepo;
    
    @ResponseBody
    @RequestMapping( value = "/{kmId}/{userQR}/{date}/1/{login}", method = RequestMethod.PUT, produces = "application/json")
    public byte[] perform( 
            @PathVariable(value="kmId")  Long kmId,
            @PathVariable(value="userQR") String userQR,
            @PathVariable(value="date") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date,
            @PathVariable(value="login") String login ) throws Exception {
        
        System.out.println("kmId " + kmId);
        System.out.println("userQR " + userQR);
        System.out.println("date " + date);
        System.out.println("login " + login);
        
        User utworzyl = userRepo.findOne(login);
        User owner = userRepo.findFirstByKodQR( userQR );
        KartaMagazynowa km  = kmRepo.findOne(kmId);
        
        if( utworzyl == null ) {
            return "Brak utykownika".getBytes("utf-8");
        }
        
        if( owner == null ) {
            return "Niepoprawny QR Kod".getBytes("utf-8");
        }
        
        if( km == null ) {
            return "Nie ma takiej partii magazynowej".getBytes("utf-8");
        }
        
        BigDecimal ilosc =  km.getStanIl().getIValue( Stan.IL_W_MAG_GL );
        
        Operacja o = new WydanieNaBudowe( utworzyl, date, ilosc, owner );
        
        o = opRepo.save(o);
        km.addOperation(o);
        o.setKartaMagazynowa(km);
        
        try {
            o.accept();
            
        } catch ( Exception e) {
            km.removeOperation(o);
            opRepo.delete(o);
            
            return e.getMessage().getBytes("utf-8");           
        };
        
        kmRepo.save(km);
        
        return "ok".getBytes("utf-8");
    }
    
}
