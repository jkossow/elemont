/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.web.operacje;

import org.kossowski.elemont.domain.KartaMagazynowa;
import org.kossowski.elemont.domain.Odcinek;
import org.kossowski.elemont.domain.Operacja;
import org.kossowski.elemont.domain.operacje.NowyOdcinek;
import org.kossowski.elemont.repositories.KartaMagazynowaRepository;
import org.kossowski.elemont.repositories.OdcinekRepository;
import org.kossowski.elemont.repositories.OperacjaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 *
 * @author jkossow
 */

@Service
@Scope("request")
public class DrukPrzywBean {
    
    @Autowired
    protected KartaMagazynowaRepository kmRepo;
    
    @Autowired
    protected OperacjaRepository opRepo;
    
    @Autowired
    protected OdcinekRepository odcRepo;
    
    private KartaMagazynowa km;
    private int ilosc = 1;
    private Long kartaId = 0L;
    
    
    public String preDruk() {
        return "/commons/operacje/drukPrzyw.xhtml";
    }
    
    public String drukuj() {
        
        for( int i = 0; i < ilosc; i++ ) {
            Odcinek odc = new Odcinek();
            odc = odcRepo.save(odc);
            NowyOdcinek no = new NowyOdcinek( odc );
            no = opRepo.save( no );
            km.addOcinek(odc);
            km.addOperation(no);
            
            try {
                no.accept();
            } catch (Exception e ) { e.printStackTrace(); };
            
            km = kmRepo.save(km);
            
            System.out.println("drukuje");
        }
        
        return "/commons/karta_mag/list.xhtml";
    };

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }

    public KartaMagazynowa getKm() {
        return km;
    }

    public void setKm(KartaMagazynowa km) {
        this.km = km;
    }

    public Long getKartaId() {
        return kartaId;
    }

    public void setKartaId(Long kartaId) {
        this.kartaId = kartaId;
    }
    
    
    
    
}
