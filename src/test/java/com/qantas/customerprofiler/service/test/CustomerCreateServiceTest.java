package com.qantas.customerprofiler.service.test;

import com.qantas.customerprofiler.service.CustomerCreateService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class CustomerCreateServiceTest {
    @Autowired
    CustomerCreateService createService;

    Map<String, Object> customerDetails;

    @Before
    public void before(){
        customerDetails = new HashMap<>();
        customerDetails.put('first-name','First Name');
        customerDetails.put('last-name','Last Name');
        customerDetails.put('first-name','First Name');
    }
    @Test
    public void createCustomerTest(){

    }
}
