/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.configuration;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 *
 * @author sanayapc
 */
@Configuration
//@EnableJpaRepositories({
//    "com.demo.repository"
//})
@EntityScan(basePackages = {
    "com.demo.model"
})
public class JpaConfiguration {
    
}
