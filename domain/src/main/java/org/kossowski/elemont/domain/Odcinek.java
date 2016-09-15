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
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author jkossow
 */

@Entity
public class Odcinek implements Serializable, Cloneable {
    
    //public static final BigDecimal NOT_SET = new BigDecimal( -1);
            
    
    @Id @GeneratedValue
    private Long id;
    
    private String nazwa;
    
    private String kks;
    
    @ManyToOne
    @JoinColumn( name = "kartaMag_id")
    //@JoinColumn( foreignKey = @ForeignKey(name = "kartamag_fk"))
    private KartaMagazynowa kartaMagazynowa;
    
    
    private BigDecimal A1 = null;
    private BigDecimal B1 = null;
    private BigDecimal A2 = null;
    private BigDecimal B2 = null;
    
    private BigDecimal ulozone;
    private BigDecimal podlaczone;
    private BigDecimal scinkiSpodziewane;
    
    private BigDecimal scinekA1;
    private BigDecimal scinekB1;
    
    private Status status = Status.S0 ;
    private boolean  uzyty = false;
    
    private BigDecimal znacznikPoczatkowy;
    
    @ManyToOne
    @JoinColumn( foreignKey = @ForeignKey(name = "owner_fk"))
    private User owner;
    
    //KartaMagazynowa kartaMag;
    
    public Odcinek(){};
    
    public Odcinek( BigDecimal A1, BigDecimal A2, BigDecimal B2, BigDecimal B1 ) {
        this.A1 = A1;
        this.A2 = A2;
        this.B1 = B1;
        this.B2 = B2;
    }
    
    
    public Odcinek( Integer A1, Integer A2, Integer B2, Integer B1 ) {
        this.A1 = A1 != null ? new BigDecimal( A1 ) : null;
        this.A2 = A2 != null ? new BigDecimal( A2 ) : null;
        this.B2 = B2 != null ? new BigDecimal( B2 ) : null;
        this.B1 = B1 != null ? new BigDecimal( B1 ) : null;
    }
    
        
    private void updateUsed() {
        uzyty =  A1 != null || B1 != null || A2 != null || B2 != null 
                // A1 != NOT_SET || B1 != NOT_SET ||
                // A2 != NOT_SET || B2 != NOT_SET 
              ? true : false ; 
    }
    
    public void setN( String wyroznik, BigDecimal war ) {
        
        switch( wyroznik ) {
            case "A1": setA1( war ); 
                break;
            case "B1": setB1( war ); 
                break;
            case "A2": setA2( war ); 
                break;
            case "B2": setB2( war ); 
                break;
            default:
                throw new IndexOutOfBoundsException( "Odczyt poza zakresem (A1,A2,B1,B2)");
                
        }
    }
    
    public BigDecimal getN( String wyroznik ) {
        
        switch( wyroznik ) {
            case "A1": return getA1(); 
            case "B1": return getB1();
            case "A2": return getA2();
            case "B2": return getB2();
        }
        throw new IndexOutOfBoundsException( "Odczyt poza zakresem (A1,A2,B1,B2)");
    }
    
    public boolean isSetN( String wyroznik ) {
        
        switch( wyroznik ) {
            case "A1": return getA1() != null; 
            case "B1": return getB1() != null;
            case "A2": return getA2() != null;
            case "B2": return getB2() != null;
        }
        throw new IndexOutOfBoundsException( "Odczyt poza zakresem (A1,A2,B1,B2)");
    }
    
    public BigDecimal spodzScinekA1() {
        return getA1().subtract( getA2() ).abs();
    }
    
    public BigDecimal spodzScinekB1() {
        return getB1().subtract( getB2() ).abs();
    }
    
    
    public BigDecimal sumaScinek() {
        BigDecimal suma = null;
        
        suma = scinekA1 != null ? scinekA1.add( suma ) : suma ; 
        suma = scinekB1 != null ? scinekB1.add( suma ) : suma ; 
        return suma;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getA1() {
        return A1;
    }

    public void setA1(BigDecimal A1) {
        this.A1 = A1;
        updateUsed();
    }

    public BigDecimal getB1() {
        return B1;
    }

    public void setB1(BigDecimal B1) {
        this.B1 = B1;
        updateUsed();
    }

    public BigDecimal getA2() {
        return A2;
    }

    public void setA2(BigDecimal A2) {
        this.A2 = A2;
        updateUsed();
    }

    public BigDecimal getB2() {
        return B2;
    }

    public void setB2(BigDecimal B2) {
        this.B2 = B2;
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

    public BigDecimal getScinekA1() {
        return scinekA1;
    }

    public void setScinekA1(BigDecimal scinekA1) {
        this.scinekA1 = scinekA1;
    }

    public BigDecimal getScinekB1() {
        return scinekB1;
    }

    public void setScinekB1(BigDecimal scinekB1) {
        this.scinekB1 = scinekB1;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public BigDecimal getZnacznikPoczatkowy() {
        return znacznikPoczatkowy;
    }

    public void setZnacznikPoczatkowy(BigDecimal znacznikPoczatkowy) {
        this.znacznikPoczatkowy = znacznikPoczatkowy;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    public String getKks() {
        return kks;
    }

    public void setKks(String kks) {
        this.kks = kks;
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
        return "Odcinek{" + "id=" + id + ", nazwa=" + nazwa + ", A1=" + A1 + ", B1=" + B1 + ", A2=" + A2 + ", B2=" + B2 + ", ulozone=" + ulozone + ", podlaczone=" + podlaczone + ", scinkiSpodziewane=" + scinkiSpodziewane + ", scinekA1=" + scinekA1 + ", scinekB1=" + scinekB1 + ", status=" + status + ", uzyty=" + uzyty + '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

   
    

    

    
    
    
}
