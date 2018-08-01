package com.qantas.customerprofiler.service.impl;

import com.qantas.customerprofiler.service.CustomerCreateService;
import com.qantas.customerprofiler.validation.ValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class CustomerCreateServiceImpl implements CustomerCreateService {

    Logger logger = LoggerFactory.getLogger(CustomerCreateServiceImpl.class);

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ValidationService validationService;

    @Value("${crm.create_url}")
    private String urlPath;

    @Value("${crm.host}")
    private String crmHost;

    @Value("${crm.port}")
    private String crmPort;


    @Override
    public ResponseEntity createCustomer(Map<String, Object> customerDetails) {
        logger.debug("Received customer details to create");

        if (customerDetails == null || customerDetails.isEmpty()) {
            logger.error("Mandatory fields are missing in the request.");
            return ResponseEntity.badRequest().body("Customer details are mandatory. Please see specifications for more details");
        }

        // Preliminary validation
        List<String> errors = validationService.validate(customerDetails);

        if (errors != null && errors.size() > 0) {
            logger.error("Validation of the request failed.");
            return ResponseEntity.badRequest().body(errors);
        }
        logger.debug("Sending request to CRM system to create the customer");
        String url = this.crmHost + ":" + this.crmPort + this.urlPath;
        ResponseEntity response = this.restTemplate.postForEntity(url, customerDetails, String.class);
        HttpStatus status = response.getStatusCode();
        if (status.is2xxSuccessful()) {
            logger.debug("Customer Id created: " + response.getBody().toString());
        }

        return response;


    }
}
