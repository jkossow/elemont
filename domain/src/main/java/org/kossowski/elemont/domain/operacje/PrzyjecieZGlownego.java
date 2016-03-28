/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.domain.operacje;

import java.math.BigDecimal;
import java.util.Date;
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
import org.kossowski.elemont.domain.User;

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
    
    private BigDecimal ilosc;
    private BigDecimal znacznikPocz;
    private BigDecimal znacznikKonc;
    private Boolean znacznikKoncaDostepny;
    private Boolean znacznikiRosnaco = true;
    
    public PrzyjecieZGlownego() {};
    
    public PrzyjecieZGlownego( User utworzyl, Date czasUtworzenia, BigDecimal ilosc ) {
        
        this.ilosc = ilosc;
        setUtworzyl(utworzyl);
        setCzasUtworzenia(czasUtworzenia);
    }

    @Override
    public void accept() throws IllegalStatusException {
        // mozna akceptowac tylko gdy status = S0
        if( getKartaMagazynowa().getStatus() != Status.S0 )
            throw new IllegalStatusException("niedopuszczalny status partii");
        
        //dopisanie do kolekcji operacji
        //getKartaMagazynowa().getOperacje().add( this );
        // juz niepotrzebne - robi to metoda wstawiania
        
        
        //modyfikacja stanu
        Stan s = getKartaMagazynowa().getStanIl();
        s.setIValue( Stan.IL_PRZYJETA, ilosc );
        s.setIValue( Stan.IL_W_MAG_GL, ilosc );
        
        //ustawienie pol jak w przyjeciu
        getKartaMagazynowa().setProjekt( this.projekt );
        getKartaMagazynowa().setMaterial( this.material );
        getKartaMagazynowa().setProducent( this.producent );
        getKartaMagazynowa().setDostawca( this.dostawca );
        getKartaMagazynowa().setMiejsceSkladowania( this.miejsceSkladowania );
        getKartaMagazynowa().setUtworzyl( this.getUtworzyl() );
        
        //korekta pola znacznik koncowy
        if( ! this.znacznikKoncaDostepny )
            setZnacznikKonc( null );
        
        getKartaMagazynowa().setZnacznikPoczatkowy( this.znacznikPocz );
        getKartaMagazynowa().setZnacznikKoncowy( this.znacznikKonc );
        getKartaMagazynowa().setZnacznikKoncowyDostepny( this.znacznikKoncaDostepny );
        getKartaMagazynowa().setZnacznikiNarastajaco( this.znacznikiRosnaco );
        
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

    public BigDecimal getZnacznikPocz() {
        return znacznikPocz;
    }

    public void setZnacznikPocz(BigDecimal znacznikPocz) {
        this.znacznikPocz = znacznikPocz;
    }

    public BigDecimal getZnacznikKonc() {
        return znacznikKonc;
    }

    public void setZnacznikKonc(BigDecimal znacznikKonc) {
        this.znacznikKonc = znacznikKonc;
    }

    public Boolean getZnacznikKoncaDostepny() {
        return znacznikKoncaDostepny;
    }

    public void setZnacznikKoncaDostepny(Boolean znacznikKoncaDostepny) {
        this.znacznikKoncaDostepny = znacznikKoncaDostepny;
    }

    public Boolean getZnacznikiRosnaco() {
        return znacznikiRosnaco;
    }

    public void setZnacznikiRosnaco(Boolean znacznikiRosnaco) {
        this.znacznikiRosnaco = znacznikiRosnaco;
    }
    
    
    
}
