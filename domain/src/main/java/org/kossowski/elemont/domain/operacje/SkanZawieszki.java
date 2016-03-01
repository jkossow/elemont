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
import org.kossowski.elemont.domain.IllegalStatusException;
import org.kossowski.elemont.domain.KartaMagazynowa;
import org.kossowski.elemont.domain.Odcinek;
import org.kossowski.elemont.domain.Operacja;
import org.kossowski.elemont.domain.Status;

/**
 *
 * @author jkossow
 */

@Entity
@DiscriminatorValue("skan_zawieszki")

public class SkanZawieszki  extends Operacja{
    
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
    
    private void dodajMontazOdcinka( Odcinek o ) {
        
        KartaMagazynowa k = o.getKartaMagazynowa();
        
        UlozenieOdcinka m = new UlozenieOdcinka( o );
        k.addOperation(m);
        
        try {
            m.accept();
        } catch (Exception e ) {e.printStackTrace(); }
    }
    
    private void dodajPodlaczeniePierwszegoKonca( Odcinek o ) {
        
        KartaMagazynowa k = o.getKartaMagazynowa();
        
        PodlaczeniePierwszegoKonca p = new PodlaczeniePierwszegoKonca(o);
        k.addOperation(p);
        
        try {
            p.accept();
        } catch (Exception e ) {e.printStackTrace(); }
    }
    
    private void dodajPodlaczenieDrugiegoKonca( Odcinek o ) {
        
        KartaMagazynowa k = o.getKartaMagazynowa();
        
        PodlaczenieDrugiegoKonca p = new PodlaczenieDrugiegoKonca(o);
        k.addOperation(p);
        
        try {
            p.accept();
        } catch (Exception e ) {e.printStackTrace(); }
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
        if( !o.getN( getSuffix()).equals( Odcinek.NOT_SET ) )
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
        //sprawdz czy mozna dodac podlaczenie drugiego konca
        //if( o.getStatus() == Status.S5  && (o.isSetN("C") || o.isSetN("D") ))
        //   dodajPodlaczenieDrugiegoKonca( o );
        
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
