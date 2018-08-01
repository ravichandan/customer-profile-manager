package com.qantas.customerprofiler.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface CustomerUpdateService {
    ResponseEntity updateCustomer(Map<String,Object> customerDetails);
}
