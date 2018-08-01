package com.qantas.customerprofiler.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface CustomerUpdateService {
    public ResponseEntity updateCustomer(Map<String,Object> customerDetails);
}
