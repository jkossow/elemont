/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.web.commons;

import java.util.List;
import org.kossowski.elemont.domain.Jm;
import org.kossowski.elemont.repositories.JmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author jkossow
 */
@Controller
public class JmBean {
    
    @Autowired
    private JmRepository jmRepo;
    
    
    public List<Jm> getFindAll() {
        return jmRepo.findAll();
    }
}
