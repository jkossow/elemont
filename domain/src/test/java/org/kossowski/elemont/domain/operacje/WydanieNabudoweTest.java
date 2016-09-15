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
import org.kossowski.elemont.domain.User;
import org.kossowski.elemont.domain.operacje.PrzyjecieZGlownego;
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
public class WydanieNabudoweTest {
    
    public WydanieNabudoweTest() {
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
    
    //normalny cykl operacji
    @Test
    public void wydanieNaBudoweTest() throws Exception {
        KartaMagazynowa k = new KartaMagazynowa();
        
        System.out.println( "Stan poczotkowy " + k.getStanIl() );
        Operacja o1 = new PrzyjecieZGlownego( new User(), new Date(), new BigDecimal(100 ));
        k.addOperation(o1);
        o1.accept();
        System.out.println( "Stan po przyjeciu " + k.getStanIl() );
        Operacja o2 = new WydanieNaBudowe( new User(), new Date(), new BigDecimal( 100 ), new User() );
        k.addOperation(o2);
        o2.accept();
        System.out.println( "Stan po wydaniu " + k.getStanIl() );
        System.out.println( "Status po wydaniu " + k.getStatus() );
        assertEquals( k.getStanIl(), new Stan( new int[]{100,0,100,0,0,0,0,100 } ));
        
    }
    
    @Test( expectedExceptions = IllegalStatusException.class )
    public void powtorneWydanieNaBudoweTest() throws Exception {
        KartaMagazynowa k = new KartaMagazynowa();
        
        System.out.println( "Stan poczotkowy " + k.getStanIl() );
        Operacja o1 = new PrzyjecieZGlownego( new User(), new Date(), new BigDecimal(100 ));
        k.addOperation(o1);
        o1.accept();
        System.out.println( "Stan po przyjeciu " + k.getStanIl() );
        Operacja o2 = new WydanieNaBudowe( new User(), new Date(), new BigDecimal( 50 ), new User() );
        k.addOperation(o2);
        o2.accept();
        System.out.println( "Stan po wydaniu " + k.getStanIl() );
        System.out.println( "Status po wydaniu " + k.getStatus() );
        assertEquals( k.getStanIl(), new Stan( new int[]{100,50,50,0,0,0,0,50 } ));
        
        //drugie wydanie
        Operacja o3 = new WydanieNaBudowe( new User(), new Date(), new BigDecimal( 50 ), new User() );
        k.addOperation(o3);
        o3.accept();
        
    }
    
    // zbyt wczesne wydanie - przy statusie S0
    @Test( expectedExceptions = IllegalStatusException.class )
    public void wydanieNaBudoweTest1()  throws IllegalStatusException, Exception {
        KartaMagazynowa k = new KartaMagazynowa();
        
        Operacja o = new WydanieNaBudowe( new User(), new Date(), BigDecimal.ONE, new User() );
        k.addOperation(o);
        o.accept();
    }
    
    //@Test( expectedExceptions = NullPointerException.class )
    //public void wydanieWithNull() {
    //    Operacja o = new WydanieNaBudowe( new User(), BigDecimal.ONE);
    //}

    
}
