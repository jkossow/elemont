/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author jkossow
 */

@Entity
public class Odcinek implements Serializable  {
    
    //public static final BigDecimal NOT_SET = new BigDecimal( -1);
            
    
    @Id @GeneratedValue
    private Long id;
    
    private String nazwa;
    
    @ManyToOne
    @JoinColumn( name = "kartaMag_id")
    //@JoinColumn( foreignKey = @ForeignKey(name = "kartamag_fk"))
    private KartaMagazynowa kartaMagazynowa;
    
    
    private BigDecimal A = null;
    private BigDecimal B = null;
    private BigDecimal C = null;
    private BigDecimal D = null;
    
    private BigDecimal ulozone;
    private BigDecimal podlaczone;
    private BigDecimal scinkiSpodziewane;
    
    private BigDecimal scinekA;
    private BigDecimal scinekB;
    
    private Status status = Status.S0 ;
    private boolean  uzyty = false;
    

    //KartaMagazynowa kartaMag;
    
    public Odcinek(){};
    
    
    
        
    private void updateUsed() {
        uzyty =  A != null || B != null || C != null || D != null 
                // A != NOT_SET || B != NOT_SET ||
                // C != NOT_SET || D != NOT_SET 
              ? true : false ; 
    }
    
    public void setN( String wyroznik, BigDecimal war ) {
        
        switch( wyroznik ) {
            case "A": setA( war ); 
                break;
            case "B": setB( war ); 
                break;
            case "C": setC( war ); 
                break;
            case "D": setD( war ); 
                break;
            default:
                throw new IndexOutOfBoundsException( "Odczyt poza zakresem (A,B,C,D");
                
        }
    }
    
    public BigDecimal getN( String wyroznik ) {
        
        switch( wyroznik ) {
            case "A": return getA(); 
            case "B": return getB();
            case "C": return getC();
            case "D": return getD();
        }
        throw new IndexOutOfBoundsException( "Odczyt poza zakresem (A,B,C,D");
    }
    
    public boolean isSetN( String wyroznik ) {
        
        switch( wyroznik ) {
            case "A": return getA() != null; 
            case "B": return getB() != null;
            case "C": return getC() != null;
            case "D": return getD() != null;
        }
        throw new IndexOutOfBoundsException( "Odczyt poza zakresem (A,B,C,D");
    }
    
    public BigDecimal spodzScinekA() {
        return getA().subtract( getC() ).abs();
    }
    
    public BigDecimal spodzScinekB() {
        return getB().subtract( getD() ).abs();
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getA() {
        return A;
    }

    public void setA(BigDecimal A) {
        this.A = A;
        updateUsed();
    }

    public BigDecimal getB() {
        return B;
    }

    public void setB(BigDecimal B) {
        this.B = B;
        updateUsed();
    }

    public BigDecimal getC() {
        return C;
    }

    public void setC(BigDecimal C) {
        this.C = C;
        updateUsed();
    }

    public BigDecimal getD() {
        return D;
    }

    public void setD(BigDecimal D) {
        this.D = D;
        updateUsed();
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isUzyty() {
        return uzyty;
    }

    public BigDecimal getUlozone() {
        return ulozone;
    }

    public void setUlozone(BigDecimal ulozone) {
        this.ulozone = ulozone;
    }

    public BigDecimal getPodlaczone() {
        return podlaczone;
    }

    public void setPodlaczone(BigDecimal podlaczone) {
        this.podlaczone = podlaczone;
    }

    
    
    public KartaMagazynowa getKartaMagazynowa() {
        return kartaMagazynowa;
    }

    public void setKartaMagazynowa(KartaMagazynowa kartaMagazynowa) {
        this.kartaMagazynowa = kartaMagazynowa;
    }

    public BigDecimal getScinkiSpodziewane() {
        return scinkiSpodziewane;
    }

    public void setScinkiSpodziewane(BigDecimal scinkiSpodziewane) {
        this.scinkiSpodziewane = scinkiSpodziewane;
    }

    public BigDecimal getScinekA() {
        return scinekA;
    }

    public void setScinekA(BigDecimal scinekA) {
        this.scinekA = scinekA;
    }

    public BigDecimal getScinekB() {
        return scinekB;
    }

    public void setScinekB(BigDecimal scinekB) {
        this.scinekB = scinekB;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    
    @Override
    public int hashCode() {
        int hash = 5;
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
        final Odcinek other = (Odcinek) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Odcinek{" + "id=" + id + ", A=" + A + ", B=" + B + ", C=" + C + ", D=" + D + 
                ", ulozone=" + ulozone + ", podlaczone=" + podlaczone + 
                "scinekA " + scinekA + " scinekB " + scinekB + " status=" + status + '}';
    }

    
    
    
}
