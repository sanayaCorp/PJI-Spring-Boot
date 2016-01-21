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
    public BrokerService embeddedActiveMQ(@Value("${spring.activemq.broker-url}") final String brokerUrl) throws Exception {
        final BrokerService broker = new BrokerService();
        broker.addConnector(brokerUrl);
        broker.start();
        return broker;
    }
    
    @Bean
    public Destination destination(@Value("${customer.queue}") final String queueName) {
        return new ActiveMQQueue(queueName);
    }
    
    @Bean
    public JmsTemplate jmsTemplate(final ConnectionFactory connectionFactory,
                                   final JmsProperties properties,
                                   final Destination destination) {
        final JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setPubSubDomain(properties.isPubSubDomain());
        jmsTemplate.setDefaultDestination(destination);
        if (destinationResolver != null) {
            jmsTemplate.setDestinationResolver(destinationResolver);
        }
        return jmsTemplate;
    }
}
