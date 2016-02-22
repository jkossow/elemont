/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.web.operacje;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.model.SelectItem;
import org.kossowski.elemont.domain.KartaMagazynowa;
import org.kossowski.elemont.domain.Material;
import org.kossowski.elemont.domain.Producent;
import org.kossowski.elemont.domain.Projekt;
import org.kossowski.elemont.domain.Umowa;
import org.kossowski.elemont.repositories.KartaMagazynowaRepository;
import org.kossowski.elemont.repositories.MaterialRepository;
import org.kossowski.elemont.repositories.ProducentRepository;
import org.kossowski.elemont.repositories.ProjektRepository;
import org.kossowski.elemont.repositories.UmowaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author jkossow
 */

@Controller
@Scope("request")
public class NowaKartaBean implements Serializable{
    
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
    
    private long materialId = 0;
    private long producentId =0;
    private long umowaId = 0;
    private long projektId = 0;
    
    
    private Material material;
    
    
    
    
    
    
    
    public NowaKartaBean() {
        Logger.getAnonymousLogger().log( Level.INFO, "Konstruktor Nowa KartaBean");
    }
    
    
    public Collection<SelectItem> getMaterialSelectItems() {
        
        List<Material> lm = matRepo.findAll();
        List<SelectItem> si = new ArrayList<>();
        
        si.add( new SelectItem( new Material() , "---" ));
        for( Material m : lm ) 
            si.add( new SelectItem( m, m.getIndeks()) );
        
        return si;
    }
    
    public List<Material> getMaterialy() {
        return matRepo.findAll();
    }
    
    public List<Producent> getProducenci() {
        return prodRepo.findAll();
    }
    
    public List<Umowa> getUmowy() {
        return umowyRepo.findAll();
    }
    
    public List<Projekt> getProjekty() {
        return projektRepo.findAll();
    }
    
    
    public void onMaterialChange() {
        System.out.println("Zmiana materialu " + materialId );
        setMaterial( matRepo.findOne( getMaterialId() ));
        
    }
    
    public String save() {
      KartaMagazynowa km = new KartaMagazynowa();
      km.setMaterial( matRepo.findOne( getMaterialId() ));
      km.setProducent( prodRepo.findOne( getProducentId() ));
      //km.setUmowa( umowyRepo.findOne( getUmowaId() ));
      // km.setProjekt( projektRepo.findOne( getProducentId()));
            
         
      Logger.getAnonymousLogger().log( Level.INFO, "zapisuję {0}", km);
      
      kmRepo.save( km );
      
      return "/faces/commons/karta_mag/list.xhtml";
              
    };
    
    public String prepareEdit() {
        return "";
    }

    public long getProjektId() {
        return projektId;
    }

    public void setProjektId(long projektId) {
        this.projektId = projektId;
    }

    
    
    
    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

   

    

   

   
    
    
    

    public long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(long materialId) {
        this.materialId = materialId;
    }

    public long getProducentId() {
        return producentId;
    }

    public void setProducentId(long producentId) {
        this.producentId = producentId;
    }

    public long getUmowaId() {
        return umowaId;
    }

    public void setUmowaId(long umowaId) {
        this.umowaId = umowaId;
    }
    
    
    
    
}
