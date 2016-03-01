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
@DiscriminatorValue("podl_1_konca")
public class PodlaczeniePierwszegoKonca extends Operacja {

    
    private BigDecimal znacznik;
    // liczba naniesiona na kablu 
    
    @ManyToOne
    @JoinColumn( foreignKey = @ForeignKey(name = "odcinek_fk"))
    private Odcinek odcinek;
    
    
    public PodlaczeniePierwszegoKonca() {};
    
    
    
    public PodlaczeniePierwszegoKonca( Odcinek o ) {
        
        this.odcinek = o;
    
    }

    @Override
    public void accept() throws IllegalStatusException, Exception {
        
        // podłaczenie pierwszego końca tylko przy C lub D tylko przy S4
        if( odcinek.getStatus() != Status.S4   )
            throw new Exception("zła faza");
        
        //nie wplywa na staty ilosciowe ani na zapis podłaczone
        // zmienia tylko status na S5
        Stan stan = odcinek.getKartaMagazynowa().getStanIl();
        
        
        
        setAcceptFlag();
        
        odcinek.setStatus( Status.S5);
        getKartaMagazynowa().trySet( Status.S5 );
    }

    
    
    @Override
    public String opis() {
        return "podłączenie pierwszego konca " + this.odcinek.getId() + " znacznik " + znacznik;
    }

    public Odcinek getOdcinek() {
        return odcinek;
    }

    public void setO(Odcinek odcinek) {
        this.odcinek = odcinek;
    }

    @Override
    public String toString() {
        return "PodlaczeniePierwszegoKonca{" + "odcinek=" + odcinek + '}';
    }

    

    

    
    
    
    
    
    
}
