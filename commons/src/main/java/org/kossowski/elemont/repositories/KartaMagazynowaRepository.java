/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.repositories;

import java.util.Collection;
import java.util.List;
import org.kossowski.elemont.domain.KartaMagazynowa;
import org.kossowski.elemont.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jkossow
 */
public interface KartaMagazynowaRepository extends JpaRepository< KartaMagazynowa, Long >{
    
    public List<KartaMagazynowa> findAllByStatus( Status s);
    public List<KartaMagazynowa> findAllByStatusIn( Collection<Status> sc );
    
}
