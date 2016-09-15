/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.domain.operacje;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.kossowski.elemont.domain.IllegalStatusException;
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
    @JoinColumn( foreignKey = @ForeignKey(name = "owner_fk"))
    private User owner;
    
    public WydanieNaBudowe() {
        super();
    }
    
    
    
    public WydanieNaBudowe( User utworzyl, Date czasUtworzenia, BigDecimal ilosc, User owner ) {
        super( utworzyl, czasUtworzenia);
        this.ilosc = ilosc;
        this.owner = owner;
        setUtworzyl( utworzyl );
        
    }

    public BigDecimal getIlosc() {
        return ilosc;
    }

    public void setIlosc(BigDecimal ilosc) {
        this.ilosc = ilosc;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    
    
    @Override
    public void accept() throws IllegalStatusException, Exception {
        
        if( getKartaMagazynowa().getStatus() != Status.S1 )
            throw new IllegalStatusException("Niedopuszczalny status partii");
        
        if( ilosc.compareTo( getKartaMagazynowa().getStanIl().getIValue(Stan.IL_W_MAG_GL) ) > 0 )
            throw new Exception("Pobranie ponad stan");
        
        if( getKartaMagazynowa().getProjekt() != null && !getKartaMagazynowa().getProjekt().getZespol().contains( this.getOwner() ))
            throw new Exception("Pracownik nieprzypisany do projektu");
        
        //przypisanie pól
        getKartaMagazynowa().setUtworzyl( getUtworzyl() );
        getKartaMagazynowa().setOwner( getOwner() );
        
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
        return "Wydanie na budowę, Pracownik: " + getOwner().getNazwisko() + " ilosc: " + getIlosc() ;
    }

    
}
