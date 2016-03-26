/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.rest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jkossow
 */

@RestController
@RequestMapping("getHistoria")
@Scope("request")
public class HistoriaRestBean {
    
    @ResponseBody
    @RequestMapping( value="/{login}", method = RequestMethod.GET, produces = "application/json" )
    public List<HistoriaItem> getHistoria() {
        
        ArrayList<HistoriaItem> historia = new ArrayList<>();
        historia.add(new HistoriaItem( 1L,  123L, new BigDecimal("50"), new BigDecimal("48"), new BigDecimal("2") ));
        historia.add(new HistoriaItem( 2L,  234L, new BigDecimal("34"), null, null ));
        historia.add(new HistoriaItem( 3L,  545L, new BigDecimal("33"), null, null ));
        
        return historia;
    }
}
