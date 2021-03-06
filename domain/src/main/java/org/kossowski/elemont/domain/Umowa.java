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

/**
 *
 * @author jkossow
 */

@Entity
public class Umowa implements Serializable {
    
    @Id @GeneratedValue
    private Long id = 0L;
    
    private String numer;

    public Umowa() {
    }

    public Umowa(String numer) {
        this.numer = numer;
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumer() {
        return numer;
    }

    public void setNumer(String numer) {
        this.numer = numer;
    }

    @Override
    public String toString() {
        return "Umowa{" + "id=" + id + ", numer=" + numer + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Umowa other = (Umowa) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
}
