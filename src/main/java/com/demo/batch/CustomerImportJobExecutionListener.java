/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.batch;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

/**
 *
 * @author sanayapc
 */
public class CustomerImportJobExecutionListener extends JobExecutionListenerSupport {
    
    private static final Logger log = LoggerFactory.getLogger(CustomerImportJobExecutionListener.class);

//    private final List<MetricProvider> metrics;
//
//    public CustomerImportJobExecutionListener(final List<MetricProvider> metrics) {
//        this.metrics = metrics;
//    }

    @Override
    public void beforeJob(final JobExecution jobExecution) {
        log.debug("Customer import job started.");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.debug("Customer import job completed.");
//            metrics.stream()
//                    .map(metric -> String.format("%s = %s", metric.getMetricName(), metric.getMetricValue()))
//                    .forEach(log::debug);
        }
    }
}
