/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.domain.util;

import java.math.BigDecimal;
import org.kossowski.elemont.domain.KartaMagazynowa;
import org.kossowski.elemont.domain.Material;
import org.kossowski.elemont.domain.Odcinek;
import org.kossowski.elemont.domain.Operacja;
import org.kossowski.elemont.domain.Producent;
import org.kossowski.elemont.domain.Projekt;
import org.kossowski.elemont.domain.User;
import org.kossowski.elemont.domain.operacje.NowyOdcinek;
import org.kossowski.elemont.domain.operacje.PrzyjecieZGlownego;
import org.kossowski.elemont.domain.operacje.SkanScinka;
import org.kossowski.elemont.domain.operacje.SkanZawieszki;
import org.kossowski.elemont.domain.operacje.WydanieNaBudowe;

/**
 *
 * @author jkossow
 */
public class Rozliczenie1 {

    
    public static void main(String[] args) {
    
        GeneratorOdcinkow bo = new GeneratorOdcinkow();
        
        KartaMagazynowa k = new KartaMagazynowa();
        
        printState(k);
        
        PrzyjecieZGlownego przyj = new PrzyjecieZGlownego();
        przyj.setProjekt(new Projekt("pr1", "projet 1" ));
        przyj.setMaterial(new Material("indeks", "nazwa"));
        przyj.setProducent( new Producent("prod1", "prod1"));
        przyj.setDostawca( new Producent( "prod2", "prod2"));
        przyj.setMiejsceSkladowania("m2/3");
        przyj.setIlosc( new BigDecimal( "100"));
        
        k.addOperation(przyj);
        try {
            przyj.accept();
        } catch (Exception e) { e.printStackTrace(); };
        System.out.println( przyj.opis() );
        printState(k);
        
        WydanieNaBudowe wyd = new WydanieNaBudowe(new User("jkossow","kossowski", "janusz"), 
                new BigDecimal(100));
        k.addOperation(( wyd));
        
        try {
            wyd.accept();
        } catch (Exception e) { e.printStackTrace(); };
        System.out.println( wyd.opis() );
        printState(k);
        
        for( int i = 0; i < 4; i++ ) {
            Odcinek o = bo.nowyOdcinek();
            NowyOdcinek no = new NowyOdcinek( o );
            k.addOcinek(o);
            k.addOperation(no);
            
            try{ 
                no.accept();
                System.out.println( no.opis() );
            } catch( Exception e) { e.printStackTrace(); };
        } 
        
        printState(k);
        
        rejSkanPrzyw(k, "1A", 20); printState(k);
        //rejSkanPrzyw(k, "1C", 20); printState(k);
        rejSkanPrzyw(k, "1B", 40); printState(k); //oryginalnie 1B
        rejSkanPrzyw(k, "1C", 22); printState(k);
        
        //rejSkanPrzyw(k, "1A", 22); printState(k);
        
        rejSkanPrzyw(k, "2A", 45); printState(k);
        rejSkanPrzyw(k, "2B", 60); printState(k);
        rejSkanPrzyw(k, "1D", 39); printState(k);
        rejSkanPrzyw(k, "2D", 60); printState(k);
        rejSkanPrzyw(k, "2C", 46); printState(k);
        
        rejSkanPrzyw(k, "3A", 00); printState(k);
        rejSkanPrzyw(k, "3B", 20); printState(k);
        rejSkanPrzyw(k, "3C", 03); printState(k);
        rejSkanPrzyw(k, "3D", 18); printState(k);
        
        rejSkanPrzyw(k, "4A",  60); printState(k);
        rejSkanPrzyw(k, "4B", 100); printState(k);
        rejSkanPrzyw(k, "4C",  61); printState(k);
        rejSkanPrzyw(k, "4D",  98); printState(k);
        
        rejSkanScinka(k, "3A", 3); printState(k);
        rejSkanScinka(k, "3B", 2); printState(k);
        rejSkanScinka(k, "1A", 2); printState(k);
        rejSkanScinka(k, "1B", 1); printState(k);
        rejSkanScinka(k, "2A", 1); printState(k);
        rejSkanScinka(k, "2B", 0); printState(k);
        rejSkanScinka(k, "4A", 1); printState(k);
        rejSkanScinka(k, "4B", 2); printState(k);
        
        
        System.out.println("Operacje na karce");
        for( Operacja o : k.getOperacje() ) {
            System.out.println( o.opis() );
        }
    }
    
    public static void printState( KartaMagazynowa k) {
        System.out.println("Status i stan " + k.getStatus() + " " + k.getStanIl() );
        System.out.println("Operacji i odcinków " + k.getOperacje().size() + " " + k.getOdcinki().size() );
        System.out.println("Material"  + k.getMaterial()  );
        System.out.println("Projekt " + k.getProjekt() );
        if( k.getUser() != null)
            System.out.println("User " + k.getUser().getLogin());
        for( Odcinek o : k.getOdcinki() ) {
            System.out.println( o );
        }
    }
    
    public static void rejSkanPrzyw( KartaMagazynowa k, String skan, long znacznik) {
        
        SkanZawieszki o = new SkanZawieszki( skan, new BigDecimal( znacznik ));
        System.out.println( o.opis() );
        k.addOperation( o );
        try {
            o.accept();
        } catch (Exception e) {  e.printStackTrace(); };
        
    }
    
    public static void rejSkanScinka( KartaMagazynowa k, String skan, long znacznik) {
        
        SkanScinka o = new SkanScinka( skan, new BigDecimal( znacznik ));
        System.out.println( o.opis() );
        k.addOperation( o );
        try {
            o.accept();
        } catch (Exception e) {  e.printStackTrace(); };
        
    }
}
