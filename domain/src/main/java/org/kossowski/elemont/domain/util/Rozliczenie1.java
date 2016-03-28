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
import org.kossowski.elemont.domain.operacje.Zwrot;

/**
 *
 * @author jkossow
 */
public class Rozliczenie1 {

    
    public static void main(String[] args) {
    
        GeneratorOdcinkow bo = new GeneratorOdcinkow();
        
        User u = new User("jkossow","kossowski", "janusz");
        u.getRole().add("ROLE_ADMIN");
        u.getRole().add("ROLE_MAGAZYN");
        u.getRole().add("ROLE_BUDOWA");
        
        KartaMagazynowa k = new KartaMagazynowa();
        k.setId( 1L );
        printState(k);
        
        PrzyjecieZGlownego przyj = new PrzyjecieZGlownego();
        
        
        
        przyj.setProjekt(new Projekt(1L,"p1", "projekt1"));
        przyj.setMaterial(new Material(1L ,"indeks", "nazwa"));
        przyj.setProducent( new Producent(1L, "prod1", "prod1"));
        przyj.setDostawca( new Producent(1L, "prod2", "prod2"));
        przyj.setMiejsceSkladowania("m2/3");
        przyj.setIlosc( new BigDecimal( "100"));
        
        k.addOperation(przyj);
        try {
            przyj.accept();
        } catch (Exception e) { e.printStackTrace(); };
        System.out.println( przyj.opis() );
        printState(k);
        
        
        k.getProjekt().getZespol().add(u);
        WydanieNaBudowe wyd = new WydanieNaBudowe( new BigDecimal(100), u );
        k.addOperation(( wyd));
        
        try {
            wyd.accept();
        } catch (Exception e) { e.printStackTrace(); };
        System.out.println( wyd.opis() );
        printState(k);
        
        for( int i = 0; i < 4; i++ ) {
            Odcinek o = bo.nowyOdcinek();
            NowyOdcinek no = new NowyOdcinek( o, u );
            k.addOcinek(o);
            k.addOperation(no);
            
            try{ 
                no.accept();
                System.out.println( no.opis() );
            } catch( Exception e) { e.printStackTrace(); };
        } 
        
        printState(k);
        
        rejSkanPrzyw(k, "1A1", 20); printState(k);
        //rejSkanPrzyw(k, "1C", 20); printState(k);
        rejSkanPrzyw(k, "1B1", 40); 
        printState(k); //oryginalnie 1B
        rejSkanPrzyw(k, "1A2", 22); printState(k);
        
        //rejSkanPrzyw(k, "1A", 22); printState(k);
        
        rejSkanPrzyw(k, "2A1", 45); printState(k);
        rejSkanPrzyw(k, "2B1", 60); printState(k);
        rejSkanPrzyw(k, "1B2", 39); printState(k);
        rejSkanPrzyw(k, "2B2", 60); printState(k);
        rejSkanPrzyw(k, "2A2", 46); printState(k);
        
        rejSkanPrzyw(k, "3A1", 00); printState(k);
        rejSkanPrzyw(k, "3B1", 20); printState(k);
        rejSkanPrzyw(k, "3A2", 03); printState(k);
        rejSkanPrzyw(k, "3B2", 18); printState(k);
        
        rejSkanPrzyw(k, "4A1",  60); printState(k);
        rejSkanPrzyw(k, "4B1", 100); printState(k);
        rejSkanPrzyw(k, "4A2",  61); printState(k);
        rejSkanPrzyw(k, "4B2",  98); printState(k);
        
        rejSkanScinka(k, "3A1", 3); printState(k);
        rejSkanScinka(k, "3B1", 2); printState(k);
        rejSkanScinka(k, "1A1", 2); printState(k);
        rejSkanScinka(k, "1B1", 1); printState(k);
        rejSkanScinka(k, "2A1", 1); printState(k);
        rejSkanScinka(k, "2B1", 0); printState(k);
        rejSkanScinka(k, "4A1", 1); printState(k);
        rejSkanScinka(k, "4B1", 2); printState(k);
        
        Zwrot z = new Zwrot(  new BigDecimal(5), u);
        k.addOperation(z);
        try {
            z.accept();
        } catch (Exception e) { e.printStackTrace(); };
        System.out.println( z.opis() );
        printState(k);
        
                
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
        if( k.getUtworzyl() != null)
            System.out.println("User " + k.getUtworzyl().getLogin());
        for( Odcinek o : k.getOdcinki() ) {
            System.out.println( o );
        }
    }
    
    public static void rejSkanPrzyw( KartaMagazynowa k, String skan, long znacznik) {
        
        User u = new User("jkossow","kossowski", "janusz");
        
        SkanZawieszki o = new SkanZawieszki( skan, new BigDecimal( znacznik ), u);
        System.out.println( o.opis() );
        k.addOperation( o );
        try {
            o.accept();
        } catch (Exception e) {  e.printStackTrace(); };
        
    }
    
    public static void rejSkanScinka( KartaMagazynowa k, String skan, long znacznik) {
        
        User u = new User("jkossow","kossowski", "janusz");
        
        SkanScinka o = new SkanScinka( skan, new BigDecimal( znacznik ), u);
        System.out.println( o.opis() );
        k.addOperation( o );
        try {
            o.accept();
        } catch (Exception e) {  e.printStackTrace(); };
        
    }
}
