package com.qantas.customerprofiler.service.impl;

import com.qantas.customerprofiler.service.CustomerUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class CustomerUpdateServiceImpl implements CustomerUpdateService {
    @Autowired
    RestTemplate restTemplate;


    @Value("${crm.update_url}")
    private String urlPath;

    @Override
    public ResponseEntity updateCustomer(Map<String, Object> customerDetails) {
        return null;
    }
}
