/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.datamodels;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import org.kossowski.elemont.domain.Jm;

/**
 *
 * @author jkossow
 */
public class JmComboBoxRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        
        try {
            Jm jm = (Jm)value;
            if( value != null ) {
                value = jm.getSymbol();
            }
            
        } catch (ClassCastException e ) {
            System.out.println("Wyjatek jm ->" + value);
        }
            
        
        return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
