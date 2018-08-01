package com.qantas.customerprofiler.service.impl;

import com.qantas.customerprofiler.service.CustomerCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class CustomerCreateServiceImpl implements CustomerCreateService {
    @Autowired
    RestTemplate restTemplate;

    @Value("${crm.create_url}")
    private String urlPath;


    @Override
    public long createCustomer(Map<String, Object> customerDetails) {
        return 0;
    }
}
