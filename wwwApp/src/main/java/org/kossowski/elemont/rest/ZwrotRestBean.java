/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.rest;

import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;
import org.kossowski.elemont.domain.KartaMagazynowa;
import org.kossowski.elemont.domain.Operacja;
import org.kossowski.elemont.domain.Stan;
import org.kossowski.elemont.domain.User;
import org.kossowski.elemont.domain.operacje.Zwrot;
import org.kossowski.elemont.repositories.KartaMagazynowaRepository;
import org.kossowski.elemont.repositories.OperacjaRepository;
import org.kossowski.elemont.repositories.UserRepository;
import org.kossowski.elemont.utils.JSFUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ZwrotRestBean {
    
    @Autowired
    protected KartaMagazynowaRepository kmRepo;
    
    @Autowired
    protected OperacjaRepository opRepo;
    
    @Autowired
    protected UserRepository userRepo;
    
    @ResponseBody
    @RequestMapping( value = "/{kmId}/{znacznikP}/{znacznikK}/{date}/8/{user}", method = RequestMethod.PUT, produces = "application/json")
    public byte[] wydanie( 
            @PathVariable(value="kmId")  Long kmId,
            @PathVariable(value="znacznikP")  String znacznikP,
            @PathVariable(value="znacznikK")  String znacznikK,
            @PathVariable(value="date") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date,
            @PathVariable(value="user") String user ) throws Exception {
        
        System.out.println("kmId " + kmId);
        System.out.println("znacznikP "  + znacznikP );
        System.out.println("znacznikK" + znacznikK );
        
        KartaMagazynowa km = kmRepo.findOne(kmId);
        User owner = userRepo.findOne(user);
        
        BigDecimal znacznikPoczatkowy = new BigDecimal( znacznikP );
        BigDecimal znacznikKoncowy;
        Boolean znacznikKoncowyDostepny;
        BigDecimal ilosc;
        
        if( znacznikK.equals("null")) {
            znacznikKoncowy = null;
            znacznikKoncowyDostepny = false;
        } else {
            znacznikKoncowy = new BigDecimal(znacznikK);
            znacznikKoncowyDostepny = true;
        }
        
        if( znacznikKoncowyDostepny )
            ilosc = znacznikPoczatkowy.subtract(znacznikKoncowy).abs();
        else
            ilosc = km.getStanIl().getIValue( Stan.IL_STAN_BIEZ );
            
        Operacja o = new Zwrot( owner, date,
            ilosc, znacznikPoczatkowy, znacznikKoncowy, znacznikKoncowyDostepny, owner);
        
        o = opRepo.save(o);
        km.addOperation(o);
        
        try {
            o.accept();
            
        } catch ( Exception e) {
            km.removeOperation(o);
            opRepo.delete(o);
            
            return e.getMessage().getBytes("utf-8");
        };
        km = kmRepo.save(km);
        
        return "ok".getBytes("utf-8");
        
    }
    
}
