/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.web.commons;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import org.kossowski.elemont.domain.Grupa;
import org.kossowski.elemont.domain.KartaMagazynowa;
import org.kossowski.elemont.domain.Status;
import org.kossowski.elemont.qr.Etykieta;
import org.kossowski.elemont.qr.EtykietaQR1;
import org.kossowski.elemont.qr.EtykietaQR2;
import org.kossowski.elemont.repositories.GrupaRepository;
import org.kossowski.elemont.repositories.KartaMagazynowaRepository;
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
public class KartaMagazynowaBean implements Serializable {
    
    @Autowired
    private KartaMagazynowaRepository kartaRepo;
    
    private KartaMagazynowa selected;
    
    public KartaMagazynowaBean() {
        
    }
    
    public List<KartaMagazynowa> getFindAll() {
        return kartaRepo.findAll();
    }
    
    public List<KartaMagazynowa> getFindAllNaBudowie() {
        return kartaRepo.findAllByStatusIn( Status.naBudowie() );
    }

    public KartaMagazynowa getSelected() {
        return selected;
    }

    public void setSelected(KartaMagazynowa selected) {
        this.selected = selected;
    }

    
    public void onSelect() {
        System.out.println("On select " + selected.getId() );
    }
   
    
    
    
    
}
