/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.web.serwis;

import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 *
 * @author jkossow
 */
public class GregorianCal {
    public static void main(String[] args) {
        System.out.println( GregorianCalendar.getInstance(TimeZone.getTimeZone("pl_PL")).getTime());
    }
}
