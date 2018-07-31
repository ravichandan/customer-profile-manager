package com.qantas.customerprofiler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerDeleteService {
    @Autowired
    RestTemplate restTemplate;

}
