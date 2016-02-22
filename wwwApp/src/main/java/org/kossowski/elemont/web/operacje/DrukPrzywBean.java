/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.web.operacje;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 *
 * @author jkossow
 */

@Service
@Scope("request")
public class DrukPrzywBean {
    
    private int ilosc = 1;
    private Long kartaId = 0L;
    
    
    public String preDruk() {
        return "/commons/operacje/drukPrzyw.xhtml";
    }
    
    public String drukuj() {
        
        for( int i = 0; i < ilosc; i++ ) {
            //Operacja o = new PrzypisanieQR2(karta, kodQR2)
            System.out.println("drukuje");
        }
        
        return "list.xhtml";
    };

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }
    
    
    
}
