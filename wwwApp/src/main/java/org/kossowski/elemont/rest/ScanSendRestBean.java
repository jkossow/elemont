/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.rest;

import java.math.BigDecimal;
import java.util.Date;
import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jkossow
 */

@RestController
@RequestMapping("sendScan")
@Scope("request")
public class ScanSendRestBean {

    public ScanSendRestBean() {
        System.out.println("konstruktor ScanSendRestBean");
    }
    
    @ResponseBody
    @RequestMapping( value="/{data1}/{data2}/{date}/{tryb}", method = RequestMethod.PUT, produces = "application/json" )
    public String skanZawieszki(
        @PathVariable(value="data1") String data1,
        @PathVariable(value="data2") String data2,
        @PathVariable(value="date")  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date,
        @PathVariable(value="tryb") int tryb ) {
            
            System.out.println("date1 " + data1);
            System.out.println("date2 " + data2);
            System.out.println("date2 " + date);
            System.out.println("tryb " +  tryb);
   
            
        switch (tryb) {
            case 1: System.out.println("t1");break;
            case 2: System.out.println("t2");break;
            case 3: System.out.println("t3");break;
            case 4: System.out.println("t4");break;
            case 5: System.out.println("t5");break;
                
        }
        return "ok";
    }
}