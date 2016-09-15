/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.domain.operacje;

import java.math.BigDecimal;
import java.util.Date;
import org.kossowski.elemont.domain.IllegalStatusException;
import org.kossowski.elemont.domain.KartaMagazynowa;
import org.kossowski.elemont.domain.Operacja;
import org.kossowski.elemont.domain.Stan;
import org.kossowski.elemont.domain.Status;
import org.kossowski.elemont.domain.User;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;





/**
 *
 * @author jkossow
 */
public class PrzyjecieZGlownegoTest {
    
    public PrzyjecieZGlownegoTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    
    
    @Test
    public void przyjecieZGlownego( BigDecimal ilosc ) throws IllegalStatusException, Exception {
        KartaMagazynowa k = new KartaMagazynowa();
        Stan przed = new Stan( new int[]{0,0,0,0,0,0,0,0} );
        Stan po    = new Stan( new int[]{ilosc.intValue(),ilosc.intValue(),0,0,0,0,0,0} );
        
        assertEquals( k.getStanIl(), przed );
        assertEquals( k.getStatus(), Status.S0 );
        
        System.out.println("Stan przed operacja " + k.getStanIl() ); 
        System.out.println("Status przed operacja " + k.getStatus() ); 
        Operacja o = new PrzyjecieZGlownego( new User(), new Date(), ilosc );
        k.addOperation(o);
        assertEquals( k.getOperacje().size(), 1 );
        
        o.accept();
        
        assertEquals( k.getStanIl(), po );
        assertEquals( k.getStatus(), Status.S1 );
        assertEquals( k.getOperacje().size(), 1 );
        
        System.out.println("Stan po operacji " + k.getStanIl() ); 
        System.out.println("Status po operacji " + k.getStatus() );
        
    }
    
    @Test
    public void przyjecieZGlownegoTest() throws IllegalStatusException, Exception {
        przyjecieZGlownego(  BigDecimal.ONE );
        przyjecieZGlownego(  BigDecimal.TEN );
        przyjecieZGlownego(  BigDecimal.ZERO );
        przyjecieZGlownego(  new BigDecimal( "12" ) );
        
    }
    
    //powtórne przyjecie 
    @Test( expectedExceptions = IllegalStatusException.class )
    public void powtornePrzyjecieTest() throws IllegalStatusException, Exception {
         KartaMagazynowa k = new KartaMagazynowa();
         Operacja o1 = new PrzyjecieZGlownego( new User(), new Date(), new BigDecimal(10) );
         k.addOperation(o1);
         o1.accept();
         
         Operacja o2 = new PrzyjecieZGlownego( new User(), new Date(),  new BigDecimal(20) );
         k.addOperation(o2);
         o2.accept();
    }
   
}
