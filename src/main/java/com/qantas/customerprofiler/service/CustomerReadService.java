package com.qantas.customerprofiler.service;

import org.springframework.http.ResponseEntity;

public interface CustomerReadService {
    public ResponseEntity readCustomer(Long id);
}
