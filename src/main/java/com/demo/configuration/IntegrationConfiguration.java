/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.configuration;

import java.io.File;
import java.time.Clock;
import org.springframework.batch.integration.launch.JobLaunchingMessageHandler;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.filters.AcceptOnceFileListFilter;
import org.springframework.integration.file.filters.CompositeFileListFilter;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.scheduling.support.PeriodicTrigger;

/**
 *
 * @author sanayapc
 */
@Configuration
@EnableIntegration
@ComponentScan(basePackages = {
        "com.demo.batch.integration"
})
public class IntegrationConfiguration {
     @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata defaultPoller() {
        final PollerMetadata pollerMetadata = new PollerMetadata();
        pollerMetadata.setTrigger(new PeriodicTrigger(10));
        return pollerMetadata;
    }

    @Bean
    public DirectChannel inboundFileChannel() {
        return new DirectChannel();
    }

    @Bean
    public DirectChannel outboundJobRequestChannel() {
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "loggingChannel")
    public LoggingHandler loggingHandler() {
        return new LoggingHandler(LoggingHandler.Level.INFO.name());
    }

    @Bean
    @InboundChannelAdapter(value = "inboundFileChannel", poller = @Poller(cron = "${customer.poller.cron}"))
    public MessageSource<File> fileMessageSource(@Value("${customer.poller.path}") final String path,
                                                 @Value("${customer.poller.fileMask}") final String fileMask) {
        final FileReadingMessageSource source = new FileReadingMessageSource();
        final CompositeFileListFilter<File> compositeFileListFilter = new CompositeFileListFilter<>();
        final SimplePatternFileListFilter simplePatternFileListFilter = new SimplePatternFileListFilter(fileMask);
        final AcceptOnceFileListFilter<File> acceptOnceFileListFilter = new AcceptOnceFileListFilter<>();
        compositeFileListFilter.addFilter(simplePatternFileListFilter);
        compositeFileListFilter.addFilter(acceptOnceFileListFilter);
        source.setFilter(compositeFileListFilter);
        source.setDirectory(new File(path));
        return source;
    }

    @ServiceActivator(inputChannel = "outboundJobRequestChannel", outputChannel = "loggingChannel")
    @Bean
    public JobLaunchingMessageHandler jobLaunchingGateway(final JobLauncher jobLauncher) {
        return new JobLaunchingMessageHandler(jobLauncher);
    }
}
