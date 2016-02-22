/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.datamodels;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.kossowski.elemont.domain.Grupa;
import org.kossowski.elemont.domain.Jm;
import org.kossowski.elemont.repositories.GrupaRepository;
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
public class GrupaTableModel extends AbstractTableModel {

    private static String[] columnNames = {"id", "Symbol","Nazwa"};
    
    @Autowired
    protected GrupaRepository grupaRepository;
    
    private List<Grupa> aList = null;
    
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
        
        Grupa grupa = aList.get(rowIndex);
        switch( columnIndex ) {
            case 0: return grupa.getId();
            case 1: return grupa.getSymbol();
                
            case 2: return grupa.getNazwa();
               
        };
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    private List<Grupa> makeList() {
        System.out.println("Make a list");
        List<Grupa> l = grupaRepository.findAll( new Sort( Sort.Direction.ASC, "id") );
        aList = l;
        return l;
    }
    
    // operacje crud
    public void create( Grupa grupa ) {
        grupaRepository.save( grupa );
        aList.add( grupa );
        int index = aList.indexOf( grupa );
        fireTableRowsInserted( aList.indexOf( grupa ), aList.indexOf( grupa ));
    }
    
    public void update( Grupa grupa ) {
        int index = aList.indexOf( grupa );
        grupaRepository.save( grupa );
        aList.set( index, grupa );
        fireTableRowsUpdated( index, index );
    }
    
    public void delete( Grupa grupa ) {
        int index = aList.indexOf( grupa );
        aList.remove( grupa );
        grupaRepository.delete( grupa );
        fireTableRowsDeleted(index-1,index-1);
    }
    
    public Grupa get( int index ) {
        return aList.get(index);
    }
    
    public List<Grupa> getList() {
        return aList;
    }
    
}
