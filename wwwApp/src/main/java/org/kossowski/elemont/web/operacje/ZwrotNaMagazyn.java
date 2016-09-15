package org.kossowski.elemont.web.operacje;


import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.kossowski.elemont.domain.KartaMagazynowa;
import org.kossowski.elemont.domain.Operacja;
import org.kossowski.elemont.domain.Stan;
import org.kossowski.elemont.domain.Status;
import org.kossowski.elemont.domain.User;
import org.kossowski.elemont.domain.operacje.WydanieNaBudowe;
import org.kossowski.elemont.domain.operacje.Zwrot;
import org.kossowski.elemont.repositories.KartaMagazynowaRepository;
import org.kossowski.elemont.repositories.OperacjaRepository;
import org.kossowski.elemont.repositories.UserRepository;
import org.kossowski.elemont.security.SecurityController;
import org.kossowski.elemont.utils.JSFUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jkossow
 */

@Service
@Scope("request")
public class ZwrotNaMagazyn {
    
    @Autowired
    protected KartaMagazynowaRepository kmRepo;
    
    @Autowired
    protected OperacjaRepository opRepo;
    
    @Autowired
    protected SecurityController secController;
    
    //@Autowired
    //protected UserRepository userRepo;
    
    protected KartaMagazynowa km;
    protected Long id; //id karty magazynowej
    
    protected BigDecimal ilosc;
    protected BigDecimal znacznikPoczatkowy;
    protected BigDecimal znacznikKoncowy;
    protected Boolean znacznikKoncowyDostepny;
    protected User owner;
    
    public String save() {
        
        if( km == null ) {
            JSFUtils.addMessage("Nie ma takiej partii materiaowej");
            return null;
        }
        
        Operacja o = new Zwrot( secController.getUser(), GregorianCalendar.getInstance().getTime(),
            ilosc, znacznikPoczatkowy, znacznikKoncowy, znacznikKoncowyDostepny, owner);
        
        o = opRepo.save(o);
        km.addOperation(o);
        
        try {
            o.accept();
            
        } catch ( Exception e) {
            km.removeOperation(o);
            opRepo.delete(o);
            
            JSFUtils.addMessage(e.getMessage());
            return null;
        };
        km = kmRepo.save(km);
        
        
        
        JSFUtils.addMessage("Zwrócono");
        return "";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getIlosc() {
        return ilosc;
    }

    public void setIlosc(BigDecimal ilosc) {
        this.ilosc = ilosc;
    }

    public KartaMagazynowa getKm() {
        return km;
    }

    public void setKm(KartaMagazynowa km) {
        this.km = km;
    }

    public BigDecimal getZnacznikPoczatkowy() {
        return znacznikPoczatkowy;
    }

    public void setZnacznikPoczatkowy(BigDecimal znacznikPoczatkowy) {
        this.znacznikPoczatkowy = znacznikPoczatkowy;
    }

    public BigDecimal getZnacznikKoncowy() {
        return znacznikKoncowy;
    }

    public void setZnacznikKoncowy(BigDecimal znacznikKoncowy) {
        this.znacznikKoncowy = znacznikKoncowy;
    }

    public Boolean getZnacznikKoncowyDostepny() {
        return znacznikKoncowyDostepny;
    }

    public void setZnacznikKoncowyDostepny(Boolean znacznikKoncowyDostepny) {
        this.znacznikKoncowyDostepny = znacznikKoncowyDostepny;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    
    
}
