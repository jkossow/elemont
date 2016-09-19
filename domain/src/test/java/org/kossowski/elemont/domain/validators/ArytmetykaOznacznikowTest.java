/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.domain.validators;

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
public class ArytmetykaOznacznikowTest {
    
    public ArytmetykaOznacznikowTest() {
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
    public void ilosc1() {
        ArytmetykaOznacznikow a = new ArytmetykaOznacznikowRosnacych();
        
        assertEquals( new BigDecimal(1000), 
                a.ilosc(new BigDecimal(2000), 
                    new BigDecimal(0), new BigDecimal(1000), true));
        
        assertEquals( new BigDecimal(1000), 
                a.ilosc(new BigDecimal(2000), 
                    new BigDecimal(1000), new BigDecimal(0), true));
        
        assertEquals( new BigDecimal(1000), 
                a.ilosc(new BigDecimal(1000), 
                    new BigDecimal(34), new BigDecimal(1000), false));
    }
}
