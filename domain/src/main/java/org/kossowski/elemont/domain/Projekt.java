/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 *
 * @author jkossow
 */
@Entity
public class Projekt implements Serializable {
    
    @Id @GeneratedValue
    private Long id = 0L;
    
    private String symbol;
    
    private String nazwa;
    
    // foreignKey = @ForeignKey(name = "operacje_fk")
    
    @ManyToMany
    @JoinTable( name="PROJ_ZESP",  
                joinColumns = { @JoinColumn( name="proj_id", unique = false )},
                inverseJoinColumns = { @JoinColumn( name="user_id", unique = false ) })
    private List<User> zespol = new ArrayList<User>();
    
    public Projekt() {};

    public Projekt(String symbol, String nazwa) {
        this.symbol = symbol;
        this.nazwa = nazwa;
    }
    
    public Projekt(Long id, String symbol, String nazwa) {
        this.id = id;
        this.symbol = symbol;
        this.nazwa = nazwa;
    }
    
    
    public boolean isUserInProject( User u ) {
        return zespol.contains(u);
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

    public List<User> getZespol() {
        return zespol;
    }

    public void setZespol(List<User> zespol) {
        this.zespol = zespol;
    }

    
    
    @Override
    public String toString() {
        return "Projekt{" + "id=" + id + ", symbol=" + symbol + ", nazwa=" + nazwa + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.id);
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
        final Projekt other = (Projekt) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
}
