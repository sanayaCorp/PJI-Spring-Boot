/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.processor;

import com.demo.model.Customer;
import org.springframework.batch.item.ItemProcessor;

/**
 *
 * @author sanayapc
 */
public class FilterCustomerProcessor implements ItemProcessor<Customer, Customer>{
    
    public Customer process(Customer customer) throws Exception {
        return customer;
    }

}
