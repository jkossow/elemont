/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.web.operacje;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.kossowski.elemont.domain.KartaMagazynowa;
import org.kossowski.elemont.domain.Material;
import org.kossowski.elemont.domain.Operacja;
import org.kossowski.elemont.domain.Producent;
import org.kossowski.elemont.domain.Projekt;
import org.kossowski.elemont.domain.Umowa;
import org.kossowski.elemont.domain.operacje.PrzyjecieZGlownego;
import org.kossowski.elemont.repositories.KartaMagazynowaRepository;
import org.kossowski.elemont.repositories.MaterialRepository;
import org.kossowski.elemont.repositories.OperacjaRepository;
import org.kossowski.elemont.repositories.ProducentRepository;
import org.kossowski.elemont.repositories.ProjektRepository;
import org.kossowski.elemont.repositories.UmowaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/**
 *
 * @author jkossow
 */

@Service
@Scope("request")
public class KartaIPrzyjecieBean implements Serializable {
    
    @Autowired
    protected MaterialRepository matRepo;
    
    @Autowired
    protected ProducentRepository prodRepo;
    
    @Autowired
    protected UmowaRepository umowyRepo;
    
    @Autowired
    protected ProjektRepository projektRepo;
    
    @Autowired
    protected KartaMagazynowaRepository kmRepo;
    
    @Autowired
    protected OperacjaRepository opRepo;
    
    //KartaMagazynowa km = new KartaMagazynowa();
    protected PrzyjecieZGlownego przyjecie =new PrzyjecieZGlownego();
     
    private BigDecimal ilosc;
    
    private Long id;
    
    
    
    public KartaIPrzyjecieBean() {
        Logger.getAnonymousLogger().log( Level.INFO, "Konstruktor PrzyjecieBean");
    }
    
    public Map<String,Material> getMaterialSelectItemsMap() {
        Map<String,Material> map = new HashMap<String,Material>();
    
        map.put( "-----", null );
        for( Material m : matRepo.findAll() )
            map.put( m.getNazwa(), m);
        
        return map;
    }
    
    
    public List<SelectItem> getMaterialSelectItems() {
        
        
        List<SelectItem> si = new ArrayList<>();
        
        si.add( new SelectItem( new Material() , "---", "", false, false, true ));
        for( Material m : matRepo.findAll() ) 
            si.add( new SelectItem( m, m.getIndeks()) );
        
        return si;
    }
    
    public List<Material> getMaterialy() {
        return matRepo.findAll();
    }
    
    public List<Producent> getProducenci() {
        return prodRepo.findAll();
    }
    
    
    
    public List<Projekt> getProjekty() {
        return projektRepo.findAll();
    }
    
    public Material getEmptyMaterial() {
        return new Material();
    }
    
      
    
    public String save() {
        //System.out.println("początek metody save PrzujeciaIKartaBean");
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("You've registered"));
      
        KartaMagazynowa km = new KartaMagazynowa();
        
        //przyjecie.setKartaMagazynowa(km);
        
        km.addOperation(przyjecie);
        
        try {
            przyjecie.accept();
        } catch ( Exception e) { e.printStackTrace();};
        
        km = kmRepo.save( km );
        
      /*
      Logger.getAnonymousLogger().info( "zapisuję {}");
      Operacja o = new PrzyjecieZGlownego( ilosc );
      km.addOperation(o);
      try {
        o.accept();
      } catch ( Exception e) { e.printStackTrace();};
      kmRepo.save( km );
      */
      return "/faces/commons/karta_mag/list.xhtml";
      
    };
    
    

      public BigDecimal getIlosc() {
        return ilosc;
    }

    public void setIlosc(BigDecimal ilosc) {
        this.ilosc = ilosc;
    }

    public PrzyjecieZGlownego getPrzyjecie() {
        return przyjecie;
    }

    public void setPrzyjecie(PrzyjecieZGlownego przyjecie) {
        this.przyjecie = przyjecie;
    }

   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
   
    
    
   
    
}
