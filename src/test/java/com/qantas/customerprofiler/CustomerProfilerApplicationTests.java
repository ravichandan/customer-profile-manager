package com.qantas.customerprofiler;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerProfilerApplicationTests {

    @Test
    public void contextLoads() {
        Assert.assertTrue("Spring context should initialize with out any error", true);
    }

}
