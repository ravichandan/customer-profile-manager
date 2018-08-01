package com.qantas.customerprofiler.service.impl;

import com.qantas.customerprofiler.service.CustomerDeleteService;
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
public class CustomerDeleteServiceImpl implements CustomerDeleteService {

    Logger logger = LoggerFactory.getLogger(CustomerCreateServiceImpl.class);
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CustomerReadService readService;

    @Value("${crm.delete_url}")
    private String urlPath;


    @Value("${crm.host}")
    private String crmHost;

    @Value("${crm.port}")
    private String crmPort;

    @Override
    public ResponseEntity deleteCustomer(Long id) {
        logger.debug("Received customer details to delete");

        if (id == null || id == 0l) {
            logger.error("Id can't be null");
            return ResponseEntity.badRequest().body("'id' can't be null. Please see specifications for more details");
        }

        logger.debug("Sending request to CRM system to delete the customer");
        String url = this.crmHost + ":" + this.crmPort + this.urlPath + "/" + id;

        try {
            this.restTemplate.delete(url);
            logger.debug("Customer record is successfully deleted");
            return ResponseEntity.accepted().body("Customer record was deleted successfully");

        } catch (Exception e) {
            logger.error("Could not delete customer");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not delete customer due to fatal reasons");
        }

    }
}
