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
import org.kossowski.elemont.domain.KartaMagazynowa;
import org.kossowski.elemont.domain.Operacja;
import org.kossowski.elemont.domain.Stan;
import org.kossowski.elemont.domain.Status;
import org.kossowski.elemont.domain.User;

/**
 *
 * @author jkossow
 */

@Entity
@DiscriminatorValue("wyd_na_bud")
public class WydanieNaBudowe extends Operacja { 
    
    private BigDecimal ilosc;
    
    @ManyToOne
    @JoinColumn( foreignKey = @ForeignKey(name = "user_fk"))
    private User user;

    public WydanieNaBudowe() {
    }
    
    
    
    public WydanieNaBudowe( User  user, BigDecimal ilosc ) {
        this.ilosc = ilosc;
        this.user = user;
    }

    public BigDecimal getIlosc() {
        return ilosc;
    }

    public void setIlosc(BigDecimal ilosc) {
        this.ilosc = ilosc;
    }

    
    @Override
    public void accept() throws IllegalStatusException, Exception {
        
        if( getKartaMagazynowa().getStatus() != Status.S1 )
            throw new IllegalStatusException();
        
        if( ilosc.compareTo( getKartaMagazynowa().getStanIl().getIValue(Stan.IL_W_MAG_GL) ) > 0 )
            throw new Exception("za dużo w pobraniu");
        
        //dopisanie do kolekcji operacji
        //getKartaMagazynowa().getOperacje().add( this );
        
        //przypisanie pól
        getKartaMagazynowa().setUser(this.user);
        
        Stan s = getKartaMagazynowa().getStanIl();
        
        s.setIValue( Stan.IL_W_MAG_GL, s.getIValue(Stan.IL_W_MAG_GL).subtract(ilosc)  );
        s.setIValue( Stan.IL_WYDANA_NA_BUD, s.getIValue(Stan.IL_WYDANA_NA_BUD).add(ilosc));
        s.setIValue( Stan.IL_STAN_BIEZ, s.getIValue(Stan.IL_STAN_BIEZ).add(ilosc));
        
        
        //zmiana statusu
        getKartaMagazynowa().setStatus( Status.S2 );
        setAcceptFlag();
    }

    @Override
    public String opis() {
        return "Wydanie na budowę user " + getUser() + " ilosc " + getIlosc() ;
    }

    
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
    
}