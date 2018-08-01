package com.qantas.customerprofiler.service.test;

import com.qantas.customerprofiler.service.CustomerDeleteService;
import com.qantas.customerprofiler.service.impl.CustomerDeleteServiceImpl;
import com.qantas.customerprofiler.service.impl.CustomerReadServiceImpl;
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
@ContextConfiguration(classes = {CustomerDeleteServiceImpl.class, RestTemplate.class, CustomerReadServiceImpl.class})
@TestPropertySource(locations = "classpath:test-application.properties")
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
    MockRestServiceServer mockServer2;
    ResponseActions actions;

    @Before
    public void before() {

        mockServer = MockRestServiceServer.createServer(restTemplate);
//        mockServer2 = MockRestServiceServer.createServer(restTemplate);
        actions = mockServer.expect(requestTo(this.crmHost + ":" + this.crmPort + this.urlPath + "/1"))
                .andExpect(method(HttpMethod.DELETE));
        actions.andRespond(withStatus(HttpStatus.ACCEPTED));
//
//        mockServer2.expect(requestTo(this.crmHost + ":" + this.crmPort + this.urlPath + "/1"))
//                .andRespond(withStatus(HttpStatus.ACCEPTED));
    }

    @Test
    public void deleteCustomerTest() {
        deleteService.deleteCustomer(1l);
        Assert.assertTrue("Customer should be successfully deleted without exception", true);
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
