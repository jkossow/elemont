/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;

/**
 *
 * @author jkossow
 */
@Embeddable
public class Stan implements Serializable {
    
    public static final int SIZE = 8;
    
    public static final int IL_PRZYJETA = 0;
    public static final int IL_W_MAG_GL = 1;
    public static final int IL_WYDANA_NA_BUD = 2;
    public static final int IL_POLOZONA = 3;
    public static final int IL_PODLACZONA = 4;
    public static final int IL_SCINKOW = 5;
    public static final int IL_INNE = 6;
    public static final int IL_STAN_BIEZ =7;
    
    //private static final ArrayList<Stan> naBudowie = Collections.
    
    @ElementCollection
    private List<BigDecimal> stan = new ArrayList<BigDecimal>( SIZE );

    public List<BigDecimal> getStan() {
        return stan;
    }

    
    public Stan() {
        for( int i = 0; i < SIZE; i++ ) {
            stan.add( new BigDecimal( 0 ) );
        }
    }
    
    public Stan( int[] s ) throws IllegalStockSizeException {
        if( s.length  != SIZE )
            throw  new IllegalStockSizeException();
        for( int i = 0; i < SIZE; i++ ) {
            stan.add( new BigDecimal( s[i] ) );
        }
    }
    
    public void setStan(List<BigDecimal> stan) {
        this.stan = stan;
    }
    
    public void setIValue( int i, BigDecimal v) {
        stan.set(i, v);
    }
    
    public BigDecimal getIValue( int i ) {
        return stan.get(i);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder( "[ " );
        for( int i = 0; i < SIZE ; i ++ ) {
            s.append( i );
            s.append( "=>");
            s.append( getIValue(i).toPlainString() );
            if( i != SIZE - 1)
                s.append(',');
            s.append( "  " );
        }
        s.append("]");
        return "Stan{" + "stan=" + s + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.stan);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Stan other = (Stan) obj;
        if (!Objects.equals(this.stan, other.stan)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}
