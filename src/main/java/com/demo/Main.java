/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.demo;

/**
 *
 * @author afes
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        logger.info("Starting Server!");
        ApplicationContext context = SpringApplication.run(Main.class, args);
    }
    
}
