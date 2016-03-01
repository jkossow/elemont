/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.web.operacje;

import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import org.kossowski.elemont.domain.KartaMagazynowa;
import org.kossowski.elemont.domain.Material;
import org.kossowski.elemont.repositories.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/**
 *
 * @author jkossow
 */

@Service
@Scope("request")
public class Test1Bean {
    
    @Autowired
    protected MaterialRepository matRepo;
    
    private KartaMagazynowa km = new KartaMagazynowa();

    public KartaMagazynowa getKm() {
        return km;
    }

    public void setKm(KartaMagazynowa km) {
        this.km = km;
    }
    
    
    public List<Material> getMaterials() {
        return matRepo.findAll();
    }
    
    public List<SelectItem> getMaterials1 () {
        
        List<SelectItem> l = new ArrayList<>();
        
        for( Material m : getMaterials() ) {
            SelectItem si = new SelectItem( m, m.getNazwa() );
            l.add( si );
        };
        l.add( new SelectItem( new Material() , "---", "", false, false, true ));
        
        return l;
    }
    
    public String save() {
        System.out.println( "mat:" + km.getMaterial() );
        return "";
    }
    
    public void listen() {
        System.out.println( km.getMaterial().getNazwa() );
    }
    
}
