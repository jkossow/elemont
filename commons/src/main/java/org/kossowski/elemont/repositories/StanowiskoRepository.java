/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.repositories;

import org.kossowski.elemont.domain.Stanowisko;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jkossow
 */
public interface StanowiskoRepository extends JpaRepository<Stanowisko, Long>{
    
}
