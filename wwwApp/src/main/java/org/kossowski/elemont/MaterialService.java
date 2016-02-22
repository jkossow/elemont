/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont;

import org.kossowski.elemont.repositories.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jkossow
 */

@Service
public class MaterialService {
    
    @Autowired
    protected MaterialRepository materialRepo;
    
    
    public void deleteAll(){
        materialRepo.deleteAllInBatch();
    }
     
    
}
