/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.domain.operacje;

import java.math.BigDecimal;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.kossowski.elemont.domain.IllegalStatusException;
import org.kossowski.elemont.domain.Odcinek;
import org.kossowski.elemont.domain.Operacja;
import org.kossowski.elemont.domain.Stan;
import org.kossowski.elemont.domain.Status;

/**
 *
 * @author jkossow
 */

@Entity
@DiscriminatorValue("montaz_odc")
public class UlozenieOdcinka extends Operacja {

    @ManyToOne
    @JoinColumn( foreignKey = @ForeignKey(name = "odcinek_fk"))
    private Odcinek odcinek;
    
    private BigDecimal polozono;
    
    public UlozenieOdcinka() {};
    
    
    
    public UlozenieOdcinka( Odcinek o ) {
        
        this.odcinek = o;
        this.polozono = o.getA().subtract(o.getB()).abs();
    }

    @Override
    public void accept() throws IllegalStatusException, Exception {
        
        // A i B tylko przy S3
        if( odcinek.getStatus() !=Status.S3   )
            throw new Exception("zła faza");
        
        
        odcinek.setUlozone(polozono);
        Stan stan = odcinek.getKartaMagazynowa().getStanIl();
        
        stan.setIValue(Stan.IL_POLOZONA,  stan.getIValue(Stan.IL_POLOZONA).add(polozono));
        //stan.setIValue( Stan.IL_WYDANA_NA_BUD, stan.getIValue(Stan.IL_WYDANA_NA_BUD).subtract(polozono)  );
        stan.setIValue(Stan.IL_STAN_BIEZ, stan.getIValue(Stan.IL_STAN_BIEZ).subtract(polozono));
        
        setAcceptFlag();
        
        odcinek.setStatus( Status.S4);
        getKartaMagazynowa().trySet( Status.S4 );
    }

    public BigDecimal getPolozono() {
        return polozono;
    }

    public void setPolozono(BigDecimal polozono) {
        this.polozono = polozono;
    }
    
    
    
    
    @Override
    public String opis() {
        return "ułożenie odcinka " + this.odcinek.getId() + " położono " + polozono;
    }

    public Odcinek getOdcinek() {
        return odcinek;
    }

    public void setO(Odcinek odcinek) {
        this.odcinek = odcinek;
    }

    @Override
    public String toString() {
        return "UlozenieOdcinka{" + "odcinek=" + odcinek + ", polozono=" + polozono + '}';
    }

    
    
    
    
    
    
}
