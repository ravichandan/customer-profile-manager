package com.qantas.customerprofiler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerCreateService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${crm.create_url}")
    private String urlPath;

}
