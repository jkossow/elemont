/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author jkossow
 */

@Entity
@Table( name = "materialy" )
public class Material implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id = 0L;
    
    private String indeks;
    private String nazwa;
    
    @ManyToOne( cascade = CascadeType.ALL )
    @JoinColumn( foreignKey =  @ForeignKey(name = "jm_fk"))
    private Jm jm = new Jm();
    
    @ManyToOne
    @JoinColumn( foreignKey =  @ForeignKey(name = "grupa_fk"))
    private Grupa grupa = new Grupa();

    public Material() {
        this.id = new Long("0");
    }

    public Material(String indeks, String nazwa) {
        this.indeks = indeks;
        this.nazwa = nazwa;
    }

    public Material(String indeks, String nazwa, Jm jm) {
        this.indeks = indeks;
        this.nazwa = nazwa;
        this.jm = jm;
    }

    public Material(String indeks, String nazwa, Jm jm, Grupa grupa) {
        this.indeks = indeks;
        this.nazwa = nazwa;
        this.jm = jm;
        this.grupa = grupa;
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

    public Jm getJm() {
        return jm;
    }

    public void setJm(Jm jm) {
        this.jm = jm;
    }

    public Grupa getGrupa() {
        return grupa;
    }

    public void setGrupa(Grupa grupa) {
        this.grupa = grupa;
    }

    public String getIndeks() {
        return indeks;
    }

    public void setIndeks(String indeks) {
        this.indeks = indeks;
    }

    @Override
    public String toString() {
        return "Material{" + "id=" + id + ", indeks=" + indeks + ", nazwa=" + nazwa + ", jm=" + jm + ", grupa=" + grupa + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Material other = (Material) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    

    
   
    
    
    
}
