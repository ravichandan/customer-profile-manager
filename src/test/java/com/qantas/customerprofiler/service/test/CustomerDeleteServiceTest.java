package com.qantas.customerprofiler.service.test;

import com.qantas.customerprofiler.service.CustomerDeleteService;
import com.qantas.customerprofiler.service.impl.CustomerDeleteServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.ResponseActions;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {CustomerDeleteServiceImpl.class, RestTemplate.class})
@TestPropertySource(locations="classpath:test-application.properties")
public class CustomerDeleteServiceTest {

    @Autowired
    CustomerDeleteService deleteService;


    //    @Mock
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

        mockServer = MockRestServiceServer.createServer(restTemplate);

        actions = mockServer.expect(requestTo(this.crmHost + ":" + this.crmPort + this.urlPath))
                .andExpect(method(HttpMethod.DELETE));
        actions.andRespond(withStatus(HttpStatus.ACCEPTED));

    }

    @Test
    public void deleteCustomerTest() {
//        customerDetails.put("details", "other information");



//        customerDetails.put("address", addresses);
        ResponseEntity response = deleteService.deleteCustomer(1l);
        Assert.assertEquals("Customer should be successfully deleted", 202, response.getStatusCodeValue());
    }

    @Test
    public void failDeletingCustomerWhenIdIsZeroTest() {

        ResponseEntity response = deleteService.deleteCustomer(0l);
        Assert.assertEquals("Customer deletion should fail", 400, response.getStatusCodeValue());
    }


    @Test
    public void failDeletingCustomerWhenIdIsNullTest() {

        ResponseEntity response = deleteService.deleteCustomer(null);
        Assert.assertEquals("Customer deletion should fail", 400, response.getStatusCodeValue());
    }
}
