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
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

/**
 *
 * @author sanayapc
 */
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
    
    @Configuration
    static class MapBatchConfigurer extends DefaultBatchConfigurer {
        @Override
        protected JobRepository createJobRepository() throws Exception {
            MapJobRepositoryFactoryBean factory = new MapJobRepositoryFactoryBean();
            factory.afterPropertiesSet();
            return factory.getObject();
        }
    }

    @Bean
    @Qualifier("jpaTransactionManagerForBatch")
    public PlatformTransactionManager jpaTransactionManager() {
        return new JpaTransactionManager();
    }

    @Bean
    public CustomerReaderFactory customerReaderFactory() {
        return new CustomerReaderFactory();
    }


//    @Bean
//    @StepScope
//    public ItemStreamReader<Customer> customerReader(final CustomerReaderFactory customerReaderFactory,
//                                                 final @Value("file:#{jobParameters['input.file.name']}") Resource resource) {
//        return new FilterItemReaderAdapter<>(customerReaderFactory.createReader(resource), CustomerPredicate.hasTodayDate());
//    }
//
//    @Bean
//    public ItemWriter<Customer> customerWriter(final CustomerRepository repository,
//                                           final JmsTemplate jmsTemplate) {
//        final CompositeItemWriter<Customer> writer = new CompositeItemWriter<>();
//        final RepositoryItemWriter<Customer> repositoryItemWriter = new RepositoryItemWriter<>();
//        repositoryItemWriter.setRepository(repository);
//        repositoryItemWriter.setMethodName("saveAndFlush");
//        final JmsItemWriter<Customer> jmsItemWriter = new JmsItemWriter();
//        jmsItemWriter.setJmsTemplate(jmsTemplate);
//        final List<ItemWriter<? super Customer>> delegates = Stream.of(
//                repositoryItemWriter,
//                jmsItemWriter)
//                .collect(Collectors.toList());
//        writer.setDelegates(delegates);
//        return writer;
//    }
//
//    @Bean
//    public CustomerCounterUpdater sumEvaluator() {
//        return new CustomerCounterUpdater();
//    }
//
//    @Bean
//    public InternalCustomerLatestCountUpdater internalCustomerLatestCountUpdater() {
//        return new InternalCustomerLatestCountUpdater();
//    }
//
//    @Bean
//    public ItemProcessor<Customer, Customer> importCustomerProcessor(final CustomerRepository customerRepository,
//                                                               final @Value("${customer.metrics.count}") String countCustomerType,
//                                                               final CustomerCounterUpdater customerCounterUpdater,
//                                                               final InternalCustomerLatestCountUpdater internalCustomerLatestCountUpdater) {
//        final List<ItemProcessor<Customer, Customer>> delegates = Stream.of(
//                new CustomerUpdateItemProcessor(customerRepository),
//                new MetricItemProcessor<>(hasType(CustomerType.valueOf(countCustomerType)), customerCounterUpdater),
//                new MetricItemProcessor<>(customer -> true, internalCustomerLatestCountUpdater))
//                .collect(Collectors.toList());
//        final CompositeItemProcessor<Customer, Customer> processor = new CompositeItemProcessor<>();
//        processor.setDelegates(delegates);
//        return processor;
//    }
//
//    @Bean
//    public CustomerImportJobExecutionListener customerJobExecutionListener(final CustomerCounterUpdater customerCounterUpdater,
//                                                                       final InternalCustomerLatestCountUpdater internalCustomerLatestCountUpdater) {
//        final List<MetricProvider> metrics = Stream.of(
//                customerCounterUpdater,
//                internalCustomerLatestCountUpdater)
//                .collect(Collectors.toList());
//        return new CustomerImportJobExecutionListener(metrics);
//    }
//
//    @Bean
//    public Job importCustomersJob(final JobBuilderFactory jobs,
//                                final Step importCustomerStep,
//                                final CustomerImportJobExecutionListener customerImportJobExecutionListener) {
//        return jobs.get("importCustomersJob")
//                .incrementer(new RunIdIncrementer())
//                .listener(customerImportJobExecutionListener)
//                .flow(importCustomerStep)
//                .end()
//                .build();
//    }
//
//    @Bean
//    public Step importCustomerStep(final StepBuilderFactory stepBuilderFactory,
//                                 @Qualifier("jpaTransactionManagerForBatch")
//                                 final PlatformTransactionManager jpaTransactionManager,
//                                 final @Value("${customer.chunk.size}") int chunkSize,
//                                 final ItemReader<Customer> customerReader,
//                                 final ItemWriter<Customer> customerWriter,
//                                 final ItemProcessor<Customer, Customer> importCustomerProcessor) {
//        return stepBuilderFactory.get("importCustomerStep")
//                .<Customer, Customer>chunk(chunkSize)
//                .reader(customerReader)
//                .processor(importCustomerProcessor)
//                .writer(customerWriter)
//                .transactionManager(jpaTransactionManager)
//                .build();
//    }
}
