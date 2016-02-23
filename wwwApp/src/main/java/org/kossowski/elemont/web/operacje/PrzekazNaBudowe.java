package org.kossowski.elemont.web.operacje;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import org.kossowski.elemont.domain.KartaMagazynowa;
import org.kossowski.elemont.domain.Operacja;
import org.kossowski.elemont.domain.Status;
import org.kossowski.elemont.domain.User;
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
    
    protected Long id; //id karty magazynowej
    protected User user;
    protected BigDecimal ilosc;
    
    public String prePrzekaz1() {
        
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> pm = fc.getExternalContext().getRequestParameterMap();
        
        id = new Long( pm.get("id"));
        
        KartaMagazynowa km = kmRepo.findOne( id );
        setIlosc( km.getStanIl().getIValue(1));
                
        return "/commons/operacje/przekNaBud.xhtml";
    }
    
    public String przekaz1() {
        
        //FacesContext fc = FacesContext.getCurrentInstance();
        //Map<String,String> pm = fc.getExternalContext().getRequestParameterMap();
        
        // fix może uda sie przekazawc karte w polu hidden
        //serializacja
        //id = new Long( pm.get("id"));
        KartaMagazynowa km = kmRepo.findOne(id);
        //wydanie.setKartaMagazynowa( km );
        
        Operacja o = new WydanieNaBudowe( getUser(), getIlosc()) ;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getIlosc() {
        return ilosc;
    }

    public void setIlosc(BigDecimal ilosc) {
        this.ilosc = ilosc;
    }

    
    
}
