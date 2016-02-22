/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.domain;

import java.math.BigDecimal;
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
public class StanTest {
    
    public StanTest() {
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
    public void test1() {
        Stan s = new Stan();
        s.setIValue(0, new BigDecimal(0));
        s.setIValue(1, new BigDecimal(1));
        s.setIValue(2, new BigDecimal(2));
        s.setIValue(3, new BigDecimal(3));
        s.setIValue(4, new BigDecimal(4));
        s.setIValue(5, new BigDecimal(5));
        s.setIValue(6, new BigDecimal(6));
        
        assertEquals( s.getIValue(0), new BigDecimal(0) );
        assertEquals( s.getIValue(1), new BigDecimal(1) );
        assertEquals( s.getIValue(2), new BigDecimal(2) );
        assertEquals( s.getIValue(3), new BigDecimal(3) );
        assertEquals( s.getIValue(4), new BigDecimal(4) );
        assertEquals( s.getIValue(5), new BigDecimal(5) );
        assertEquals( s.getIValue(6), new BigDecimal(6) );
    }
    
    @Test( expectedExceptions = IllegalStockSizeException.class )
    public void stanIntArrayContructorException() {
        Stan s = new Stan( new int[]{1} );
    }
    
    public void stanIntArrayContructor() {
        Stan s = new Stan( new int[]{ 11,12,13,14,15,16,17} );
        assertEquals(s.getIValue(0), new BigDecimal(11));
        assertEquals(s.getIValue(1), new BigDecimal(12));
        assertEquals(s.getIValue(2), new BigDecimal(13));
        assertEquals(s.getIValue(3), new BigDecimal(14));
        assertEquals(s.getIValue(4), new BigDecimal(15));
        assertEquals(s.getIValue(5), new BigDecimal(16));
        assertEquals(s.getIValue(6), new BigDecimal(17));
    }
    
    @Test
    public void stanEquals1() {
        Stan s1 = new Stan();
        Stan s2 = new Stan();
        
        assertEquals(s1, s2);
        
    }
    
    @Test
    public void stanEquals2() {
        Stan s1 = new Stan( new int[]{100,101,102,103,104,105,106,107} );
        Stan s2 = new Stan( new int[]{100,101,102,103,104,105,106,107} );
        
        assertEquals(s1, s2);
        
    }
    
    @Test
    public void stanNotEqual() {
        Stan s1 = new Stan( new int[]{99,101,102,103,104,105,106,107} );
        Stan s2 = new Stan( new int[]{100,101,102,103,104,105,106,107} );
        
        assertNotEquals(s1, s2);
        
    }
    
    
}
