/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.wprawki;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author jkossow
 */
//@Configuration

@Configuration
public class Conf12 {

    @Bean
    public SampleBeanClass sampleBeanClass1() {
        SampleBeanClass s = new SampleBeanClass();
        s.setP1("confAuto");
        s.setP2("confAutp2");
        return s;

    }
}
