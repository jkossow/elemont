/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.sandbox;

import java.io.Serializable;
import java.util.List;
import org.kossowski.elemont.domain.KartaMagazynowa;
import org.kossowski.elemont.domain.Material;
import org.kossowski.elemont.repositories.KartaMagazynowaRepository;
import org.kossowski.elemont.repositories.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 *
 * @author jkossow
 */

@Service
@Scope("view")
public class MatSelBean implements Serializable {
    
    @Autowired
    protected MaterialRepository matRepo; 
    
    @Autowired
    protected KartaMagazynowaRepository kmRepo; 
            
    private KartaMagazynowa selMat;

    public MatSelBean() {
        System.out.println("Mat Sel Bean constructor");
    }

    
    
    
    public KartaMagazynowa getSelMat() {
        return selMat;
    }

    public void setSelMat(KartaMagazynowa selMat) {
        System.out.println("sele "  + selMat );
        this.selMat = selMat;
    }

    public List<KartaMagazynowa> findAll() {
        return kmRepo.findAll();
    }
    
    public void onSelect() {
        System.out.println("On select");
    }
            
    
    
}
