/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.configuration;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.JmsProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.destination.DestinationResolver;

/**
 *
 * @author sanayapc
 */
@Configuration
@EnableJms
@ComponentScan(basePackages = {
    "com.demo.jms"
})
public class JmsConfiguration {
    
    @Autowired(required = false)
    private DestinationResolver destinationResolver;
    
    @Bean
    public BrokerService embedService(@Value("${spring.activemq.broker-url}") final String brokerUrl) throws Exception {
        final BrokerService service = new BrokerService();
        service.addConnector(brokerUrl);
        service.start();
        return service;
    }
    
    @Bean
    public Destination d(@Value("${customer.queue}") final String quString) {
        return new ActiveMQQueue(quString);
    }
    
    @Bean
    public JmsTemplate jt(final ConnectionFactory connectionFactory,
            final JmsProperties jmsProperties,
            final Destination destination) {
        
        final JmsTemplate jt = new JmsTemplate(connectionFactory);
        jt.setPubSubDomain(jmsProperties.isPubSubDomain());
        jt.setDefaultDestination(destination);
        if (destinationResolver != null) {
            jt.setDestinationResolver(destinationResolver);
        }
        return jt;
    }
}
