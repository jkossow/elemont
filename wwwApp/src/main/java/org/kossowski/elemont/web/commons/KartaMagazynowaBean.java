/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.web.commons;

import java.util.ArrayList;
import java.util.List;
import org.kossowski.elemont.domain.Grupa;
import org.kossowski.elemont.domain.KartaMagazynowa;
import org.kossowski.elemont.domain.Status;
import org.kossowski.elemont.repositories.GrupaRepository;
import org.kossowski.elemont.repositories.KartaMagazynowaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author jkossow
 */

@Controller
public class KartaMagazynowaBean {
    
    @Autowired
    private KartaMagazynowaRepository kartaRepo;
    
    private KartaMagazynowa selectedKarta;
    
    public KartaMagazynowaBean() {
        
    }
    
    public List<KartaMagazynowa> getFindAll() {
        return kartaRepo.findAll();
    }
    
    public List<KartaMagazynowa> getFindAllNaBudowie() {
        return kartaRepo.findAllByStatusIn( Status.naBudowie() );
    }

    public KartaMagazynowa getSelectedKarta() {
        return selectedKarta;
    }

    public void setSelectedKarta(KartaMagazynowa selectedKarta) {
        System.out.println("Selected karta " + selectedKarta.getId());
        this.selectedKarta = selectedKarta;
    }

   
    
    
    
    
}
