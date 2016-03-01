/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.domain.operacje;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.kossowski.elemont.domain.IllegalStatusException;
import org.kossowski.elemont.domain.Odcinek;
import org.kossowski.elemont.domain.Operacja;
import org.kossowski.elemont.domain.Status;

/**
 *
 * @author jkossow
 */

@Entity
@DiscriminatorValue("nowy_odc")
public class NowyOdcinek  extends Operacja {
    
    @ManyToOne
    @JoinColumn( foreignKey = @ForeignKey(name = "odcinek_fk"))
    private Odcinek odcinek;
    
    
    public NowyOdcinek() {
        this.odcinek = new Odcinek();
    };
    
    public NowyOdcinek( Odcinek odcinek ) {
        this.odcinek = odcinek;
    }
    
    private Set<Status> allowedStates() {
        Set<Status> hs = new HashSet<>();
        
        hs.add(Status.S2);
        hs.add(Status.S3);
        
        return hs;
    }
    @Override
    public void accept() throws IllegalStatusException, Exception {
        
        if(  !allowedStates().contains( getKartaMagazynowa().getStatus()  ))
            throw new IllegalStatusException();
        
        //zmiana statusu
        getKartaMagazynowa().setStatus( Status.S3 );
        odcinek.setStatus( Status.S3 );
        setAcceptFlag();
    }

    @Override
    public String opis() {
        return "NowyOdcinek: " +  odcinek.getId();
    }

    
}
