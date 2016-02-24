/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.domain.operacje;


import java.math.BigDecimal;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.kossowski.elemont.domain.KartaMagazynowa;
import org.kossowski.elemont.domain.Odcinek;
import org.kossowski.elemont.domain.Operacja;
import org.kossowski.elemont.domain.User;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



/**
 *
 * @author jkossow
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({org.kossowski.elemont.domain.operacje.PrzyjecieZGlownegoTest.class, org.kossowski.elemont.domain.operacje.PrzypisanieQR2Test.class, org.kossowski.elemont.domain.operacje.WydanieNabudoweTest.class})
public class SkanZawieszkiTest {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeClass
    public void setUp() throws Exception {
    }

    @AfterClass
    public void tearDown() throws Exception {
    }
    /*
    @Test
    public void testQRCode1() {
        SkanZawieszki sk = new SkanZawieszki();
        sk.setQrCode("23432D");
        System.out.println("testqrCode suffix " + sk.getSuffix() );
        System.out.println("get qrcodeid " + sk.getIdOdcinka() );
        
        assertTrue( sk.testQRCode() );
    }
    
    @Test
    public void suffixTest() {
        SkanZawieszki sk = new SkanZawieszki();
        sk.setQrCode("1111A");
        System.out.println( sk.getSuffix() );
        assertEquals( sk.getSuffix(), "A");
    }
    
    @Test
    public void prefixTest() {
        SkanZawieszki sk = new SkanZawieszki();
        sk.setQrCode("1234A");
        System.out.println( sk.getIdOdcinka() );
        assertEquals( sk.getIdOdcinka(), new Long(1234) );
    }
    
    @Test
    public void skanTest1()  throws Exception {
        System.out.println("Start skanTest1");
        KartaMagazynowa km = new KartaMagazynowa();
        Operacja o = new PrzyjecieZGlownego( new BigDecimal(100) );
        km.addOperation(o);
        o.accept();
        System.out.println( "il Operacji -> " + km.getOperacje().size()) ;
        
        o = new WydanieNaBudowe( new User(), new BigDecimal(100));
        km.addOperation(o);
        o.accept();
        System.out.println( "il Operacji -> " + km.getOperacje().size()) ;
        
       
    }
   */
}
