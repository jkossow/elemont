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
import org.kossowski.elemont.domain.Odcinek;
import org.kossowski.elemont.domain.Operacja;
import org.kossowski.elemont.domain.Status;
import org.kossowski.elemont.domain.User;

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
    
    @ManyToOne
    @JoinColumn( foreignKey = @ForeignKey(name = "owner_fk"))
    private User owner;
    
    private BigDecimal znacznikPoczatkowy;
    
    
    public NowyOdcinek() {}; 
    
    public NowyOdcinek( User u) {
        this.odcinek = new Odcinek();
        setUtworzyl(u);
    };
    
    public NowyOdcinek( User utworzyl, Date czasUtworzenia, 
            Odcinek odcinek, BigDecimal znacznikPoczatkowy, User owner ) {
        
        super( utworzyl, czasUtworzenia);
        this.odcinek = odcinek;
        this.owner = owner;
        this.znacznikPoczatkowy = znacznikPoczatkowy;
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
            throw new IllegalStatusException("niedopuszczalny status partii");
        
        if( !getKartaMagazynowa().getProjekt().getZespol().contains( this.getOwner() ))
            throw new Exception("Pracownik nieprzypisany do projektu");
        
        //nazwanie odcinka
        int i = getKartaMagazynowa().getPrzyrostekNazwyOdcinka();
        odcinek.setNazwa(  getKartaMagazynowa().getId() + ":" + i);
        i++;
        getKartaMagazynowa().setPrzyrostekNazwyOdcinka(i);

        //przypisanie pol
        odcinek.setOwner(owner);
        odcinek.setZnacznikPoczatkowy(znacznikPoczatkowy);
        
        //zmiana statusu
        
        // nie zmianiamy znacznika początowego karty 
        //getKartaMagazynowa().ustawZnacznikBiezacy(znacznikPoczatkowy);
        getKartaMagazynowa().setStatus( Status.S3 );
        odcinek.setStatus( Status.S3 );
        setAcceptFlag();
    }

    @Override
    public String opis() {
        return "NowyOdcinek: " +  odcinek.getId();
    }

    public Odcinek getOdcinek() {
        return odcinek;
    }

    public void setOdcinek(Odcinek odcinek) {
        this.odcinek = odcinek;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public BigDecimal getZnacznikPoczatkowy() {
        return znacznikPoczatkowy;
    }

    public void setZnacznikPoczatkowy(BigDecimal znacznikPoczatkowy) {
        this.znacznikPoczatkowy = znacznikPoczatkowy;
    }

    
}
