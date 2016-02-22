/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.services;

import org.kossowski.elemont.domain.Stanowisko;
import org.kossowski.elemont.repositories.JmRepository;
import org.kossowski.elemont.repositories.StanowiskoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jkossow
 */

@Service
public class InitDataBean {
    
    @Autowired
    private JmRepository jmRepo;
    
    @Autowired
    private StanowiskoRepository stanowiskoRepo;
    
    
    public void init() {
        System.out.println( "Jednostki miar: " + jmRepo.count() );
        System.out.println( "Stanowiska: " + stanowiskoRepo.count() );
    }
    
}
