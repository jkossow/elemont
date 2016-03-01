/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.web.commons;

import java.util.List;
import org.kossowski.elemont.domain.Grupa;
import org.kossowski.elemont.domain.Umowa;
import org.kossowski.elemont.repositories.GrupaRepository;
import org.kossowski.elemont.repositories.UmowaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/**
 *
 * @author jkossow
 */

@Service
public class UmowaBean {
    
    @Autowired
    private UmowaRepository umowaRepo;
    
    
    public List<Umowa> getFindAll() {
        return umowaRepo.findAll();
    }
}
