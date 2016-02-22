package org.kossowski.elemont.web.operacje;


import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import org.kossowski.elemont.domain.KartaMagazynowa;
import org.kossowski.elemont.domain.Operacja;
import org.kossowski.elemont.domain.Status;
import org.kossowski.elemont.domain.operacje.WydanieNaBudowe;
import org.kossowski.elemont.repositories.KartaMagazynowaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jkossow
 */

@Controller
@Scope("request")
public class PrzekazNaBudowe {
    
    @Autowired
    protected KartaMagazynowaRepository kmRepo;
    
    private WydanieNaBudowe wydanie = new WydanieNaBudowe();
    
    public String prePrzekaz1() {
        
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> pm = fc.getExternalContext().getRequestParameterMap();
        
        Long id = new Long( pm.get("id"));
        
        KartaMagazynowa km = kmRepo.findOne( id );
        wydanie.setKartaMagazynowa(km);
        wydanie.setIlosc( km.getStanIl().getIValue(1));
        
        
        return "/commons/operacje/przekNaBud.xhtml";
    }
    
    public String przekaz1() {
        
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> pm = fc.getExternalContext().getRequestParameterMap();
        
        // fix może uda sie przekazawc karte w polu hidden
        //serializacja
        Long id = new Long( pm.get("id"));
        KartaMagazynowa km = kmRepo.findOne(id);
        wydanie.setKartaMagazynowa( km );
        
        Operacja o = new WydanieNaBudowe( wydanie.getUser(), wydanie.getIlosc()) ;
        km.addOperation(o);
        
        try {
            o.accept();
            kmRepo.save(km);
        } catch ( Exception e) {e.printStackTrace();};
        
        return "/commons/karta_mag/list.xhtml";
    }
    
    
    public String przekazAll() {
        
        Status s = Status.S1;  
        List<KartaMagazynowa> ls = kmRepo.findAllByStatus( s );
        
        for( KartaMagazynowa k : ls ) {
            System.out.println( k.getId() );
            Operacja o = new WydanieNaBudowe( null, k.getStanIl().getIValue(1) );
            k.addOperation(o);
            try {
                o.accept();
                System.out.println( k.getStatus());
                kmRepo.save( k );
            } catch ( Exception e ) { e.printStackTrace(); }
        }
        return "";
    }

    public WydanieNaBudowe getWydanie() {
        return wydanie;
    }

    public void setWydanie(WydanieNaBudowe wydanie) {
        this.wydanie = wydanie;
    }
    
    
    
}
