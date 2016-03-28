/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author jkossow
 */


@Entity
@Inheritance( strategy = InheritanceType.SINGLE_TABLE )
@DiscriminatorColumn(name = "typ_operacji")
@DiscriminatorValue("o")
public class Operacja implements IOperacja, Serializable {
    
    @Id @GeneratedValue
    private Long id = 0L;
    
    @ManyToOne
    @JoinColumn( name = "kartaMag_id" )
    private KartaMagazynowa kartaMagazynowa;
    
    private Date czasUtworzenia = null;
    private Date czasAkceptacji = null;
    private Boolean accepted = false;
    
    @ManyToOne
    @JoinColumn( foreignKey = @ForeignKey(name = "utworzyl_fk"))
    private User utworzyl;
    
    public Operacja() {
        this.czasUtworzenia = GregorianCalendar.getInstance(TimeZone.getTimeZone("pl_PL")).getTime();
        
    }
    
    public Operacja( User utworzyl, Date czasAkceptacji ) {
        this.utworzyl = utworzyl;
        this.czasAkceptacji = czasAkceptacji;
    }
    
    
    public Operacja( KartaMagazynowa k ) {
        this();
        if( k == null )
            throw new NullPointerException("Karta magazynowa jest null");
        this.kartaMagazynowa = k;
    }
    

    public void setAcceptFlag() {
        setAccepted(true);
        setCzasAkceptacji( GregorianCalendar.getInstance().getTime() );
    }
    
    public String opis() {
        return "operacja baza";
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public KartaMagazynowa getKartaMagazynowa() {
        return kartaMagazynowa;
    }

    public void setKartaMagazynowa(KartaMagazynowa kartaMagazynowa) {
        this.kartaMagazynowa = kartaMagazynowa;
    }

    public Date getCzasUtworzenia() {
        return czasUtworzenia;
    }

    public void setCzasUtworzenia(Date czasUtworzenia) {
        this.czasUtworzenia = czasUtworzenia;
    }

    public Date getCzasAkceptacji() {
        return czasAkceptacji;
    }

    public void setCzasAkceptacji(Date czasAkceptacji) {
        this.czasAkceptacji = czasAkceptacji;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }
    
    
    public String getOpis() {
      return opis();  
    };

    public User getUtworzyl() {
        return utworzyl;
    }

    public void setUtworzyl(User utworzyl) {
        this.utworzyl = utworzyl;
    }

    
    @Override
    public String toString() {
        return "Operacja{" + "id=" + id + '}';
    }

    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Operacja other = (Operacja) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public void accept() throws IllegalStatusException, Exception {
        accepted = true;
        czasAkceptacji = GregorianCalendar.getInstance().getTime();
    }
    
    
    
}
