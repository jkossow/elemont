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
import org.kossowski.elemont.domain.Material;
import org.kossowski.elemont.domain.Stanowisko;
import org.kossowski.elemont.repositories.JmRepository;
import org.kossowski.elemont.repositories.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.kossowski.elemont.repositories.ProjektRepository;
import org.kossowski.elemont.repositories.StanowiskoRepository;

/**
 *
 * @author jkossow
 */

@Component
public class StanowiskoTableModel extends AbstractTableModel {

    private static String[] columnNames = {"id", "Symbol","Nazwa"};
    
    @Autowired
    protected StanowiskoRepository stanowiskoRepository;
    
    private List<Stanowisko> aList = null;
    
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
        
        Stanowisko s = aList.get(rowIndex);
        switch( columnIndex ) {
            case 0: return s.getId();
            case 1: return s.getSymbol();
            case 2: return s.getNazwa();
        };
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    private List<Stanowisko> makeList() {
        System.out.println("Make a list");
        List<Stanowisko> l = stanowiskoRepository.findAll( new Sort( Sort.Direction.ASC, "id") );
        aList = l;
        return l;
    }
    
    // operacje crud
    public void create( Stanowisko s ) {
        stanowiskoRepository.save(s );
        aList.add(s);
        int index = aList.indexOf(s);
        fireTableRowsInserted(aList.indexOf(s), aList.indexOf(s) );
    }
    
    public void update( Stanowisko s ) {
        int index = aList.indexOf(s);
        stanowiskoRepository.save(s );
        aList.set(index, s );
        fireTableRowsUpdated( index, index );
    }
    
    public void delete( Stanowisko s ) {
        int index = aList.indexOf(s);
        aList.remove(s);
        stanowiskoRepository.delete(s );
        fireTableRowsDeleted(index-1,index-1);
    }
    
    public Stanowisko get( int index ) {
        return aList.get(index);
    }
    
    public List<Stanowisko> getList() {
        return aList;
    }
    
}
