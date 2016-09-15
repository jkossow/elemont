/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

/**
 *
 * @author jkossow
 */

@Entity
public class KartaMagazynowa implements Serializable {
    
    @Id @GeneratedValue
    private Long id = 0L;
    
    @Enumerated( EnumType.STRING )
    private Status status = Status.S0;
    
    @ManyToOne
    @JoinColumn( foreignKey = @ForeignKey(name = "projekt_fk"))
    private Projekt projekt = null; //new Projekt();
    
    @ManyToOne
    @JoinColumn( foreignKey = @ForeignKey(name = "material_fk"))
    private Material material = null;
    // w tym momencie niewykorzystywane pole 
    // jednostka miary wynika z wartosci material.jm
    // byc moze bedzie potrzeba aby karta mag. miala swoja wartosc jjm
    // i tylko wykorzystywala material.jm jako wartosc domyslną - podpowiadana 
    // przy tworzeniu nowej karty magazynowej
    
    //@ManyToOne
    //@JoinColumn( foreignKey = @ForeignKey(name = "jm_fk"))
    //private Jm jm = null; 
    
    @ManyToOne
    @JoinColumn( foreignKey = @ForeignKey(name = "producent_fk"))
    private Producent producent = null;
    
    @ManyToOne
    @JoinColumn( foreignKey = @ForeignKey(name = "dostawca_fk"))
    private Producent dostawca = null;
    
    @Embedded
    @JoinColumn( foreignKey = @ForeignKey( name = "stan_fk"))
    private Stan stanIl = new Stan();
    
    @OneToMany(  mappedBy = "kartaMagazynowa" , cascade = CascadeType.ALL  )
    @OrderBy("czasUtworzenia")
    //@JoinColumn( foreignKey = @ForeignKey(name = "operacje_fk"))
    private List<Operacja> operacje = new ArrayList<>();       

    @OneToMany ( mappedBy = "kartaMagazynowa", cascade = CascadeType.ALL )
    @OrderBy("a1")
    //@JoinColumn( foreignKey = @ForeignKey(name = "odcinki_fk"))
    private List<Odcinek> odcinki = new ArrayList<>();
    
    //@ManyToOne
    //@JoinColumn( foreignKey = @ForeignKey(name = "umowa_fk"))
    //private Umowa umowa = null;
    
    @ManyToOne
    @JoinColumn( foreignKey = @ForeignKey(name = "utworzyl_fk"))
    private User utworzyl = null;
    
    @ManyToOne
    @JoinColumn( foreignKey = @ForeignKey(name = "owner_fk"))
    private User owner = null;
    
    private String miejsceSkladowania;
    private BigDecimal znacznikPoczatkowy;
    private BigDecimal znacznikKoncowy;
    private Boolean znacznikKoncowyDostepny;
    private Boolean znacznikiNarastajaco;
    
    private BigDecimal znacznikBiezacy;
    
    private Integer przyrostekNazwyOdcinka = 1;  // pomocnicza do nadawania nazw odcinkom
    
    
    public void addOperation( Operacja o ) {
        o.setKartaMagazynowa(this);
        operacje.add(o);
    }
    
    public void removeOperation( Operacja o ) {
        if( operacje.contains( o )) {
            operacje.remove(o);
            o.setKartaMagazynowa( null );
        }        
    }
    
    public void addOcinek( Odcinek o ) {
        o.setKartaMagazynowa(this);
        odcinki.add(o);
    }
    
    public void removeOdcinek( Odcinek o ) {
        if( odcinki.contains( o )) {
            odcinki.remove(o);
            o.setKartaMagazynowa( null );
        }        
    }
    
    public void ustawZnacznikBiezacy( BigDecimal znacznik ) {
        if( getZnacznikiNarastajaco() ) 
            if( znacznik.compareTo(znacznikBiezacy) > 0)
                znacznikBiezacy = znacznik;
        else
            if( znacznik.compareTo(znacznikBiezacy) < 0)
                znacznikBiezacy = znacznik;
                
    }
    
