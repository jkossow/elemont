/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.wprawki;

import org.kossowski.elemont.domain.Projekt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.kossowski.elemont.repositories.ProjektRepository;

/**
 *
 * @author jkossow
 */

@Service
public class ActionClass {
    
    @Autowired
    public ProjektRepository repo;
    
    public void a1() {
        Projekt b = new Projekt();
        b.setNazwa("budowa 2");
        
        repo.save(b);
    }

    public ProjektRepository getRepo() {
        return repo;
    }

    public void setRepo(ProjektRepository repo) {
        this.repo = repo;
    }
    
    
    
}
