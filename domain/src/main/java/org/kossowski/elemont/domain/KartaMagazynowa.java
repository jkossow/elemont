/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.domain;

import java.io.Serializable;
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
    //@JoinColumn( foreignKey = @ForeignKey(name = "operacje_fk"))
    private List<Operacja> operacje = new ArrayList<>();       

    @OneToMany ( mappedBy = "kartaMagazynowa", cascade = CascadeType.ALL )
    //@JoinColumn( foreignKey = @ForeignKey(name = "odcinki_fk"))
    private List<Odcinek> odcinki = new ArrayList<>();
    
    //@ManyToOne
    //@JoinColumn( foreignKey = @ForeignKey(name = "umowa_fk"))
    //private Umowa umowa = null;
    
    @ManyToOne
    @JoinColumn( foreignKey = @ForeignKey(name = "user_fk"))
    private User user = null;
    
    private String miejsceSkladowania;
    
    
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "KartaMagazynowa{" + "id=" + id + ", status=" + status + ", projekt=" + projekt + ", material=" + material + ", producent=" + producent + ", dostawca=" + dostawca + ", stanIl=" + stanIl + ", operacje=" + operacje + ", odcinki=" + odcinki + ", user=" + user + ", miejsceSkladowania=" + miejsceSkladowania + '}';
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
