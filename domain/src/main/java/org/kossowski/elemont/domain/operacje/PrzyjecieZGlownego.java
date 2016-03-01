/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.domain.operacje;

import java.math.BigDecimal;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.kossowski.elemont.domain.IllegalStatusException;
import org.kossowski.elemont.domain.Material;
import org.kossowski.elemont.domain.Operacja;
import org.kossowski.elemont.domain.Producent;
import org.kossowski.elemont.domain.Projekt;
import org.kossowski.elemont.domain.Stan;
import org.kossowski.elemont.domain.Status;

/**
 *
 * @author jkossow
 */
@Entity
@DiscriminatorValue("przyj_z_gl")
public class PrzyjecieZGlownego extends Operacja {
    
    @ManyToOne
    @JoinColumn( foreignKey = @ForeignKey(name = "projekt_fk"))
    private Projekt projekt;
    
    @ManyToOne
    @JoinColumn( foreignKey = @ForeignKey(name = "material_fk"))
    private Material material;
    
    @ManyToOne
    @JoinColumn( foreignKey = @ForeignKey(name = "producent_fk"))
    private Producent producent;
    
    @ManyToOne
    @JoinColumn( foreignKey = @ForeignKey(name = "dostawca_fk"))
    private Producent dostawca;
    
    String miejsceSkladowania;
    
    private BigDecimal ilosc ;
    
    public PrzyjecieZGlownego() {};
    
    public PrzyjecieZGlownego( BigDecimal ilosc ) {
        
        this.ilosc = ilosc;
        
    }

    @Override
    public void accept() throws IllegalStatusException {
        // mozna akceptowac tylko gdy status = S0
        if( getKartaMagazynowa().getStatus() != Status.S0 )
            throw new IllegalStatusException();
        
        //dopisanie do kolekcji operacji
        //getKartaMagazynowa().getOperacje().add( this );
        // juz niepotrzebne - robi to metoda wstawiania
        
        
        //modyfikacja stanu
        Stan s = getKartaMagazynowa().getStanIl();
        s.setIValue( Stan.IL_PRZYJETA, ilosc);
        s.setIValue( Stan.IL_W_MAG_GL, ilosc);
        
        //ustawienie pol jak w przyjeciu
        getKartaMagazynowa().setProjekt(projekt);
        getKartaMagazynowa().setMaterial(material);
        getKartaMagazynowa().setProducent(producent);
        getKartaMagazynowa().setDostawca(dostawca);
        getKartaMagazynowa().setMiejsceSkladowania(miejsceSkladowania);
        
        //zmiana statusu
        getKartaMagazynowa().setStatus( Status.S1 );
        
        setAcceptFlag();
        
    }

    @Override
    public String opis() {
        return "przyjecieZGlownego projekt " + getProjekt().getSymbol() + " ilosc " + getIlosc() ;
    }

    
    
    
    public BigDecimal getIlosc() {
        return ilosc;
    }

    public void setIlosc(BigDecimal ilosc) {
        this.ilosc = ilosc;
    }

    public Projekt getProjekt() {
        return projekt;
    }

    public void setProjekt(Projekt projekt) {
        this.projekt = projekt;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Producent getProducent() {
        return producent;
    }

    public void setProducent(Producent producent) {
        this.producent = producent;
    }

    public Producent getDostawca() {
        return dostawca;
    }

    public void setDostawca(Producent dostawca) {
        this.dostawca = dostawca;
    }

    public String getMiejsceSkladowania() {
        return miejsceSkladowania;
    }

    public void setMiejsceSkladowania(String miejsceSkladowania) {
        this.miejsceSkladowania = miejsceSkladowania;
    }
    
    
    
}
