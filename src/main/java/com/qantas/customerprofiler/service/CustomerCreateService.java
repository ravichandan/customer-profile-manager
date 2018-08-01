package com.qantas.customerprofiler.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface CustomerCreateService {
    ResponseEntity createCustomer(Map<String,Object> customerDetails);
}
