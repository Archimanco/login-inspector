package com.hotelbeds.supplierintegrations.hackertest;

import com.hotelbeds.supplierintegrations.hackertest.detector.HackerDetector;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
class HacktestApplicationTest {

    @Autowired
    HackerDetector hackerDetector;

    @Test
    public void contextLoads() throws Exception {
        assertThat("context is loaded with hackdetector", hackerDetector, is(not(nullValue())));
    }
}