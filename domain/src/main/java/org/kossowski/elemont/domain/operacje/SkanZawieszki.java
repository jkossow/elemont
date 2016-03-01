/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.domain.operacje;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.kossowski.elemont.domain.IllegalStatusException;
import org.kossowski.elemont.domain.Odcinek;
import org.kossowski.elemont.domain.Operacja;
import org.kossowski.elemont.domain.Stan;
import org.kossowski.elemont.domain.Status;

/**
 *
 * @author jkossow
 */

@Entity
@DiscriminatorValue("skan_zawieszki")

public class SkanZawieszki  extends Operacja{
    
    @ManyToOne
    @JoinColumn( foreignKey = @ForeignKey(name = "odcinek_fk"))
    private Odcinek odcinek;
    private String qrCode;
    private BigDecimal znacznik;
    
    private String suffix;
    private Long prefix;
    
    // rezerwa na rejestrowanie z ktorego czytnika jest odczyt
    //@ManyToOne
    //private CzytnikQR2 czytnik; 
    
    public SkanZawieszki() {};
    
    //mozna jeszcze dodac info o czytniku
    public SkanZawieszki( String qrCode, BigDecimal znacznik ) {
        
        this.qrCode = qrCode;
        this.znacznik = znacznik;
        
        suffix = getSuffix();
        prefix = getIdOdcinka();
    }
    
    public Long getIdOdcinka() {
       String s = qrCode.substring( 0, qrCode.length() - 1 );
       return new Long(s);
    };
    
    public String getSuffix() {
        
        String s = qrCode.substring( qrCode.length() -1 );
        return s;
    }
    
    public boolean testQRCode() {
        
        String s = getSuffix();

        return s.equals("A") || s.equals("B") || s.equals("C") || s.equals("D");
    }
    
    
    
    private Odcinek findOdcinek() {
       
        List<Odcinek> odcinki = getKartaMagazynowa().getOdcinki();
        Odcinek found = null;
        Long id = getIdOdcinka();
        
        for( Odcinek o : odcinki )
            if( o.getId().equals( id ) ) {
                found = o;
                break;
        };
        
        return found;
    }
    
    private void dodajMontazOdcinka( Odcinek o ) throws Exception {
        System.out.println("Ułożono odcinek " + o.getId());
        
        BigDecimal polozono = o.getA().subtract(o.getB()).abs();
        o.setUlozone(polozono);
        Stan stan = o.getKartaMagazynowa().getStanIl();
        
        stan.setIValue(Stan.IL_POLOZONA,  stan.getIValue(Stan.IL_POLOZONA).add(polozono));
        //stan.setIValue( Stan.IL_WYDANA_NA_BUD, stan.getIValue(Stan.IL_WYDANA_NA_BUD).subtract(polozono)  );
        stan.setIValue(Stan.IL_STAN_BIEZ, stan.getIValue(Stan.IL_STAN_BIEZ).subtract(polozono));
        
        //setAcceptFlag();
        
        o.setStatus( Status.S4);
        getKartaMagazynowa().trySet( Status.S4 );
        
    }
    
    private void dodajPodlaczeniePierwszegoKonca( Odcinek o ) throws Exception {
        System.out.println("Podłączono pierszwy koniec " + o.getId());
        
        Stan stan = o.getKartaMagazynowa().getStanIl();
        
        //setAcceptFlag();
        
        o.setStatus( Status.S5);
        getKartaMagazynowa().trySet( Status.S5 );
    }
    
    private void dodajPodlaczenieDrugiegoKonca( Odcinek o ) throws  Exception {
        System.out.println("Podłczono drugi koniec " + o.getId());
        
        if( o.getStatus() !=Status.S5   )
            throw new Exception("zła faza");
        
        BigDecimal podlaczono =  o.getC().subtract(o.getD()).abs();
        o.setPodlaczone( podlaczono );
        Stan stan = o.getKartaMagazynowa().getStanIl();
        
        stan.setIValue( Stan.IL_PODLACZONA, stan.getIValue( Stan.IL_PODLACZONA).add( podlaczono ));
        //stan.setIValue( Stan.IL_POLOZONA,   stan.getIValue( Stan.IL_POLOZONA).subtract( podlaczono ));
        
        //setAcceptFlag();
        
        o.setStatus( Status.S6);
        getKartaMagazynowa().trySet( Status.S6 );
    }
    
    
    @Override
    public void accept() throws  IllegalStatusException, Exception  {
        
        if( ! testQRCode() )
            throw new Exception( "Niepoprawny QR Code");
        
        Odcinek o;
        
        if( ( o = findOdcinek() ) ==  null )
            throw new Exception( "nieprzypisany odcinek, odcinek nie z tez partii");
        
        // A i B tylko przy S3
        if( (getSuffix().equals("A") || getSuffix().equals("B")) &&  o.getStatus() !=Status.S3   )
            throw new Exception("zła faza");
        
        // C i D tylko przy S4
        if( (getSuffix().equals("C") || getSuffix().equals("D")) && !( o.getStatus() == Status.S4 || o.getStatus() ==Status.S5 ) )
            throw new Exception("zła faza");
        
        //sprawdz czy juz byl skanowany
        System.out.println( "Zancznik n " + getSuffix() + " " + o.getN( getSuffix() ) );
        if( o.getN( getSuffix()) != null )
            throw new Exception("juz przypisany");
        
        
        o.setN( getSuffix(), znacznik);
        
     
        
        //akceptacja
        setAcceptFlag();
        
        //sprawdz czy mozna dodac ulozenie
        System.out.println("Test issetA, issSetB " + o.isSetN("A") + " " +o.isSetN("B"));
        if(o.getStatus() == Status.S3 && o.isSetN("A") && o.isSetN("B")) 
            dodajMontazOdcinka( o ); 
        else {        
        //sprawdz czy mozna dodac podlaczeniepierwszego konca
            if( o.getStatus() == Status.S4  )
                dodajPodlaczeniePierwszegoKonca( o );
            else 
                if( o.getStatus() == Status.S5 )
                    dodajPodlaczenieDrugiegoKonca( o );
        }
    }

    @Override
    public String opis() {
        return "skanPrzywieszki " + prefix+suffix + " znacznik " + znacznik;
    }

    
    
    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public BigDecimal getZnacznik() {
        return znacznik;
    }

    public void setZnacznik(BigDecimal znacznik) {
        this.znacznik = znacznik;
    }

    public Odcinek getOdcinek() {
        return odcinek;
    }

    public void setOdcinek(Odcinek odcinek) {
        this.odcinek = odcinek;
    }
    
}
