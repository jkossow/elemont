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
import org.kossowski.elemont.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author jkossow
 */
public interface KartaMagazynowaRepository extends JpaRepository< KartaMagazynowa, Long >{
    
    public List<KartaMagazynowa> findAllByStatus( Status s);
    public List<KartaMagazynowa> findAllByStatusIn( Collection<Status> sc );
    
    //@Query("select distinct o.kartaMagazynowa from Odcinek o, KartaMagazynowa k where o.owner = :u or k.owner = :u")
    
    @Query("select distinct k from KartaMagazynowa k left join k.odcinki o "
            + " where o.owner= :user or k.owner= :user" )
    public List<KartaMagazynowa> findByKartaIOdcinki(@Param("user") User u);
}
