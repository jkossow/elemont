/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author jkossow
 */
public class JSFUtils {
    
    public static void addMessage( String message ) {
        FacesMessage facesMessage = new FacesMessage( message );
        FacesContext.getCurrentInstance().addMessage(null,  facesMessage);
    }
    
    
}
