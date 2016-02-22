/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.datamodels;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.kossowski.elemont.domain.Jm;
import org.kossowski.elemont.repositories.JmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.kossowski.elemont.repositories.ProjektRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jkossow
 */

@Component
public class JmTableModel extends AbstractTableModel {

    private static String[] columnNames = {"id", "Symbol","Nazwa"};
    
    @Autowired
    protected JmRepository jmRepository;
    
    private List<Jm> aList = null;
    
    @Override
    public int getRowCount() {
       if( aList == null ) makeList();
       
       return aList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        if( aList == null ) makeList(); 
        
        Jm jm = aList.get(rowIndex);
        switch( columnIndex ) {
            case 0: return jm.getId();
            case 1: return jm.getSymbol();
                
            case 2: return jm.getNazwa();
               
        };
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    private List<Jm> makeList() {
        System.out.println("Make a list");
        List<Jm> l = jmRepository.findAll( new Sort( Sort.Direction.ASC, "id") );
        aList = l;
        return l;
    }
    
    // operacje crud
    public void create( Jm jm ) {
        jmRepository.save( jm );
        aList.add(jm);
        int index = aList.indexOf(jm);
        fireTableRowsInserted( aList.indexOf(jm), aList.indexOf(jm) );
    }
    
    public void update( Jm jm ) {
        int index = aList.indexOf(jm);
        jmRepository.save( jm );
        aList.set( index, jm );
        fireTableRowsUpdated( index, index );
    }
    
    public void delete( Jm jm ) {
        System.out.println("Kasuję " + jm);
        int index = aList.indexOf(jm);
        System.out.println("index " + index);
        aList.remove(jm);
        jmRepository.delete( jm );
        fireTableRowsDeleted(index-1,index-1);
    }
    
    public Jm get( int index ) {
        return aList.get(index);
    }
    
    public List<Jm> getList() {
        return aList;
    }
    
}
