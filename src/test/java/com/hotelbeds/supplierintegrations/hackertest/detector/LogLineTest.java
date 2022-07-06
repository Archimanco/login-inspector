package com.hotelbeds.supplierintegrations.hackertest.detector;

import com.hotelbeds.supplierintegrations.hackertest.utils.TimeCalculator;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class LogLineTest {

    @Test
    void testTtoLogLine_givenKnownLogstring_yieldsExpectedLogObject() {
        String logAsString = "80.238.9.179,133612947,SIGNIN_SUCCESS,Will.Smith";
        LogLine logLineConverted = LogLine.toLogLine(logAsString);

        assertThat("IP is converted correctly",
                logLineConverted.getIp(),is("80.238.9.179"));
        assertThat("Date is converted correctly",
                logLineConverted.getDate(),is(133612947l));
        assertThat("Action is converted correctly",
                logLineConverted.getAction(),is(Action.SIGNIN_SUCCESS));
        assertThat("Name is converted correctly",
                logLineConverted.getName(),is("Will.Smith"));
    }

    @Test
    void isLogLineParseable() {
        fail("note implemented reason: waiting for answers on logic -> see hackdetector");
    }
}