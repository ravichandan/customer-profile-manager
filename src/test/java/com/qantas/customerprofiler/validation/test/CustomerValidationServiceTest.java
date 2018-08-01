package com.qantas.customerprofiler.validation.test;

import com.qantas.customerprofiler.validation.ValidationService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ValidationService.class})

public class CustomerValidationServiceTest {
    @Autowired
    ValidationService validationService;

    Map<String, Object> customerDetails;

    @Before
    public void before() {
        customerDetails = new HashMap<>();
        customerDetails.put("first-name", "First Name");
        customerDetails.put("last-name", "Last Name");
        customerDetails.put("date-of-birth", "22/01/1940");
        customerDetails.put("details", "other information");

        Map<String, String> homeAddress = new HashMap<>();
        homeAddress.put("type", "home");
        homeAddress.put("lineOne", "96, Chandos St");
        homeAddress.put("lineTwo", "Line two");
        homeAddress.put("suburb", "Naremburn");
        homeAddress.put("state", "NSW");
        homeAddress.put("postalCode", "2065");
        Map<String, String>[] addresses = new Map[1];
        addresses[0] = homeAddress;

        customerDetails.put("address", addresses);

    }

    @Test
    public void validateHappyPathForCreateCustomerTest() {
        List<String> errors = validationService.validate(customerDetails);
        Assert.assertNull(errors);
    }

    @Test
    public void validateEmptyNamesCreateCustomerTest() {
        customerDetails.remove("first-name");
        customerDetails.remove("last-name");
        List<String> errors = validationService.validate(customerDetails);
        Assert.assertEquals(2, errors.size());
    }

    @Test
    public void validateHappyPathForUpdateCustomerTest() {
        customerDetails.put("id", 1);
        List<String> errors = validationService.validate(customerDetails);
        Assert.assertNull(errors);
    }

    @Test
    public void validateEmptyIdForUpdateCustomerTest() {
        customerDetails.remove("id");
        List<String> errors = validationService.validate(customerDetails);
        Assert.assertEquals(1, errors.size());
    }
}
