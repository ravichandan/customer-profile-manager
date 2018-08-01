package com.qantas.customerprofiler.service.impl;

import com.qantas.customerprofiler.service.CustomerCreateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class CustomerCreateServiceImpl implements CustomerCreateService {

    Logger logger = LoggerFactory.getLogger(CustomerCreateServiceImpl.class);

    @Autowired
    RestTemplate restTemplate;

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
            return ResponseEntity.badRequest().body("Customer details are mandatory. Please see specifications for more details");
        }

        String url = this.crmHost + ":" + this.crmPort + this.urlPath;
        ResponseEntity response = this.restTemplate.postForEntity(url, customerDetails, String.class);
        HttpStatus status = response.getStatusCode();
        if (status.is2xxSuccessful()) {
//            Integer customerId = Integer.parseInt(response.getBody().toString());
            logger.debug("Customer Id created: " + response.getBody().toString());
        }

        return response;


    }
}
