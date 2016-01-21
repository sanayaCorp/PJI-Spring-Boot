package com.demo.jms;

import com.demo.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
/**
 * This is Customer listener to simulate external tool receiving processed customer.
 */
@Component
public class CustomerListener {
    private static final Logger log = LoggerFactory.getLogger(CustomerListener.class);

    @JmsListener(destination = "${customer.queue}" )
    public void onNewCustomer(final Customer customer) {
        log.debug("onNewCustomer : {}", customer);
    }
}
