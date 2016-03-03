package org.kossowski.elemont.domain;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.math.BigDecimal;
import javafx.scene.control.SplitPane;
import org.kossowski.elemont.domain.Odcinek;
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
public class OdcinekTest { 
    
    public OdcinekTest() {
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
    public void odcinekSetTest() {
        Odcinek o = new Odcinek();
        o.setA1(BigDecimal.ONE); 
        o.setB1(BigDecimal.ONE); 
        o.setA2(BigDecimal.ONE); 
        o.setB2(BigDecimal.ONE); 
        
        assertEquals(o.getA1(), BigDecimal.ONE);
        assertEquals(o.getB1(), BigDecimal.ONE);
        assertEquals(o.getA2(), BigDecimal.ONE);
        assertEquals(o.getB2(), BigDecimal.ONE);
    }
    
    @Test
    public void odcinekUsedTest() {
        Odcinek o = new Odcinek();
        
        assertFalse( o.isUzyty() );
        
        o.setA1( BigDecimal.ONE ); 
        assertTrue( o.isUzyty() );
    }
    
    @Test
    public void odcinekWithIdTest() {
       // Odcinek o = new Odcinek("1111A");
       // assertEquals(o.getId(), "1111");
        
       // Odcinek o1 = new Odcinek("1112B");
        
    }
    
    @Test
    public void odcinekEqualsTest() {
        
    }
    
    @Test 
    public void isSet() {
        Odcinek o = new Odcinek();
        assertFalse( o.isSetN("A2"));
                
        o.setA2( BigDecimal.TEN );
        assertTrue( o.isSetN("A2"));
    }
}
