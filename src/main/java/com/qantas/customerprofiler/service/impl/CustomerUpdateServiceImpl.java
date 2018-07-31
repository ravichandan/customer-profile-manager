package com.qantas.customerprofiler.service.impl;

import com.qantas.customerprofiler.service.CustomerUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerUpdateServiceImpl implements CustomerUpdateService {
    @Autowired
    RestTemplate restTemplate;


    @Value("${crm.update_url}")
    private String urlPath;

}
