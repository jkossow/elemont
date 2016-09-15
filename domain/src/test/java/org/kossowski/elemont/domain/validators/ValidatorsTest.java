package org.kossowski.elemont.domain.validators;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.kossowski.elemont.domain.Odcinek;

/**
 *
 * @author jkossow
 */


public class ValidatorsTest {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUp() throws Exception {
    }

    @AfterMethod
    public void tearDown() throws Exception {
    }
    
    
    @Test
    public void seq1Test() {
        Odcinek o = new Odcinek();
        
       SkanZawieszkiValidator v = SkanZawieszkiValidator.getInstance(true);
       
        assertTrue( v.dobraSekwencja(o) );
        
    }
    
    @Test
    public void seq2Test() {
        Odcinek o = new Odcinek( 1, 2, 3, 4 );
        
       SkanZawieszkiValidator v = SkanZawieszkiValidator.getInstance(true);
       
        assertTrue( v.dobraSekwencja(o) );
        
    }
    
    @Test
    public void seq3Test() {
       Odcinek o = new Odcinek( 1, 5, 2, 4 );
        
       SkanZawieszkiValidator v = SkanZawieszkiValidator.getInstance(true);
       assertFalse( v.dobraSekwencja(o) );
        
    }
    
    @Test
    public void seq4Test() {
       Odcinek o = new Odcinek( 1, 2, 2, 4 );
        
       SkanZawieszkiValidator v = SkanZawieszkiValidator.getInstance(true);
       assertFalse( v.dobraSekwencja(o) );
        
    }
    
    @Test
    public void seq5Test() {
       Odcinek o = new Odcinek( 2, 2, 3, 3 );
        
       SkanZawieszkiValidator v = SkanZawieszkiValidator.getInstance(true);
       assertTrue( v.dobraSekwencja(o) );
        
    }
    
    @Test
    public void seq6Test() {
       Odcinek o = new Odcinek( 1, null, 2, 4 );
        
       SkanZawieszkiValidator v = SkanZawieszkiValidator.getInstance(true);
       assertTrue( v.dobraSekwencja(o) );
        
    }
    
    @Test
    public void seq7Test() {
       Odcinek o = new Odcinek( null, 2, 2, null );
        
       SkanZawieszkiValidator v = SkanZawieszkiValidator.getInstance(true);
       assertFalse( v.dobraSekwencja(o) );
        
    }
    
    public void seq8Test() {
       Odcinek o = new Odcinek( 5 , null, null, 1);
        
       SkanZawieszkiValidator v = SkanZawieszkiValidator.getInstance(true);
       assertFalse(v.dobraSekwencja(o) );
        
    }
    
}
