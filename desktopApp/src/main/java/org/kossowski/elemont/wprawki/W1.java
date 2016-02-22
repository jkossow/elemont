/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.wprawki;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import org.kossowski.elemont.repositories.ProjektRepository;

/**
 *
 * @author jkossow
 */

@Component
public class W1 {
       
    public static void main(String[] args) {
        
              
        ApplicationContext ctx = new ClassPathXmlApplicationContext("springContext.xml");
        
       
        String[] names = ctx.getBeanDefinitionNames();
        
        System.out.println("Lista beanów");
        for( String s : names )
            System.out.println( s );
        System.out.println("Koniec - listy beanów");
       
        ActionClass a = (ActionClass)ctx.getBean("actionClass");
        a.a1();
        
       
    }
    
}
