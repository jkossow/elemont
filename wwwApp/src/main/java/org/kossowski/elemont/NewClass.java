/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont;

import java.math.BigDecimal;
import java.util.List;
import org.kossowski.elemont.domain.Jm;
import org.kossowski.elemont.domain.KartaMagazynowa;
import org.kossowski.elemont.domain.Stan;
import org.kossowski.elemont.repositories.JmRepository;
import org.kossowski.elemont.repositories.KartaMagazynowaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;



/**
 *
 * @author jkossow
 */

@Controller
public class NewClass {
    
       
    @Autowired
    protected JmRepository jmRepo;
    
    @Autowired
    protected MaterialService matServ;
    
    @Autowired
    protected KartaMagazynowaRepository kartaRepo;

    private String name = "dddd";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
    public List<Jm> getJm() {
        return jmRepo.findAll();
    }
    
    public List<KartaMagazynowa> getKartaMagazynowa() {
        return kartaRepo.findAll();
    }
    
    public void deleteAllMaterials() {
        matServ.deleteAll();
    }
    
    public void createKartaMaag() {
        KartaMagazynowa k = new KartaMagazynowa();
        
        Stan s = new Stan();
        s.setIValue(Stan.IL_PRZYJETA, new BigDecimal( 15 ) );
        k.setStanIl(s);
        kartaRepo.save(k);
        
    }
}
