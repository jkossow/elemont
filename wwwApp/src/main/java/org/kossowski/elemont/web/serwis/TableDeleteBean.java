/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.web.serwis;

import org.kossowski.elemont.domain.KartaMagazynowa;
import org.kossowski.elemont.domain.User;
import org.kossowski.elemont.repositories.GrupaRepository;
import org.kossowski.elemont.repositories.JmRepository;
import org.kossowski.elemont.repositories.KartaMagazynowaRepository;
import org.kossowski.elemont.repositories.MaterialRepository;
import org.kossowski.elemont.repositories.ProducentRepository;
import org.kossowski.elemont.repositories.ProjektRepository;
import org.kossowski.elemont.repositories.StanowiskoRepository;
import org.kossowski.elemont.repositories.UmowaRepository;
import org.kossowski.elemont.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/**
 *
 * @author jkossow
 */

@Service
public class TableDeleteBean {
    
    @Autowired
    private JmRepository jmRepo;
    
    @Autowired
    private GrupaRepository grupaRepo;
    
    @Autowired
    private MaterialRepository matRepo;
    
    @Autowired
    private ProducentRepository prodRepo;
    
    @Autowired
    private KartaMagazynowaRepository kartaRepo;
    
    @Autowired
    private StanowiskoRepository stanRepo;
    
    @Autowired
    private UmowaRepository umowaRepo;
    
    @Autowired 
    private ProjektRepository projektRepo;
    
    @Autowired 
    private UserRepository userRepo;
 
    
    public void deleteData(){
        kartaRepo.deleteAll();
        
        stanRepo.deleteAll();
        matRepo.deleteAll();
        prodRepo.deleteAll();
        grupaRepo.deleteAll();
        jmRepo.deleteAll();
        umowaRepo.deleteAll();
        projektRepo.deleteAll();
        userRepo.deleteAll();
        //initUser();
        
    }
    
    public void deleteKm() {
        kartaRepo.deleteAll();
    }
    
    private void initUser() {
        User u;
        
        u = new User("jkossow", "Kossowski", "Janusz");
        u.setPassword("aaaaa");
        u.getRole().add("ROLE_ADMIN");
        u.getRole().add("ROLE_BUDOWA");
        u.getRole().add("ROLE_MAGAZYN");
        u.getRole().add("ROLE_SERWIS");
        u.setKodQR("001");
        userRepo.save( u );
        
        u = new User("jakubk", "Kwiatkowski", "Jakub");
        u.setKodQR("002");
        u.getRole().add("ROLE_ADMIN");
        u.getRole().add("ROLE_BUDOWA");
        u.getRole().add("ROLE_MAGAZYN");
        u.getRole().add("ROLE_ADMIN");
        userRepo.save( u );
        
        /*
        u = new User("golonm", "Golonka", "łukasz");
        u.setPassword("aaaaa");
        u.getRole().add("ROLE_MAGAZYN");
        u.setKodQR("003");
        userRepo.save( u );
        
        u = new User("golonb", "Golonka", "łukasz");
        u.setPassword("aaaaa");
        u.getRole().add("ROLE_BUDOWA");
        u.setKodQR("004");
        userRepo.save( u );
        
        u = new User("golonadmin", "Golonka", "łukasz");
        u.setPassword("aaaaa");
        u.getRole().add("ROLE_ADMIN");
        u.setKodQR("005");
        userRepo.save( u );*/
    }
    
}
