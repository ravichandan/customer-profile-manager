package com.qantas.customerprofiler.service;

import org.springframework.http.ResponseEntity;

public interface CustomerReadService {
    ResponseEntity readCustomer(Long id);
}
