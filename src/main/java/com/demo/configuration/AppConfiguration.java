/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.configuration;

import org.springframework.context.annotation.Import;

/**
 *
 * @author sanayapc
 */

@Import({
        JmsConfiguration.class,
        BatchConfiguration.class
//        IntegrationConfiguration.class,
//        MonitoringConfig.class
})
public class AppConfiguration {
    
}
