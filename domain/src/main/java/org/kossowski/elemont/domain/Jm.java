/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author jkossow
 */

@SuppressWarnings("PersistenceUnitPresent")
@Entity
@Table( name = "jm" )
public class Jm implements Serializable {
    
    @Id @GeneratedValue
    private Long id = 0L;
    
    private String symbol;
    private String nazwa;

    public Jm() {
    }

    public Jm(String symbol, String nazwa) {
        this.symbol = symbol;
        this.nazwa = nazwa;
    }
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "Jm{" + "id=" + id + ", symbol=" + symbol + ", nazwa=" + nazwa + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Jm other = (Jm) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
}
