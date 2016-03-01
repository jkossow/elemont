/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.repositories;

import org.kossowski.elemont.domain.Jm;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jkossow
 */
public interface JmRepository  extends JpaRepository<Jm, Long > {
    
    Jm findFirstBySymbol( String s );
    
}
