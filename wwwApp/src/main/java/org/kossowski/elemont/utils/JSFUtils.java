/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.utils;

import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
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
    
    public static String getRequestParam( String paramName ) {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> pm = fc.getExternalContext().getRequestParameterMap();
        
        
        return pm.get( paramName );
    }
    
    public static String getElementValue( String id ) {
        //UIComponent c = FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
        //c.getValueBinding("value");
        
        //return (String)c.getAttributes().get("value");
        return "";
    }
    
}
