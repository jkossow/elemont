/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.test2.domain;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import org.kossowski.elemont.domain.KartaMagazynowa;
import org.kossowski.elemont.domain.Stan;
import org.kossowski.elemont.domain.operacje.PrzyjecieZGlownego;
import org.kossowski.elemont.domain.operacje.WydanieNaBudowe;
import org.kossowski.elemont.domain.operacje.Zwrot;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author jkossow
 */
public class PrzyjecieTest {
    
    public PrzyjecieTest() {
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

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }
    
    @Test
    public void test1() throws Exception {
        
        KartaMagazynowa k = new KartaMagazynowa();
        assertEquals(k.getStanIl(), new Stan( new int[] {0,0,0,0,0,0,0,0}) );
        assertEquals( k.getZnacznikBiezacy(), null);
        assertEquals(k.getZnacznikPoczatkowy(), null );
        assertEquals(k.getZnacznikKoncowy(), null );
        
        PrzyjecieZGlownego p = new PrzyjecieZGlownego( null, new Date(), 
                new BigDecimal(1000));
        p.setZnacznikPoczatkowy( new BigDecimal(BigInteger.ZERO));
        p.setIlosc( new BigDecimal( 1000 ));
        k.addOperation(p);
        p.accept();
        System.out.println( k.getStanIl() );
     
        WydanieNaBudowe w = new WydanieNaBudowe(null, new Date(), 
                new BigDecimal(1000), null);
        k.addOperation(w);
        w.accept();
        
        System.out.println( k.getStanIl() );
        
        Zwrot z = new Zwrot( null, new Date(), new BigDecimal(800), 
                new BigDecimal(200), new BigDecimal(1000), false, null);
        k.addOperation(z);
        z.accept();
        
        System.out.println( k.getStanIl());
        
    }
}
