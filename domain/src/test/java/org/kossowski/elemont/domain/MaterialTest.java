/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.domain;

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
public class MaterialTest {
    
    public MaterialTest() {
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
    public void equalsTest() {
        Material m1 = new Material();
        Material m2 = new Material();
        m1.setId(2L);
        m2.setId(2L);
        m1.setNazwa("dddd");
        assertTrue( m1.equals(m2));
    }
    
    @Test
    public void equalsTest1() {
        Material m1 = new Material();
        Material m2 = new Material();
        m1.setId(2L);
        m2.setId(3L);
        //assertFalse( m1.equals(m2));
    }
    
    @Test
    public void equalsTest3() {
        Material m1 = new Material();
        Material m2 = null;
        m1.setId(2L);
        //m2.setId(2L);
        //assertFalse(m1.equals(m2));
    }
}
