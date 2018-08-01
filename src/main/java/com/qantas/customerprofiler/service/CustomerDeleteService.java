package com.qantas.customerprofiler.service;

import org.springframework.http.ResponseEntity;

public interface CustomerDeleteService {

    public ResponseEntity deleteCustomer(Long Id);
}
