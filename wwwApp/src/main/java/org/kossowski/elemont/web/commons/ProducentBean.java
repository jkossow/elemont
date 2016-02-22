/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.web.commons;

import java.util.List;
import org.kossowski.elemont.domain.Grupa;
import org.kossowski.elemont.domain.Producent;
import org.kossowski.elemont.repositories.GrupaRepository;
import org.kossowski.elemont.repositories.ProducentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author jkossow
 */

@Controller
public class ProducentBean {
    
    @Autowired
    private ProducentRepository producentRepo;
    
    
    public List<Producent> getFindAll() {
        return producentRepo.findAll();
    }
}