    public void trySet( Status status ) {
        
        boolean mozna = true;
        
        for( Odcinek o : odcinki) {
            System.out.println("Try set status odcinka "  + status + " "  + o.getStatus() + " " + status.compareTo(o.getStatus()) );
            if( status.compareTo( o.getStatus()) > 0 ) 
                mozna = false;
            
        };
        
        if( mozna )
            setStatus(status);
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public List<Operacja> getOperacje() {
        return operacje;
    }

    public void setOperacje(List<Operacja> operacje) {
        this.operacje = operacje;
        
    }

    //public Jm getJm() {
    //    return jm;
    //}

    //public void setJm(Jm jm) {
    //    this.jm = jm;
    //}

    public Stan getStanIl() {
        return stanIl;
    }

    public void setStanIl(Stan stanIl) {
        this.stanIl = stanIl;
    }

    public List<Odcinek> getOdcinki() {
        return odcinki;
    }

    public void setOdcinki(List<Odcinek> odcinki) {
        this.odcinki = odcinki;
    }

    public Producent getProducent() {
        return producent;
    }

    public void setProducent(Producent producent) {
        this.producent = producent;
    }

    //public Umowa getUmowa() {
    //    return umowa;
    //}

    //public void setUmowa(Umowa umowa) {
    //    this.umowa = umowa;
    //}

    public String getMiejsceSkladowania() {
        return miejsceSkladowania;
    }

    public void setMiejsceSkladowania(String miejsceSkladowania) {
        this.miejsceSkladowania = miejsceSkladowania;
    }

    public Producent getDostawca() {
        return dostawca;
    }

    public void setDostawca(Producent dostawca) {
        this.dostawca = dostawca;
    }

    public User getUtworzyl() {
        return utworzyl;
    }

    public void setUtworzyl(User utworzyl) {
        this.utworzyl = utworzyl;
    }

    public Integer getPrzyrostekNazwyOdcinka() {
        return przyrostekNazwyOdcinka;
    }

    public void setPrzyrostekNazwyOdcinka(Integer przyrostekNazwyOdcinka) {
        this.przyrostekNazwyOdcinka = przyrostekNazwyOdcinka;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public BigDecimal getZnacznikPoczatkowy() {
        return znacznikPoczatkowy;
    }

    public void setZnacznikPoczatkowy(BigDecimal znacznikPoczatkowy) {
        this.znacznikPoczatkowy = znacznikPoczatkowy;
    }

    public BigDecimal getZnacznikKoncowy() {
        return znacznikKoncowy;
    }

    public void setZnacznikKoncowy(BigDecimal znacznikKoncowy) {
        this.znacznikKoncowy = znacznikKoncowy;
    }

    public Boolean getZnacznikKoncowyDostepny() {
        return znacznikKoncowyDostepny;
    }

    public void setZnacznikKoncowyDostepny(Boolean znacznikKoncowyDostepny) {
        this.znacznikKoncowyDostepny = znacznikKoncowyDostepny;
    }

    public Boolean getZnacznikiNarastajaco() {
        return znacznikiNarastajaco;
    }

    public void setZnacznikiNarastajaco(Boolean znacznikiNarastajaco) {
        this.znacznikiNarastajaco = znacznikiNarastajaco;
    }

    public BigDecimal getZnacznikBiezacy() {
        return znacznikBiezacy;
    }

    public void setZnacznikBiezacy(BigDecimal znacznikBiezacy) {
        this.znacznikBiezacy = znacznikBiezacy;
    }

    
    

    
    
    @Override
    public String toString() {
        return "KartaMagazynowa{" + "id=" + id + ", status=" + status + ", projekt=" + projekt + ", material=" + material + ", producent=" + producent + ", dostawca=" + dostawca + ", stanIl=" + stanIl + ", operacje=" + operacje + ", odcinki=" + odcinki + ", user=" + utworzyl + ", miejsceSkladowania=" + miejsceSkladowania + '}';
    }


    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final KartaMagazynowa other = (KartaMagazynowa) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
    
}
