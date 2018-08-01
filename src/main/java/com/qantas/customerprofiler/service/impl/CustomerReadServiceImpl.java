package com.qantas.customerprofiler.service.impl;

import com.qantas.customerprofiler.service.CustomerReadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerReadServiceImpl implements CustomerReadService {

    Logger logger = LoggerFactory.getLogger(CustomerReadServiceImpl.class);

    @Autowired
    RestTemplate restTemplate;


    @Value("${crm.read_url}")
    private String urlPath;

    @Value("${crm.host}")
    private String crmHost;

    @Value("${crm.port}")
    private String crmPort;


    @Override
    public ResponseEntity readCustomer(Long id) {
        logger.debug("Received customer details to read");

        if (id == null || id == 0l) {
            logger.error("Mandatory fields are missing in the request.");
            return ResponseEntity.badRequest().body("id is mandatory. Please see specifications for more details");
        }

        logger.debug("Requesting CRM system for a customer");
        String url = this.crmHost + ":" + this.crmPort + this.urlPath + "/" + id;
        ResponseEntity response = this.restTemplate.getForEntity(url, String.class);
        HttpStatus status = response.getStatusCode();
        if (status.is2xxSuccessful()) {
            logger.debug("Customer Id fetch: " + response.getBody().toString());
        }

        return response;
    }
}
