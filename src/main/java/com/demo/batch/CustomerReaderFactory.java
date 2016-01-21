/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.batch;

import com.demo.model.Customer;

import java.beans.PropertyEditor;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.io.Resource;

/**
 *
 * @author sanayapc
 */
public class CustomerReaderFactory {
    
    public static final String[] CUSTOMER_FILE_CSV_FIELDS = new String[]{"name","address","gender","date","curs","bal"};
    public static final String DATE_FORMAT = "dd-MM-yyyy";
    
    public ItemStreamReader<Customer> createReader(final Resource source) {
        final FlatFileItemReader<Customer> reader = new FlatFileItemReader<>();
        reader.setResource(source);
        final DefaultLineMapper<Customer> lineMapper = new DefaultLineMapper<>();
        final DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames(CUSTOMER_FILE_CSV_FIELDS);
        lineMapper.setLineTokenizer(lineTokenizer);
        final BeanWrapperFieldSetMapper<Customer> fieldMapper = new BeanWrapperFieldSetMapper<>();
        fieldMapper.setTargetType(Customer.class);
        final DateFormat df = new SimpleDateFormat(DATE_FORMAT);
        final Map<Class, PropertyEditor> customEditors = Stream.of(
                new AbstractMap.SimpleEntry<>(Date.class, new CustomDateEditor(df, false)))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        fieldMapper.setCustomEditors(customEditors);
        lineMapper.setFieldSetMapper(fieldMapper);
        reader.setLineMapper(lineMapper);
        return reader;
    }
}
