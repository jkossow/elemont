/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.domain.operacje;

import java.math.BigDecimal;
import java.time.Instant;
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
@DiscriminatorValue("podl_2_konca")
public class PodlaczenieDrugiegoKonca extends Operacja {

    @ManyToOne
    @JoinColumn( foreignKey = @ForeignKey(name = "odcinek_fk"))
    private Odcinek odcinek;
    
    private BigDecimal podlaczono;
    
    public PodlaczenieDrugiegoKonca() {};
    
    
    
    public PodlaczenieDrugiegoKonca( Odcinek o ) {
        
        this.odcinek = o;
        this.podlaczono = o.getD().subtract(o.getC()).abs();
    }

    @Override
    public void accept() throws IllegalStatusException, Exception {
        
        // drugi koniec C lib D i stan S5
        if( odcinek.getStatus() !=Status.S5   )
            throw new Exception("zła faza");
        
        odcinek.setPodlaczone( podlaczono );
        Stan stan = odcinek.getKartaMagazynowa().getStanIl();
        
        stan.setIValue( Stan.IL_PODLACZONA, stan.getIValue( Stan.IL_PODLACZONA).add( podlaczono ));
        //stan.setIValue( Stan.IL_POLOZONA,   stan.getIValue( Stan.IL_POLOZONA).subtract( podlaczono ));
        
        setAcceptFlag();
        
        odcinek.setStatus( Status.S6);
        getKartaMagazynowa().trySet( Status.S6 );
    }

    public BigDecimal getPodlaczono() {
        return podlaczono;
    }

    public void setPodlaczono(BigDecimal podlaczono) {
        this.podlaczono = podlaczono;
    }

    
    
    @Override
    public String opis() {
        return "podłączenie drugiego konca " + this.odcinek.getId() + " podłaczono " + podlaczono;
    }

    public Odcinek getOdcinek() {
        return odcinek;
    }

    public void setO(Odcinek odcinek) {
        this.odcinek = odcinek;
    }

    @Override
    public String toString() {
        return "PodlaczenieDrugiegoKonca{" + "odcinek=" + odcinek + ", podlaczono=" + podlaczono + '}';
    }

    
}
