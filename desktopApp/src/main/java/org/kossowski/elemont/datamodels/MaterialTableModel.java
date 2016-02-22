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
import org.kossowski.elemont.repositories.JmRepository;
import org.kossowski.elemont.repositories.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.kossowski.elemont.repositories.ProjektRepository;

/**
 *
 * @author jkossow
 */

@Component
public class MaterialTableModel extends AbstractTableModel {

    private static String[] columnNames = {"id", "Indeks","Nazwa"};
    
    @Autowired
    protected MaterialRepository matRepository;
    
    private List<Material> aList = null;
    
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
        
        Material m = aList.get(rowIndex);
        switch( columnIndex ) {
            case 0: return m.getId();
            case 1: return m.getIndeks();
            case 2: return m.getNazwa();
        };
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    private List<Material> makeList() {
        System.out.println("Make a list");
        List<Material> l = matRepository.findAll( new Sort( Sort.Direction.ASC, "id") );
        aList = l;
        return l;
    }
    
    // operacje crud
    public void create( Material m ) {
        matRepository.save( m );
        aList.add(m);
        int index = aList.indexOf(m);
        fireTableRowsInserted( aList.indexOf(m), aList.indexOf(m) );
    }
    
    public void update( Material m ) {
        int index = aList.indexOf(m);
        matRepository.save( m );
        aList.set( index, m );
        fireTableRowsUpdated( index, index );
    }
    
    public void delete( Material m ) {
        int index = aList.indexOf(m);
        aList.remove(m);
        matRepository.delete( m );
        fireTableRowsDeleted(index-1,index-1);
    }
    
    public Material get( int index ) {
        return aList.get(index);
    }
    
    public List<Material> getList() {
        return aList;
    }
    
}
