/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.domain.operacje;

import java.math.BigDecimal;
import java.util.Date;
import org.junit.After;
import org.junit.Before;
import org.kossowski.elemont.domain.IllegalStatusException;
import org.kossowski.elemont.domain.KartaMagazynowa;
import org.kossowski.elemont.domain.Material;
import org.kossowski.elemont.domain.Operacja;
import org.kossowski.elemont.domain.Producent;
import org.kossowski.elemont.domain.Projekt;
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

    @org.junit.BeforeClass
    public static void setUpClass() throws Exception {
    }

    @org.junit.AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    

    
    
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

    /**
     * Test of accept method, of class PrzyjecieZGlownego.
     */
    @org.junit.Test
    public void testAccept() throws Exception {
        System.out.println("accept");
        PrzyjecieZGlownego instance = new PrzyjecieZGlownego();
        instance.accept();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of opis method, of class PrzyjecieZGlownego.
     */
    @org.junit.Test
    public void testOpis() {
        System.out.println("opis");
        PrzyjecieZGlownego instance = new PrzyjecieZGlownego();
        String expResult = "";
        String result = instance.opis();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIlosc method, of class PrzyjecieZGlownego.
     */
    @org.junit.Test
    public void testGetIlosc() {
        System.out.println("getIlosc");
        PrzyjecieZGlownego instance = new PrzyjecieZGlownego();
        BigDecimal expResult = null;
        BigDecimal result = instance.getIlosc();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIlosc method, of class PrzyjecieZGlownego.
     */
    @org.junit.Test
    public void testSetIlosc() {
        System.out.println("setIlosc");
        BigDecimal ilosc = null;
        PrzyjecieZGlownego instance = new PrzyjecieZGlownego();
        instance.setIlosc(ilosc);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProjekt method, of class PrzyjecieZGlownego.
     */
    @org.junit.Test
    public void testGetProjekt() {
        System.out.println("getProjekt");
        PrzyjecieZGlownego instance = new PrzyjecieZGlownego();
        Projekt expResult = null;
        Projekt result = instance.getProjekt();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setProjekt method, of class PrzyjecieZGlownego.
     */
    @org.junit.Test
    public void testSetProjekt() {
        System.out.println("setProjekt");
        Projekt projekt = null;
        PrzyjecieZGlownego instance = new PrzyjecieZGlownego();
        instance.setProjekt(projekt);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMaterial method, of class PrzyjecieZGlownego.
     */
    @org.junit.Test
    public void testGetMaterial() {
        System.out.println("getMaterial");
        PrzyjecieZGlownego instance = new PrzyjecieZGlownego();
        Material expResult = null;
        Material result = instance.getMaterial();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMaterial method, of class PrzyjecieZGlownego.
     */
    @org.junit.Test
    public void testSetMaterial() {
        System.out.println("setMaterial");
        Material material = null;
        PrzyjecieZGlownego instance = new PrzyjecieZGlownego();
        instance.setMaterial(material);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProducent method, of class PrzyjecieZGlownego.
     */
    @org.junit.Test
    public void testGetProducent() {
        System.out.println("getProducent");
        PrzyjecieZGlownego instance = new PrzyjecieZGlownego();
        Producent expResult = null;
        Producent result = instance.getProducent();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setProducent method, of class PrzyjecieZGlownego.
     */
    @org.junit.Test
    public void testSetProducent() {
        System.out.println("setProducent");
        Producent producent = null;
        PrzyjecieZGlownego instance = new PrzyjecieZGlownego();
        instance.setProducent(producent);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDostawca method, of class PrzyjecieZGlownego.
     */
    @org.junit.Test
    public void testGetDostawca() {
        System.out.println("getDostawca");
        PrzyjecieZGlownego instance = new PrzyjecieZGlownego();
        Producent expResult = null;
        Producent result = instance.getDostawca();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDostawca method, of class PrzyjecieZGlownego.
     */
    @org.junit.Test
    public void testSetDostawca() {
        System.out.println("setDostawca");
        Producent dostawca = null;
        PrzyjecieZGlownego instance = new PrzyjecieZGlownego();
        instance.setDostawca(dostawca);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMiejsceSkladowania method, of class PrzyjecieZGlownego.
     */
    @org.junit.Test
    public void testGetMiejsceSkladowania() {
        System.out.println("getMiejsceSkladowania");
        PrzyjecieZGlownego instance = new PrzyjecieZGlownego();
        String expResult = "";
        String result = instance.getMiejsceSkladowania();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMiejsceSkladowania method, of class PrzyjecieZGlownego.
     */
    @org.junit.Test
    public void testSetMiejsceSkladowania() {
        System.out.println("setMiejsceSkladowania");
        String miejsceSkladowania = "";
        PrzyjecieZGlownego instance = new PrzyjecieZGlownego();
        instance.setMiejsceSkladowania(miejsceSkladowania);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getZnacznikPoczatkowy method, of class PrzyjecieZGlownego.
     */
    @org.junit.Test
    public void testGetZnacznikPoczatkowy() {
        System.out.println("getZnacznikPoczatkowy");
        PrzyjecieZGlownego instance = new PrzyjecieZGlownego();
        BigDecimal expResult = null;
        BigDecimal result = instance.getZnacznikPoczatkowy();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setZnacznikPoczatkowy method, of class PrzyjecieZGlownego.
     */
    @org.junit.Test
    public void testSetZnacznikPoczatkowy() {
        System.out.println("setZnacznikPoczatkowy");
        BigDecimal znacznikPoczatkowy = null;
        PrzyjecieZGlownego instance = new PrzyjecieZGlownego();
        instance.setZnacznikPoczatkowy(znacznikPoczatkowy);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getZnacznikKoncowy method, of class PrzyjecieZGlownego.
     */
    @org.junit.Test
    public void testGetZnacznikKoncowy() {
        System.out.println("getZnacznikKoncowy");
        PrzyjecieZGlownego instance = new PrzyjecieZGlownego();
        BigDecimal expResult = null;
        BigDecimal result = instance.getZnacznikKoncowy();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setZnacznikKoncowy method, of class PrzyjecieZGlownego.
     */
    @org.junit.Test
    public void testSetZnacznikKoncowy() {
        System.out.println("setZnacznikKoncowy");
        BigDecimal znacznikKoncowy = null;
        PrzyjecieZGlownego instance = new PrzyjecieZGlownego();
        instance.setZnacznikKoncowy(znacznikKoncowy);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getZnacznikKoncowyDostepny method, of class PrzyjecieZGlownego.
     */
    @org.junit.Test
    public void testGetZnacznikKoncowyDostepny() {
        System.out.println("getZnacznikKoncowyDostepny");
        PrzyjecieZGlownego instance = new PrzyjecieZGlownego();
        Boolean expResult = null;
        Boolean result = instance.getZnacznikKoncowyDostepny();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setZnacznikKoncowyDostepny method, of class PrzyjecieZGlownego.
     */
    @org.junit.Test
    public void testSetZnacznikKoncowyDostepny() {
        System.out.println("setZnacznikKoncowyDostepny");
        Boolean znacznikKoncowyDostepny = null;
        PrzyjecieZGlownego instance = new PrzyjecieZGlownego();
        instance.setZnacznikKoncowyDostepny(znacznikKoncowyDostepny);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getZnacznikiRosnaco method, of class PrzyjecieZGlownego.
     */
    @org.junit.Test
    public void testGetZnacznikiRosnaco() {
        System.out.println("getZnacznikiRosnaco");
        PrzyjecieZGlownego instance = new PrzyjecieZGlownego();
        Boolean expResult = null;
        Boolean result = instance.getZnacznikiRosnaco();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setZnacznikiRosnaco method, of class PrzyjecieZGlownego.
     */
    @org.junit.Test
    public void testSetZnacznikiRosnaco() {
        System.out.println("setZnacznikiRosnaco");
        Boolean znacznikiRosnaco = null;
        PrzyjecieZGlownego instance = new PrzyjecieZGlownego();
        instance.setZnacznikiRosnaco(znacznikiRosnaco);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
   
}
