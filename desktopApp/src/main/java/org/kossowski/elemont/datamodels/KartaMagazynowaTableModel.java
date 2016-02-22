/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.datamodels;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.kossowski.elemont.domain.KartaMagazynowa;
import org.kossowski.elemont.repositories.KartaMagazynowaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

/**
 *
 * @author jkossow
 */

@Component
public class KartaMagazynowaTableModel extends AbstractTableModel {

    private static String[] columnNames = {"id", "Material","Projekt"};
    
    @Autowired
    protected KartaMagazynowaRepository kartaMagRepository;
    
    private List<KartaMagazynowa> aList = null;
    
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
        
        KartaMagazynowa k = aList.get(rowIndex);
        switch( columnIndex ) {
            case 0: return k.getId();
            case 1: return k.getMaterial().getNazwa();
                
            case 2: return k.getProjekt().getSymbol();
               
        };
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    private List<KartaMagazynowa> makeList() {
        System.out.println("Make a list");
        List<KartaMagazynowa> l = kartaMagRepository.findAll( new Sort( Sort.Direction.ASC, "id") );
        aList = l;
        return l;
    }
    
    // operacje crud
    public void create( KartaMagazynowa k ) {
        kartaMagRepository.save( k );
        aList.add( k );
        int index = aList.indexOf( k );
        fireTableRowsInserted( aList.indexOf( k ), aList.indexOf( k) );
    }
    
    public void update( KartaMagazynowa k ) {
        int index = aList.indexOf(k);
        kartaMagRepository.save(k );
        aList.set(index, k );
        fireTableRowsUpdated( index, index );
    }
    
    public void delete( KartaMagazynowa k ) {
        System.out.println("Kasuję " + k);
        int index = aList.indexOf(k);
        System.out.println("index " + index);
        aList.remove(k);
        kartaMagRepository.delete(k );
        fireTableRowsDeleted(index-1,index-1);
    }
    
    public KartaMagazynowa get( int index ) {
        return aList.get(index);
    }
    
    public List<KartaMagazynowa> getList() {
        return aList;
    }
    
}
