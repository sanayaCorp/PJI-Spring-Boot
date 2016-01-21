/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.demo.configuration;

import javax.jms.Queue;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author afes
 */
@Configuration
public class ActiveMQConfig {
    
    public static final String HELLO_QUEUE = "hello.queue";
    
    @Bean
    public Queue helloJMSQueue() {
        return new ActiveMQQueue(HELLO_QUEUE);
    }
}
