/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.configuration;

import com.demo.batch.CustomerReaderFactory;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

/**
 *
 * @author sanayapc
 */
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
    
    @Configuration
    static class MapBatchConfig extends DefaultBatchConfigurer {
//        @Override
        protected JobRepository jobRepository() throws Exception {
            MapJobRepositoryFactoryBean factory = new MapJobRepositoryFactoryBean();
            factory.afterPropertiesSet();
            return factory.getObject();
        }
    }
    
    @Bean
    @Qualifier("jpaTransactionManagerForBatch")
    public PlatformTransactionManager jpaTransactionManager() {
        return jpaTransactionManager();
    }
    
    @Bean
    public CustomerReaderFactory customerReaderFactory() {
        return new CustomerReaderFactory();
    }
}
