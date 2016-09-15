/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.security;

/**
 *
 * @author jkossow
 */
import javax.sql.DataSource;
import org.kossowski.elemont.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    
    @Autowired
    protected DataSource dataSource;
    
     @Autowired
     protected UserRepository userRepo;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        
        http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/rest/**").permitAll()
                .antMatchers("/faces/serwis/index.xhtml").permitAll()
                .antMatchers("/faces/magazyn/*").hasRole("MAGAZYN")
                .antMatchers("/faces/budowa/*").hasRole("BUDOWA")
                .antMatchers("/faces/admin/*").hasRole("ADMIN")
                .antMatchers("/faces/serwis/*").hasRole("SERWIS")
                .antMatchers("/faces/sandbox/*").hasRole("SERWIS")
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/rest/login").failureUrl("/rest/login?error")
                
                .permitAll()
                
                .and()
            .logout()
                //.logoutSuccessUrl("/rest/login?logout")
                //.permitAll().invalidateHttpSession(true).clearAuthentication(true)
                .and()
            .exceptionHandling().accessDeniedPage("/403.xhtml");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        
        if( userRepo.count() > 0 )  
            auth
                .jdbcAuthentication()
                    .dataSource(dataSource)
                    .usersByUsernameQuery(
                        "select login,password, aktywny from users where login=?")
                    .authoritiesByUsernameQuery(
                        "select login, role from roles where login=?" )
            ;
        else
            auth
                .inMemoryAuthentication()
                    .withUser("superadmin").password("password").roles("ADMIN","SERWIS");
    }
}