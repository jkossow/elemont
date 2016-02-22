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
import org.kossowski.elemont.domain.Projekt;
import org.kossowski.elemont.repositories.JmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.kossowski.elemont.repositories.ProjektRepository;

/**
 *
 * @author jkossow
 */

@Component
public class ProjektTableModel extends AbstractTableModel {

    private static String[] columnNames = {"id", "Symbol","Nazwa"};
    
    @Autowired
    protected ProjektRepository projektRepository;
    
    private List<Projekt> aList = null;
    
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
        
        Projekt p = aList.get(rowIndex);
        switch( columnIndex ) {
            case 0: return p.getId();
            case 1: return p.getSymbol();
            case 2: return p.getNazwa();
               
        };
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    private List<Projekt> makeList() {
        System.out.println("Make a list");
        List<Projekt> l = projektRepository.findAll( new Sort( Sort.Direction.ASC, "id") );
        aList = l;
        return l;
    }
    
    // operacje crud
    public void create( Projekt p ) {
        projektRepository.save( p );
        aList.add(p);
        int index = aList.indexOf(p);
        fireTableRowsInserted( aList.indexOf(p), aList.indexOf(p) );
    }
    
    public void update( Projekt p ) {
        int index = aList.indexOf(p);
        projektRepository.save( p );
        aList.set( index, p );
        fireTableRowsUpdated( index, index );
    }
    
    public void delete( Projekt p ) {
        System.out.println("Kasuję " + p);
        int index = aList.indexOf(p);
        System.out.println("index " + index);
        aList.remove(p);
        projektRepository.delete( p );
        fireTableRowsDeleted(index-1,index-1);
    }
    
    public Projekt get( int index ) {
        return aList.get(index);
    }
    
    public List<Projekt> getList() {
        return aList;
    }
    
}
