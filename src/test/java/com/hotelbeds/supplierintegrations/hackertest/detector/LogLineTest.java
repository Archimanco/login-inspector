package com.hotelbeds.supplierintegrations.hackertest.detector;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

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
    void testTtoLogLine_givenBadLogstring_yieldsNull() {
        String logAsString = "80.23348.9.179,-34,SIGNIN_SUCC,Wilith";
        LogLine logLineConverted = LogLine.toLogLine(logAsString);

        assertThat("attempt to parse a bad formatted logline",
                logLineConverted,is(nullValue()));
    }

    @Test
    void testTtoLogLine_givenNullInput_yieldsNull() {
        assertThat("attempt to parse null",
                LogLine.toLogLine(null),is(nullValue()));
    }

    @Test
    void testIsLogLineParseable_givenKnownValidLogstring_yieldsTrue() {
        String logAsString = "80.238.9.179,133612947,SIGNIN_SUCCESS,Will.Smith";
        assertThat("Valid String is parseable",
                LogLine.isLogLineParseable(logAsString),is(true));
    }

    @Test
    void testIsLogLineParseable_givenInalidLogstring_yieldsFalse() {
        String logAsString = "-80.238.9.179,-133612947,SIGNIN,WillSmith";
        assertThat("Inalid String is not parseable",
                LogLine.isLogLineParseable(logAsString),is(false));
    }

    @Test
    void testIsLogLineParseable_givenNullLogstring_yieldsFalse() {
        assertThat("Null String is not parseable",
                LogLine.isLogLineParseable(null),is(false));
    }

    @Test
    void testIsIpValid() {

        String invalidIp = "80.23348.9.179";
        assertThat("is valid " + invalidIp,
                LogLine.isIpValid(invalidIp),is(false));

        invalidIp = "80.233.9179";
        assertThat("is valid " + invalidIp,
                LogLine.isIpValid(invalidIp),is(false));

        invalidIp = "-80.233.9.179";
        assertThat("is valid " + invalidIp,
                LogLine.isIpValid(invalidIp),is(false));

        invalidIp = "255.255.255.256";
        assertThat("is valid " + invalidIp,
                LogLine.isIpValid(invalidIp),is(false));

        invalidIp = null;
        assertThat("is valid " + invalidIp,
                LogLine.isIpValid(invalidIp),is(false));
    }

    @Test
    void testIsActionValid_givenValidString_yieldsTrue() {
        assertThat("SIGNIN_SUCCESS is valid ",
                LogLine.isActionValid("SIGNIN_SUCCESS"),is(true));
        assertThat("SIGNIN_FAILURE is valid ",
                LogLine.isActionValid("SIGNIN_FAILURE"),is(true));
    }

    @Test
    void testIsActionValid_givenInvalidString_yieldsFalse() {
        assertThat("HELLO_WORLD is not valid ",
                LogLine.isActionValid("HELLO_WORLD"),is(false));
    }

    @Test
    void testIsActionValid_givenNullString_yieldsFalse() {
        assertThat("Null is not valid ",
                LogLine.isActionValid(null),is(false));
    }

    @Test
    void testIsUsernameValid_givenValidString_yieldsTrue() {
        assertThat("Mr.Admin is valid ",
                LogLine.isUsernameValid("Mr.Admin"),is(true));
    }
    @Test
    void testIsUsernameValid_givenInvalidString_yieldsFalse() {
        assertThat("AwesomeName is not valid ",
                LogLine.isUsernameValid("AwesomeName"),is(false));
    }
    @Test
    void testIsUsernameValid_givenNullString_yieldsFalse() {
        assertThat("Null is not valid ",
                LogLine.isUsernameValid(null),is(false));
    }

    @Test
    void testIsDateValid_givenValidString_yieldsTrue() {
        assertThat("123456789 is valid",
                LogLine.isDateValid("123456789"),is(true));
    }
    @Test
    void testIsDateValid_givenInvalidString_yieldsFalse() {
        assertThat("-123456789 is valid",
                LogLine.isDateValid("-123456789"),is(false));
    }
    @Test
    void testIsDateValid_givenNullString_yieldsFalse() {
        assertThat("null is valid",
                LogLine.isDateValid(null),is(false));
    }
}