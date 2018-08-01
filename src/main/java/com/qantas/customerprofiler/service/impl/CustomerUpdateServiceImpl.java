package com.qantas.customerprofiler.service.impl;

import com.qantas.customerprofiler.service.CustomerUpdateService;
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
public class CustomerUpdateServiceImpl implements CustomerUpdateService {
    private Logger logger = LoggerFactory.getLogger(CustomerCreateServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ValidationService validationService;

    @Value("${crm.host}")
    private String crmHost;

    @Value("${crm.port}")
    private String crmPort;

    @Value("${crm.update_url}")
    private String urlPath;

    @Override
    public ResponseEntity updateCustomer(Map<String, Object> customerDetails) {
        logger.debug("Received customer details to update");

        if (customerDetails == null || customerDetails.isEmpty() || !customerDetails.containsKey("id")) {
            logger.error("Mandatory fields are missing in the request.");
            return ResponseEntity.badRequest().body("Customer details are mandatory. Please see specifications for more details");
        }

        if (!customerDetails.containsKey("id")|| Integer.parseInt(customerDetails.get("id").toString())== 0) {
            logger.error("Id is mandatory to update the customer");
            return ResponseEntity.badRequest().body("Id is mandatory to update the customer. Please see specifications for more details");
        }

        // Preliminary validation
        List<String> errors = validationService.validate(customerDetails);

        if (errors != null && errors.size() > 0) {
            logger.error("Validation of the request failed.");
            return ResponseEntity.badRequest().body(errors);
        }
        logger.debug("Sending request to CRM system to update the customer");
        String url = this.crmHost + ":" + this.crmPort + this.urlPath;
        try {
            this.restTemplate.put(url, customerDetails);
            logger.debug("Customer Id updated with given details");
            return ResponseEntity.accepted().body("Customer Id updated with given details");
        } catch (Exception e) {
            logger.error("Customer Id updating failed.", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Customer Id updated with given details");
        }
    }
}
