/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.domain.operacje;

import java.math.BigDecimal;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
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
@DiscriminatorValue("zwrot_na_mag")
public class Zwrot extends Operacja {
    
    private BigDecimal ilosc;
    
    public Zwrot(){};
    
    public Zwrot( BigDecimal ilosc, User user ) {
        this.ilosc = ilosc;
        setUtworzyl(user );
    }

    
    @Override
    public void accept() throws IllegalStatusException, Exception {
        
        if( getKartaMagazynowa().getStatus() != Status.S8 )
            throw new IllegalStatusException("niedopuszczalny status partii");
        
        if( ilosc.compareTo( getKartaMagazynowa().getStanIl().getIValue(Stan.IL_STAN_BIEZ) ) > 0 )
            throw new Exception("Zwrot ponad stan");
        
        //przypisanie pól
               
        Stan s = getKartaMagazynowa().getStanIl();
        
        s.setIValue( Stan.IL_W_MAG_GL, s.getIValue(Stan.IL_W_MAG_GL).add(ilosc)  );
        s.setIValue( Stan.IL_WYDANA_NA_BUD, s.getIValue(Stan.IL_WYDANA_NA_BUD).subtract(ilosc));
        s.setIValue( Stan.IL_STAN_BIEZ, s.getIValue(Stan.IL_STAN_BIEZ).subtract(ilosc));
        
        
        //zmiana statusu
        getKartaMagazynowa().setStatus( Status.S9 );
        setAcceptFlag();
    }

    @Override
    public String opis() {
        return "Zwrot z budowy, ilosc: " + getIlosc() ;
    }
    
    
    public BigDecimal getIlosc() {
        return ilosc;
    }

    public void setIlosc(BigDecimal ilosc) {
        this.ilosc = ilosc;
    }
    
    
    
}
