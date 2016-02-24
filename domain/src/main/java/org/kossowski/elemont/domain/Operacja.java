/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
    
    private Date creationTime = null;
    private Date acceptionTime = null;
    private Boolean accepted = false;

    
    public Operacja() {
        this.creationTime = GregorianCalendar.getInstance().getTime();
        
    }
    
    public Operacja( KartaMagazynowa k ) {
        this();
        if( k == null )
            throw new NullPointerException("Karta magazynowa jest null");
        this.kartaMagazynowa = k;
    }
    
    public void setAcceptFlag() {
        setAccepted(true);
        setAcceptionTime( GregorianCalendar.getInstance().getTime() );
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

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getAcceptionTime() {
        return acceptionTime;
    }

    public void setAcceptionTime(Date acceptionTime) {
        this.acceptionTime = acceptionTime;
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
        acceptionTime = GregorianCalendar.getInstance().getTime();
    }
    
    
    
}
