package com.hotelbeds.supplierintegrations.hackertest.detector;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

class HackerDetectorTest {

    private HackerDetector hackerDetector;

    @BeforeEach
    void setUp() {
        hackerDetector = new HackerDetector();
    }

    @Test
    void testParseLine_erasesRecordsOlderThan5Minutes() {
        List<String> logLinesAsString = new ArrayList<String>(
                Arrays.asList(
                        "183.238.9.180,133602947,SIGNIN_FAILURE,Tony.Stark",
                        "183.238.9.180,133612947,SIGNIN_FAILURE,Tony.Stark",
                        "183.238.9.180,133622947,SIGNIN_FAILURE,Tony.Stark",
                        "183.238.9.180,133632947,SIGNIN_FAILURE,Tony.Stark",
                        "183.238.9.180,133634047,SIGNIN_FAILURE,Tony.Stark",
                        "183.238.9.180,133634247,SIGNIN_FAILURE,Tony.Stark"
                        ));
        for (String logLineAsString : logLinesAsString) {
            hackerDetector.parseLine(logLineAsString);
        }

        assertThat("Signing failures older than 5 minutes are ignored",
                hackerDetector.parseLine("183.238.9.180,134002947,SIGNIN_FAILURE,Tony.Stark"),
                is(nullValue()));
    }

    @Test
    void testParseLine_differentIpsAreEvaluatedSepparately() {
        List<String> logLinesAsString = new ArrayList<String>(
                Arrays.asList(
                        "183.238.9.180,133602947,SIGNIN_FAILURE,Tony.Stark",
                        "183.238.9.181,133612947,SIGNIN_FAILURE,Tony.Stark",
                        "183.238.9.182,133633947,SIGNIN_FAILURE,Tony.Stark",
                        "183.238.9.183,133644947,SIGNIN_FAILURE,Tony.Stark",
                        "183.238.9.184,133655947,SIGNIN_FAILURE,Tony.Stark",
                        "183.238.9.185,133666947,SIGNIN_FAILURE,Tony.Stark",
                        "183.238.9.186,133677947,SIGNIN_FAILURE,Tony.Stark"
                ));
        for (String logLineAsString : logLinesAsString) {
            hackerDetector.parseLine(logLineAsString);
        }

        assertThat("Ips are evalauted separately",
                hackerDetector.parseLine("183.238.9.180,133687947,SIGNIN_FAILURE,Tony.Stark"),
                is(nullValue()));
    }

    @Test
    void testParseLine_supusiciousIpsAreCaught() {
        List<String> logLinesAsString = new ArrayList<String>(
                Arrays.asList(
                        "183.238.9.180,133602947,SIGNIN_FAILURE,Tony.Stark",
                        "183.238.9.180,133612947,SIGNIN_FAILURE,Tony.Stark",
                        "183.238.9.180,133633947,SIGNIN_FAILURE,Tony.Stark",
                        "183.238.9.180,133644947,SIGNIN_FAILURE,Tony.Stark",
                        "183.238.9.180,133655947,SIGNIN_FAILURE,Tony.Stark",
                        "183.238.9.180,133666947,SIGNIN_FAILURE,Tony.Stark",
                        "183.238.9.180,133677947,SIGNIN_FAILURE,Tony.Stark"
                ));
        for (String logLineAsString : logLinesAsString) {
            hackerDetector.parseLine(logLineAsString);
        }

        assertThat("Ip 183.238.9.180 is suspicious and is beign caught",
                hackerDetector.parseLine("183.238.9.180,133687947,SIGNIN_FAILURE,Tony.Stark"),
                is("183.238.9.180"));
    }

    @Test
    void testParseLine_successLoginsAreNotEvaluatedAsSuspicious() {
        List<String> logLinesAsString = new ArrayList<String>(
                Arrays.asList(
                        "183.238.9.180,133602947,SIGNIN_SUCCESS,Tony.Stark",
                        "183.238.9.180,133612947,SIGNIN_SUCCESS,Tony.Stark",
                        "183.238.9.180,133633947,SIGNIN_SUCCESS,Tony.Stark",
                        "183.238.9.180,133644947,SIGNIN_SUCCESS,Tony.Stark",
                        "183.238.9.180,133655947,SIGNIN_SUCCESS,Tony.Stark",
                        "183.238.9.180,133666947,SIGNIN_SUCCESS,Tony.Stark",
                        "183.238.9.180,133677947,SIGNIN_SUCCESS,Tony.Stark"
                ));
        for (String logLineAsString : logLinesAsString) {
            hackerDetector.parseLine(logLineAsString);
        }

        assertThat("Ip 183.238.9.180 is not suspicious",
                hackerDetector.parseLine("183.238.9.180,133687947,SIGNIN_SUCCESS,Tony.Stark"),
                is(nullValue()));
    }

    @Test
    void testParseLine_evaluateKnownLogs() {
        List<String> logLinesAsString = new ArrayList<String>(
                Arrays.asList(
                        "183.238.9.180,133602947,SIGNIN_SUCCESS,Tony.Stark",
                        "183.238.9.180,133612947,SIGNIN_SUCCESS,Tony.Stark",
                        "183.238.9.180,133613947,SIGNIN_FAILURE,Mr.Hacker",
                        "183.238.9.180,133633947,SIGNIN_SUCCESS,Tony.Stark",
                        "183.238.9.180,133644947,SIGNIN_SUCCESS,Tony.Stark",
                        "183.238.9.180,133655947,SIGNIN_SUCCESS,Tony.Stark",
                        "183.238.9.180,133666947,SIGNIN_SUCCESS,Tony.Stark",
                        "183.238.9.180,133677947,SIGNIN_SUCCESS,Tony.Stark"
                ));
        for (String logLineAsString : logLinesAsString) {
            hackerDetector.parseLine(logLineAsString);
        }

        assertThat("Ip 183.238.9.180 is not suspicious",
                hackerDetector.parseLine("183.238.9.180,133687947,SIGNIN_SUCCESS,Tony.Stark"),
                is(nullValue()));

        hackerDetector.parseLine("183.238.9.180,133687947,SIGNIN_FAILURE,Mr.Hacker");
        hackerDetector.parseLine("183.238.9.180,133688947,SIGNIN_FAILURE,Mr.Hacker");
        hackerDetector.parseLine("183.238.9.180,133689947,SIGNIN_FAILURE,Mr.Hacker");
        hackerDetector.parseLine("183.238.9.180,133690947,SIGNIN_FAILURE,Mr.Hacker");

        assertThat("Ip 183.238.9.180 is suspicious",
                hackerDetector.parseLine("183.238.9.180,133691947,SIGNIN_FAILURE,Mr.Hacker"),
                is("183.238.9.180"));
    }

}