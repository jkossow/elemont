/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.web.commons;

import java.io.Serializable;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import org.kossowski.elemont.domain.KartaMagazynowa;
import org.kossowski.elemont.domain.Status;
import org.kossowski.elemont.repositories.KartaMagazynowaRepository;
import org.kossowski.elemont.security.SecurityController;
import org.kossowski.elemont.utils.JSFUtils;
import org.primefaces.event.TabChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 *
 * @author jkossow
 */

@Service
@Scope("request")
public class KartaMagazynowaBean implements Serializable {
    
    @Autowired
    protected SecurityController secController; 
    
    @Autowired
    private KartaMagazynowaRepository kartaRepo;
    
    private KartaMagazynowa selected;
    
    private KartaMagazynowa detailed;
    
    
   
    
    public KartaMagazynowaBean() {
        
    }
    
    public List<KartaMagazynowa> getFindAll() {
        return kartaRepo.findAll();
    }
    
    public List<KartaMagazynowa> findByKartaIOdcinki() {
        return kartaRepo.findByKartaIOdcinki(secController.getUser() ) ;
    }
    
    public List<KartaMagazynowa> getFindAllNaBudowie() {
        return kartaRepo.findAllByStatusIn( Status.naBudowie() );
    }

    public KartaMagazynowa getSelected() {
        return selected;
    }

    public void setSelected(KartaMagazynowa selected) {
        this.selected = selected;
    }

    public KartaMagazynowa getDetailed() {
        
        return detailed;
    }

    public void setDetailed(KartaMagazynowa detailed) {
        this.detailed = detailed;
    }

   
    
    
  
    public String remove() {
        
        String s = JSFUtils.getRequestParam("id");
        Long id = new Long( s );
        
        kartaRepo.delete(id);
        
        return "";
    }
    
    public String preDetails() {
        
        String s = JSFUtils.getRequestParam("id");
        Long id = new Long( s );
        
        
        detailed = kartaRepo.findOne(id);
        return "details.xhtml";
    }
    
    public String details() {
        System.out.println("Detale");
        return "details.xhtml";
    }
    
  
    
    public String component() {
        //UIComponent c = FacesContext.getCurrentInstance()
        //            .getViewRoot().findComponent("formDetail:kartaId");
        //    String id = (String) c.getValueExpression("value")
        //            .getValue( FacesContext.getCurrentInstance().getELContext());
        //System.out.println("component id =" + id);
        
        //System.out.println("Component(jk) " + hiddenInput.getValue().getClass().getName() );
        //System.out.println( "Comp val = " + hiddenInput.getValue() );
        return "";
    }
    
}
