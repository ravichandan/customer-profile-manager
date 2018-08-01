package com.qantas.customerprofiler.service.test;

import com.qantas.customerprofiler.service.impl.CustomerCreateServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.ResponseActions;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {CustomerCreateServiceImpl.class, RestTemplate.class})
@TestPropertySource(locations = "classpath:test-application.properties")
public class CustomerCreateServiceTest {
    @Autowired
    private CustomerCreateServiceImpl customerCreateService;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${crm.create_url}")
    private String urlPath;

    @Value("${crm.host}")
    private String crmHost;

    @Value("${crm.port}")
    private String crmPort;


    Map<String, Object> customerDetails;

    MockRestServiceServer mockServer;

    ResponseActions actions;

    @Before
    public void before() {
        customerDetails = new HashMap<>();
        customerDetails.put("first-name", "First Name");
        customerDetails.put("last-name", "Last Name");
        customerDetails.put("date-of-birth", "22/01/1940");
        mockServer = MockRestServiceServer.createServer(restTemplate);

        actions = mockServer.expect(requestTo(this.crmHost + ":" + this.crmPort + this.urlPath))
                .andExpect(method(HttpMethod.POST));
        actions.andRespond(withSuccess("{customerId:1}", MediaType.APPLICATION_JSON));


    }

    @Test
    public void createCustomerTest() {
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
        ResponseEntity response = customerCreateService.createCustomer(customerDetails);
        Assert.assertEquals("Customer should be successfully created", 200, response.getStatusCodeValue());
    }

    @Test
    public void failCreatingCustomerWhenCustomerDetailsAreNullTest() {
        ResponseEntity response = customerCreateService.createCustomer(null);
        Assert.assertEquals("Customer creation should fail", 400, response.getStatusCodeValue());
    }


    @Test
    public void failCreatingCustomerWhenCustomerDetailsAreEmptyTest() {
        ResponseEntity response = customerCreateService.createCustomer(new HashMap<>());
        Assert.assertEquals("Customer creation should fail", 400, response.getStatusCodeValue());
    }

}
