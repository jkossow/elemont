package org.kossowski.elemont.domain;

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

/**
 *
 * @author jkossow
 */
public class KartaMagazynowaTest {
    
    public KartaMagazynowaTest() {
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
    public void operacjeSizeTest1() {
        
        KartaMagazynowa k = new KartaMagazynowa();
        assertEquals( k.getOperacje().size() , 0 );
     }
    
    @Test
    public void operacjeSizeTest2() {
        
        KartaMagazynowa k = new KartaMagazynowa();
        k.getOperacje().add( new Operacja( k ) );
        assertEquals( k.getOperacje().size() , 1 );
     }
    
    
    @Test
    public void operacjeSizeTest3() {
        
        int size = 1000;
        
        KartaMagazynowa k = new KartaMagazynowa();
        for( int i = 0; i < size; i++ )
            k.getOperacje().add( new Operacja( k ) );
        assertEquals( k.getOperacje().size() , size );
     }
    
    @Test 
     public void stanInitState() {
        KartaMagazynowa k = new KartaMagazynowa();
        assertEquals( k.getStanIl(), new Stan( new int[] {0,0,0,0,0,0,0,0}) );
    }
     
    @Test 
     public void stanSetState() {
        KartaMagazynowa k = new KartaMagazynowa();
        Stan s = new Stan( new int[] {0,0,0,10,0,0,0,0} );
        k.setStanIl(s);
        assertEquals( k.getStanIl(), s );
    }
     
    
}
