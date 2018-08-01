package com.qantas.customerprofiler.validation;

import com.qantas.customerprofiler.service.impl.CustomerCreateServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ValidationService {
    private Logger logger = LoggerFactory.getLogger(CustomerCreateServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${auth.server.url}")
    private String authServerUrl;


    public List<String> validate(Map<String, Object> customerDetails) {

        logger.debug("Validating customer fields");
        List<String> errors = null;

        if (!customerDetails.containsKey("first-name")) {
            logger.error("'first-name' field is mandatory");
            errors = new ArrayList<>();
            errors.add("'first-name' field is mandatory");
        }
        if (!customerDetails.containsKey("last-name")) {
            logger.error("'last-name' field is mandatory");
            if (errors == null) {
                errors = new ArrayList<>();
            }
            errors.add("'last-name' field is mandatory");
        }

        return errors;

    }

    public boolean validateToken(String token) {
        ResponseEntity response = this.restTemplate.getForEntity(authServerUrl, String.class);
        if (response.getStatusCode() == HttpStatus.ACCEPTED) {
            // validate the authentication and respond accordingly
            return response.getBody().toString().toUpperCase().contains("SUCCESS");
        }
        return true;
    }
}
