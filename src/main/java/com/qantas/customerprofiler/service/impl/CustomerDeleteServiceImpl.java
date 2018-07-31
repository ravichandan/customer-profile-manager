package com.qantas.customerprofiler.service.impl;

import com.qantas.customerprofiler.service.CustomerDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerDeleteServiceImpl implements CustomerDeleteService {
    @Autowired
    RestTemplate restTemplate;


    @Value("${crm.delete_url}")
    private String urlPath;
}
