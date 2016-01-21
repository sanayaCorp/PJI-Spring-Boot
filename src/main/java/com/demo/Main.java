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
import com.demo.configuration.AppConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Configuration
@EnableAutoConfiguration
@Import(AppConfiguration.class)
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    /**
     * @param args the command line arguments
     */
    public static void main(final String[] args) {
        logger.info("Starting Server!");
        String[] config = {"config/context.xml", "job/job-customer.xml" };

        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext(config);

        JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
        Job job = (Job) context.getBean("customerJob");

        try {
                JobExecution jobExecution = jobLauncher.run(job, new JobParameters());
                System.out.println("Exit Status : " + jobExecution.getStatus());

        } catch (Exception e) {
                e.printStackTrace();
        }

        System.out.println("Done");
    }
      
}
