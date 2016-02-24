/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.web.serwis;

/**
 *
 * @author jkossow
 */
public class Printer {

    public static void main(String[] args) throws Exception {
        java.lang.Runtime.getRuntime().exec("lp -d cab_EOS1_300 /Users/jkossow/Downloads/qr1.zpl");
        java.lang.Runtime.getRuntime().exec("lp -d cab_EOS1_300 /Users/jkossow/Downloads/qr2.zpl");
        java.lang.Runtime.getRuntime().exec("lp -d cab_EOS1_300 /Users/jkossow/Downloads/qr3.zpl");
    }
    
}
