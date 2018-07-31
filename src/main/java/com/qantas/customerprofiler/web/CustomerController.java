package com.qantas.customerprofiler.web;

import com.qantas.customerprofiler.service.CustomerCreateService;
import com.qantas.customerprofiler.service.CustomerDeleteService;
import com.qantas.customerprofiler.service.CustomerUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class CustomerController {
    @Autowired
    CustomerCreateService createService;
    @Autowired
    CustomerUpdateService updateService;
    @Autowired
    CustomerDeleteService deleteService;

    // To create a new customer
    @PostMapping(path = "/customers", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public Response createCustomer() {

    }

    // To delete an existing customer
    @DeleteMapping(path = "/customers", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public Response createCustomer() {

    }

    // To update an existing customer
    @PutMapping(path = "/customers", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public Response createCustomer() {

    }
}
