/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.repositories;

import java.util.List;
import java.io.Serializable;
import org.kossowski.elemont.domain.Projekt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jkossow
 */

@Repository
public interface ProjektRepository extends JpaRepository<Projekt, Serializable> {
    
    public List<Projekt> findBySymbol( String s );
    public Projekt findFirstBySymbol( String s );
    
}
