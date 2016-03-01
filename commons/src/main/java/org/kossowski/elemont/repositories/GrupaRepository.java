/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.repositories;

import org.kossowski.elemont.domain.Grupa;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jkossow
 */
public interface GrupaRepository extends JpaRepository<Grupa, Long> {
    
    Grupa findFirstBySymbol( String s );
}
