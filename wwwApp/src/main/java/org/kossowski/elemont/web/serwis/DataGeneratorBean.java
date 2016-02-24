/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.web.serwis;

import com.sun.javafx.UnmodifiableArrayList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.kossowski.elemont.domain.Grupa;
import org.kossowski.elemont.domain.Jm;
import org.kossowski.elemont.domain.KartaMagazynowa;
import org.kossowski.elemont.domain.Material;
import org.kossowski.elemont.domain.Odcinek;
import org.kossowski.elemont.domain.Operacja;
import org.kossowski.elemont.domain.Producent;
import org.kossowski.elemont.domain.Projekt;
import org.kossowski.elemont.domain.Stanowisko;
import org.kossowski.elemont.domain.Umowa;
import org.kossowski.elemont.domain.User;
import org.kossowski.elemont.domain.operacje.NowyOdcinek;
import org.kossowski.elemont.domain.operacje.PrzyjecieZGlownego;
import org.kossowski.elemont.domain.operacje.WydanieNaBudowe;
import org.kossowski.elemont.repositories.GrupaRepository;
import org.kossowski.elemont.repositories.JmRepository;
import org.kossowski.elemont.repositories.KartaMagazynowaRepository;
import org.kossowski.elemont.repositories.MaterialRepository;
import org.kossowski.elemont.repositories.OperacjaRepository;
import org.kossowski.elemont.repositories.ProducentRepository;
import org.kossowski.elemont.repositories.ProjektRepository;
import org.kossowski.elemont.repositories.StanowiskoRepository;
import org.kossowski.elemont.repositories.UmowaRepository;
import org.kossowski.elemont.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author jkossow
 */

@Controller
public class DataGeneratorBean {
    
    @Autowired
    private JmRepository jmRepo;
    
    @Autowired
    private GrupaRepository grupaRepo;
    
    @Autowired
    private MaterialRepository matRepo;
    
    @Autowired
    private KartaMagazynowaRepository kartaRepo;
    
    @Autowired
    private OperacjaRepository operacjaRepo;
    
    @Autowired
    private ProjektRepository projektRepo;
    
    @Autowired
    private UmowaRepository umowaRepo;
    
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private ProducentRepository prodRepo;
    
    @Autowired
    private StanowiskoRepository stanRepo;
    
    @Autowired
    protected ApplicationContext ac;
    
    
    public void insertData() throws Exception {
        
        initJm();
        initGrupa();
        initUser();
        initProducent();
        initMaterial();
        initProjekt();
        initUmowa();
        
        initStanowisko();
        
        List<Material> lm = matRepo.findAll();
        KartaMagazynowa k = new KartaMagazynowa();
        
        k = kartaRepo.save(k);
        
        PrzyjecieZGlownego p = new PrzyjecieZGlownego();
        p.setMaterial( lm.get(0));
        p.setDostawca( prodRepo.findFirstBySymbol("ant"));
        p.setProducent(prodRepo.findFirstBySymbol("ant"));
        p.setProjekt( projektRepo.findFirstBySymbol("p1"));
        p.setMiejsceSkladowania("ffffff");
        p.setIlosc( new BigDecimal(300));
        
        k.addOperation(p);
        p.accept();
        kartaRepo.save( k );
        
        WydanieNaBudowe w = new WydanieNaBudowe( userRepo.findFirstByKodQR("001"), new BigDecimal(300));
        k.addOperation(w);
        w.accept();
        kartaRepo.save(k);
        
        Odcinek odc = new Odcinek();
        NowyOdcinek no = new NowyOdcinek(odc);
        k.addOcinek(odc);
        k.addOperation(no);
        no.accept();
        kartaRepo.save(k);
        
    }
    
    private void initJm() {
        Jm jm = new Jm( "szt.", "Sztuka");
        jmRepo.save( jm );
        jmRepo.save( new Jm( "mb", "metr bieżący"));
        jmRepo.save( new Jm( "kg", "kilogram"));
        
    }
    
    private void initGrupa() {
        grupaRepo.save( new Grupa("kable", "Kable"));
        grupaRepo.save( new Grupa("osprzet", "Osprzęt"));
    }
    
    private void initMaterial() {
        Jm jm = jmRepo.findFirstBySymbol("mb");
        Grupa grupa = grupaRepo.findFirstBySymbol("kable");
        
        try {
            
            List<String> l = Files.readAllLines( Paths.get( "/material.txt"),Charset.forName("cp1250"));
            for( String s: l) {
                Material m = new Material(s, s, jm, grupa);
                matRepo.save(m);
            }
        } catch ( IOException e ){ e.printStackTrace(); };
            
    }
    
    private void initProjekt() {
        
        User j = userRepo.findOne("jkossow");
        User k = userRepo.findOne("jakubk");
        
        Projekt p = new Projekt("p1", "projekt pierwszy");
        p.getZespol().add( j );
        projektRepo.save( p );
        
        p = new Projekt("p1 bis", "projekt pierwszy bis");
        p.getZespol().add( k );
        projektRepo.save( p );
        
        p = new Projekt("p2", "projekt drugi");
        p.getZespol().add( j );
        p.getZespol().add( k );
        projektRepo.save( p );
        
        p = new Projekt( "p2", "projektTrzeci");
        p.getZespol().add( j );
        p.getZespol().add( k );
        projektRepo.save( p );
        
    }
    
    private void initUmowa() {
        umowaRepo.save( new Umowa("um nr 1"));
        umowaRepo.save( new Umowa("um nr 2"));
        umowaRepo.save( new Umowa("um nr 3"));
        umowaRepo.save( new Umowa("um nr 4"));
    }
    
    private void initUser() {
        User u;
        
        u = new User("jkossow", "Kossowski", "Janusz");
        u.setPassword("aaa");
        u.getRole().add("ROLE_ADMIN");
        u.getRole().add("ROLE_BUDOWA");
        u.getRole().add("ROLE_MAGAZYN");
        u.getRole().add("ROLE_SERWIS");
        u.setKodQR("001");
        userRepo.save( u );
        
        u = new User("jakubk", "Kwiatkowski", "Jakub");
        u.setKodQR("002");
        userRepo.save( u );
    }
    
    private void initProducent() {
        prodRepo.save( new Producent("ant", "Ant Computers"));
        prodRepo.save( new Producent("inna","jakaś inna firma"));
    }
    
    private void initStanowisko() {
        stanRepo.save( new Stanowisko("s1", "Stanowisko1"));
        stanRepo.save( new Stanowisko("s2", "Stanowisko2"));
        stanRepo.save( new Stanowisko("s3", "Stanowisko3"));
        stanRepo.save( new Stanowisko("s4", "Stanowisko4"));
    }
    
    
    public void beanList() {
        
        String[] beans = ac.getBeanDefinitionNames();
        
        for( String s : beans)
            System.out.println( s );
        
    }
            
    
}
