/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.domain.operacje;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
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
@DiscriminatorValue("zwrot_na_mag")
public class Zwrot extends Operacja {
    
    private BigDecimal ilosc;
    
    private BigDecimal znacznikPoczatkowy;
    private BigDecimal znacznikKoncowy;
    private Boolean znacznikKoncowyDostepny;
    
    @ManyToOne
    @JoinColumn( foreignKey = @ForeignKey(name = "owner_fk"))
    private User owner;
    
    
    
    public Zwrot(){};
    
    public Zwrot( User utworzyl, Date czasUtworzenia, BigDecimal ilosc, 
            BigDecimal znacznikPoczatkowy, BigDecimal znacznikKoncowy,
            Boolean znacznikKoncowyDostepny, User owner ) {
        
        super(utworzyl, czasUtworzenia);
        this.ilosc = ilosc;
        this.znacznikPoczatkowy = znacznikPoczatkowy;
        this.znacznikKoncowy = znacznikKoncowy;
        this.znacznikKoncowyDostepny = znacznikKoncowyDostepny;
        this.owner = owner;
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
            throw new IllegalStatusException("Niedopuszczalny status partii");
        
        if( ilosc.compareTo( getKartaMagazynowa().getStanIl().getIValue(Stan.IL_STAN_BIEZ) ) > 0 )
            throw new Exception("Zwrot ponad stan");
        
        //przypisanie pól
        
               
        Stan s = getKartaMagazynowa().getStanIl();
        
        s.setIValue( Stan.IL_W_MAG_GL, s.getIValue(Stan.IL_W_MAG_GL).add(ilosc)  );
        s.setIValue( Stan.IL_WYDANA_NA_BUD, s.getIValue(Stan.IL_WYDANA_NA_BUD).subtract(ilosc));
        s.setIValue( Stan.IL_STAN_BIEZ, s.getIValue(Stan.IL_STAN_BIEZ).subtract(ilosc));
        
        
        //zmiana statusu
        
        getKartaMagazynowa().setOwner(null);
        getKartaMagazynowa().setStatus( Status.S1 );
        getKartaMagazynowa().ustawZnacznikBiezacy(znacznikPoczatkowy);
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
