package com.qantas.customerprofiler.service;

import org.springframework.http.ResponseEntity;

public interface CustomerDeleteService {

    ResponseEntity deleteCustomer(Long Id);
}
