/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.rest;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author jkossow
 */
public class HistoriaItem implements Serializable {

    private Long lp;
    private Long idOdcinka;
    private BigDecimal dlugosc;
    private BigDecimal podlaczono;
    private BigDecimal dlScinek;

    public HistoriaItem() {
    }

    public HistoriaItem(Long lp, Long idOdcinka, BigDecimal dlugosc, BigDecimal podlaczono, BigDecimal dlScinek) {
        this.lp = lp;
        this.idOdcinka = idOdcinka;
        this.dlugosc = dlugosc;
        this.podlaczono = podlaczono;
        this.dlScinek = dlScinek;
    }
    
    //public String getLine() {
    //    StringBuilder sb = new StringBuilder();
    //    sb.append(lp).append("|").
    //       append(idOdcinka).append("|").
    //       append(dlugosc).append("|").
    //       append(podlaczono).append("|").
    //       append(dlScinek).append(";");
    //    
    //    return sb.toString();
    //}

    public Long getLp() {
        return lp;
    }

    public void setLp(Long lp) {
        this.lp = lp;
    }

    public Long getIdOdcinka() {
        return idOdcinka;
    }

    public void setIdOdcinka(Long idOdcinka) {
        this.idOdcinka = idOdcinka;
    }

    public BigDecimal getDlugosc() {
        return dlugosc;
    }

    public void setDlugosc(BigDecimal dlugosc) {
        this.dlugosc = dlugosc;
    }

    public BigDecimal getPodlaczono() {
        return podlaczono;
    }

    public void setPodlaczono(BigDecimal podlaczono) {
        this.podlaczono = podlaczono;
    }

    public BigDecimal getDlScinek() {
        return dlScinek;
    }

    public void setDlScinek(BigDecimal dlScinek) {
        this.dlScinek = dlScinek;
    }
    
    
    
}
