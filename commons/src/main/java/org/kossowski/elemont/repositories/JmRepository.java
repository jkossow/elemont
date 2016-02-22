/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.repositories;

import java.util.List;
import org.kossowski.elemont.domain.Jm;
import org.kossowski.elemont.domain.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author jkossow
 */
public interface JmRepository  extends JpaRepository<Jm, Long > {
    
    Jm findFirstBySymbol( String s );
    
}
