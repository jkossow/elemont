/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.web.commons;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.faces.context.FacesContext;
import org.kossowski.elemont.domain.Grupa;
import org.kossowski.elemont.domain.Projekt;
import org.kossowski.elemont.domain.User;
import org.kossowski.elemont.repositories.GrupaRepository;
import org.kossowski.elemont.repositories.ProjektRepository;
import org.kossowski.elemont.repositories.UserRepository;
import org.primefaces.model.DualListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author jkossow
 */

@Controller
@Scope("request")
public class ProjektBean {
    
    @Autowired
    private ProjektRepository projektRepo;
    
    @Autowired
    private UserRepository userRepo;
    
    private Projekt projekt = new Projekt();
    
    private DualListModel<User> pickData;
    
    
    public List<Projekt> getFindAll() {
        return projektRepo.findAll();
    }
    
    public String delete() {
        
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> pm = fc.getExternalContext().getRequestParameterMap();
        
        Long id  = new Long( pm.get("id") );
        
        projekt = projektRepo.findOne(id);
        projektRepo.delete( projekt );
        
        return "list.xhtml";
        
    }

    public String prepAdd() {
        
        List<User> source = userRepo.findAll();
        List<User> target = projekt.getZespol();
        
        pickData = new DualListModel<User>( source, target );
        return "add.xhtml";
    }
    
    public String prepEdit() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> pm = fc.getExternalContext().getRequestParameterMap();
        
        Long id  = new Long( pm.get("id") );
        //User u;
        projekt = projektRepo.findOne(id);
        
        List<User> source = userRepo.findAll();
        List<User> target = projekt.getZespol();
        
        
        System.out.println("source start");
        for( User u1: source )
            System.out.println( u1.getNazwisko() + " " + u1.getImie() );
        System.out.println("source stop");
        
        System.out.println("target start");
        for( User u1: target )
            System.out.println( u1.getNazwisko() + " " + u1.getImie() );
        System.out.println("target stop");
        
        //for( User u : source)
        //    if( target.contains(u)) {
        //        System.out.println("Do usunięcia "  + u.getNazwisko());
        //        //source.remove(u);
        //    }
        source.removeAll(target);
        
        System.out.println("target start po korekcie");
        for( User u1: target )
            System.out.println( u1.getNazwisko() + " " + u1.getImie() );
        System.out.println("target stop po korekcie");
        
        pickData = new DualListModel<User>( source, target );
        
        return "edit.xhtml";
    }
    
    public String save() {
        
        //System.out.println("source start");
        //for( User u1: pickData.getSource() )
        //    System.out.println( u1.getNazwisko() + " " + u1.getImie() );
        //System.out.println("source stop");
        
        //System.out.println("target start");
        //for( User u1: pickData.getTarget() )
        //    System.out.println( u1.getNazwisko() + " " + u1.getImie() );
        //System.out.println("target stop");
       
        projekt.setZespol( pickData.getTarget() );
        projektRepo.save( projekt );
        return "list.xhtml";
    }
    
    
    public Projekt getProjekt() {
        return projekt;
    }

    public void setProjekt(Projekt projekt) {
        this.projekt = projekt;
    }

    public DualListModel<User> getPickData() {
        return pickData;
    }

    public void setPickData(DualListModel<User> pickData) {
        this.pickData = pickData;
    }
    
    
    
    
}
