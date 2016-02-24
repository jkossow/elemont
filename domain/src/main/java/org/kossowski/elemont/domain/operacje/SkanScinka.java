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
import org.kossowski.elemont.domain.Odcinek;
import org.kossowski.elemont.domain.Operacja;
import org.kossowski.elemont.domain.Stan;
import org.kossowski.elemont.domain.Status;

/**
 *
 * @author jkossow
 */

@Entity
@DiscriminatorValue("skan_scinka")
public class SkanScinka  extends Operacja{
    
    
    private String qrCode;
    private BigDecimal dlugosc;
    
    private String suffix;
    private Long prefix;
    
    // rezerwa na rejestrowanie z ktorego czytnika jest odczyt
    //@ManyToOne
    //private CzytnikQR2 czytnik; 
    
    public SkanScinka() {};
    
    //mozna jeszcze dodac info o czytniku
    public SkanScinka( String qrCode, BigDecimal znacznik ) {
        
        this.qrCode = qrCode;
        this.dlugosc = znacznik;
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

        return s.equals("A") || s.equals("B");
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
    
    
    private void rejestruj( Odcinek o, String scinek, BigDecimal dlugosc ) {
        
        switch (scinek) {
            case "A" : o.setScinekA( dlugosc );
                break;
            case "B" : o.setScinekB( dlugosc );
                break;
        };
        
        Stan stan = getKartaMagazynowa().getStanIl();
        stan.setIValue(Stan.IL_SCINKOW, stan.getIValue(Stan.IL_SCINKOW).add(dlugosc));
        
        
    }
    
    
    
    @Override
    public void accept() throws  IllegalStatusException, Exception  {
        
        if( ! testQRCode() )
            throw new Exception( "Niepoprawny QR Code");
        
        Odcinek o;
        String scinek =  getSuffix();
        if( ( o = findOdcinek() ) ==  null )
            throw new Exception( "nieprzypisany odcinek, odcinek nie z tez partii");
        
        // A i B tylko przy S6
        if( (scinek.equals("A") || scinek.equals("B")) &&  o.getStatus() != Status.S6   )
            throw new Exception("zła faza");
        
        if( scinek.equals("A") && o.getScinekA() != null)       
            throw new Exception("scinek A juz skanowany");
        
        if( scinek.equals("B") && o.getScinekB() != null)       
            throw new Exception("scinek A juz skanowany");
        
        rejestruj( o, scinek, dlugosc );
        
        
        
        //akceptacja
        setAcceptFlag();
        
        if( o.getScinekA() != null && o.getScinekB() != null )
            o.setStatus( Status.S8 );
        getKartaMagazynowa().trySet( Status.S8 );
    }

    @Override
    public String opis() {
        return "skanScinka " + prefix+suffix;
    }

    
    
    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public BigDecimal getDlugosc() {
        return dlugosc;
    }

    public void setDlugosc(BigDecimal dlugosc) {
        this.dlugosc = dlugosc;
    }

    public Long getPrefix() {
        return prefix;
    }

    public void setPrefix(Long prefix) {
        this.prefix = prefix;
    }

    
    
    
    
    
}
