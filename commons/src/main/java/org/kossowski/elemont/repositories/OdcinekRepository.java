/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.repositories;

import java.io.Serializable;
import org.kossowski.elemont.domain.Odcinek;
import org.kossowski.elemont.domain.Umowa;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jkossow
 */
public interface OdcinekRepository extends JpaRepository<Odcinek, Long>{
    
}
