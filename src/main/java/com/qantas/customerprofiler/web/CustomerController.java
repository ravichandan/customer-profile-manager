package com.qantas.customerprofiler.web;

import com.qantas.customerprofiler.service.CustomerCreateService;
import com.qantas.customerprofiler.service.CustomerDeleteService;
import com.qantas.customerprofiler.service.CustomerReadService;
import com.qantas.customerprofiler.service.CustomerUpdateService;
import com.qantas.customerprofiler.validation.ValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class CustomerController {

    Logger logger = LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    CustomerCreateService createService;
    @Autowired
    CustomerUpdateService updateService;
    @Autowired
    CustomerDeleteService deleteService;
    @Autowired
    CustomerReadService readService;

    @Autowired
    ValidationService validationService;

    @GetMapping(path = "/pulse")
    @ResponseBody
    public void pulse() {
        logger.debug("Pulse url. Still alive");
    }

    // To create a new customer
    @PostMapping(path = "/customers", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)

    public ResponseEntity createCustomer(@RequestBody HashMap<String, Object> customerDetails, @RequestHeader("Authorization") String token) {

        logger.debug("Received request in to create a customer");
        validateToken(token);
        // NOTE: The below token validation will be done at API GATEWAY level.
        // The below is just for demonstration purposes

        logger.debug("Authorized to perform this action. Proceeding with the request");
        return createService.createCustomer(customerDetails);

    }


    // To delete an existing customer
    @DeleteMapping(path = "/customers", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity deleteCustomer(@PathVariable("id") long id, @RequestHeader("Authorization") String token) {

        logger.debug("Received request in to delete a customer by id");

        // NOTE: The below token validation will be done at API GATEWAY level.
        // The below is just for demonstration purposes
        validateToken(token);

        logger.debug("Authorized to perform this action. Proceeding with the request");
        return deleteService.deleteCustomer(id);
    }

    // To update an existing customer
    @PutMapping(path = "/customers", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity updateCustomer(@RequestBody HashMap<String, Object> customerDetails, @RequestHeader("Authorization") String token) {

        logger.debug("Received request in to update a customer");

        // NOTE: The below token validation will be done at API GATEWAY level.
        // The below is just for demonstration purposes
        validateToken(token);
        logger.debug("Authorized to perform this action. Proceeding with the request");

        return updateService.updateCustomer(customerDetails);
    }

    // To read an existing customer
    @GetMapping(path = "/customers", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getCustomer(@PathVariable("id") long id, @RequestHeader("Authorization") String token) {

        logger.debug("Received request in to fetch a customer");

        // NOTE: The below token validation will be done at API GATEWAY level.
        // The below is just for demonstration purposes
        validateToken(token);
        logger.debug("Authorized to perform this action. Proceeding with the request");

        return readService.readCustomer(id);
    }

    private boolean validateToken(String token) {
        try {
            boolean authValid = validationService.validateToken(token);
            if (!authValid) {
                //redirect to different url. Probably login page
                return false;
            }
        } catch (Exception e) {
            // Return HttpStatus.UNAUTHORIZED to the caller.
            logger.error("Not authorized to perform this action.");
        }
        return true;

    }
}


