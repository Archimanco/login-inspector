package com.hotelbeds.supplierintegrations.hackertest.utils;

import com.hotelbeds.supplierintegrations.hackertest.utils.TimeCalculator;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.fail;

class TimeCalculatorTest {

    // for the GMT+02:00
    private static String TIMEZONEID = "Europe/Madrid";
    private static Locale LOCALE_FOR_TESTING = new Locale("en");

    // Epoch timestamp: 1657021775
    // Timestamp in milliseconds: 1657021775000
    // Date and time (GMT): Tuesday, 5 July 2022 11:49:35
    // Date and time (your time zone): martes, 5 de julio de 2022 13:49:35 GMT+02:00
    private static long KNOWN_EPOCH_TIME_IN_MILLIS = 1657021775000l;
    private static long SECOND_IN_MILLIS = 1000l;
    private static long MINUTE_IN_SECONDS = 60;
    private static long HOUR_IN_MINUTES = 60l;
    private static long MINUTE_IN_MILLIS = MINUTE_IN_SECONDS * SECOND_IN_MILLIS;

    private static long HOURS_IN_MILLIS = HOUR_IN_MINUTES * MINUTE_IN_MILLIS;


    @Test
    void testDifferenceInMinutesBetween_givenTimeStampsStartBeforeEnd_yieldsExpectedDifference() {
        final long FIVE_HOURS_IN_MILLIS = 5 * HOUR_IN_MINUTES * MINUTE_IN_MILLIS;
        final long FIVE_HOURS_IN_MINUTES = 5 * HOUR_IN_MINUTES;

        Timestamp start = new Timestamp(KNOWN_EPOCH_TIME_IN_MILLIS);
        Timestamp end = new Timestamp(KNOWN_EPOCH_TIME_IN_MILLIS + FIVE_HOURS_IN_MILLIS);

        assertThat("Start: Tuesday, 5 July 2022 11:49:35 End: Tuesday, 5 July 2022 16:49:35",
                TimeCalculator.differenceInMinutesBetween(start,end),is(FIVE_HOURS_IN_MINUTES));
    }

    @Test
    void testDifferenceInMinutesBetween_givenTimeStampsEndBeforeStart_yieldsExpectedDifference() {
        final long FIVE_HOURS_IN_MILLIS = 5 * HOURS_IN_MILLIS;
        final long FIVE_HOURS_IN_MINUTES = 5 * HOUR_IN_MINUTES;

        Timestamp start = new Timestamp(KNOWN_EPOCH_TIME_IN_MILLIS);
        Timestamp end = new Timestamp(KNOWN_EPOCH_TIME_IN_MILLIS + FIVE_HOURS_IN_MILLIS);

        assertThat("Start: Tuesday, 5 July 2022 16:49:35 End: Tuesday, 5 July 2022 11:49:35",
                TimeCalculator.differenceInMinutesBetween(end,start),is(FIVE_HOURS_IN_MINUTES));
    }

    @Test
    void testDifferenceInMinutesBetween_givenTimeStampsWithMinutesAndSecondsDifference_yieldsMinuteDifferenceRoundedDown()  {
        Timestamp start = new Timestamp(KNOWN_EPOCH_TIME_IN_MILLIS);
        long timeOffset = 4 * MINUTE_IN_MILLIS + 3 * SECOND_IN_MILLIS;
        Timestamp end = new Timestamp(KNOWN_EPOCH_TIME_IN_MILLIS + timeOffset);

        assertThat("Start: Tuesday, 5 July 2022 11:49:35 End: Tuesday, 5 July 2022 16:54:38",
                TimeCalculator.differenceInMinutesBetween(start,end),is(4l));
    }

    @Test
    void testTimeCalculator_givenTimeStampsWithSecondsDifference_yieldsZeroMinutes()  {
        Timestamp start = new Timestamp(KNOWN_EPOCH_TIME_IN_MILLIS);
        long timeOffset = 3 * SECOND_IN_MILLIS;
        Timestamp end = new Timestamp(KNOWN_EPOCH_TIME_IN_MILLIS + timeOffset);

        assertThat("Start: Tuesday, 5 July 2022 11:49:35 End: Tuesday, 5 July 2022 11:49:38",
                TimeCalculator.differenceInMinutesBetween(start,end),is(0l));
    }

    @Test
    void testTimeCalculator_givenTimeStampsSameDates_yieldsZeroDifference()  {
        Timestamp start = new Timestamp(KNOWN_EPOCH_TIME_IN_MILLIS);

        assertThat("Start: Tuesday, 5 July 2022 11:49:35 End: Tuesday, 5 July 2022 11:49:35",
                TimeCalculator.differenceInMinutesBetween(start,start),is(0l));
    }

    @Test
    void testDifferenceInMinutesBetween_givenZDTsWithMinutesAndSecondsDifference_yieldsMinuteDifferenceRoundedDown()  {
        /*
        Timestamp start = new Timestamp(KNOWN_EPOCH_TIME_IN_MILLIS);
        long timeOffset = 4 * MINUTE_IN_MILLIS + 3 * SECOND_IN_MILLIS;
        Timestamp end = new Timestamp(KNOWN_EPOCH_TIME_IN_MILLIS + timeOffset);
         */

        // TODO
        fail("Not implemented!");


        ZonedDateTime start = null;
        ZonedDateTime end = null;

        assertThat("Start: Tuesday, 5 July 2022 11:49:35 End: Tuesday, 5 July 2022 16:54:38",
                TimeCalculator.differenceInMinutesBetween(start,end),is(4 * MINUTE_IN_MILLIS));
    }

    @Test
    void stringInRFC2822ToZonedDateTime() {
        // TODO
        fail("Not implemented!");
    }

    @Test
    void isStringRFC2822Format() {
        // TODO
        fail("Not implemented!");
    }


/*
    @Test
    void testTimeCalculator_givenEndBeforeStart_yieldsExpectedDifference() {
        Date start = null, end = null;
        long dayInMinutes = 24 * 60;

        try {
            end = formater.parse("Wed, 20 Dec 2000 16:01:07 +0200");
            start = formater.parse("Thu, 21 Dec 2000 16:01:07 +0200");

        } catch (Exception e) {
            fail("Dates in fixture cannot be instantiated!");
        }

        assertThat("Start: Thu, 21 Dec 2000 16:01:07 End: Wed, 20 Dec 2000 16:01:07",
                TimeCalculator.differenceInMinutesBetween(start,end),is(dayInMinutes));
    }

    @Test
    void testTimeCalculator_givenDatesWithMinutesAndSecondsDifference_yieldsMinuteDifferenceRoundedDown()  {
        Date start = null, end = null;

        try {
            start = formater.parse("Thu, 21 Dec 2000 16:01:07 +0200");
            end = formater.parse("Thu, 21 Dec 2000 16:09:00 +0200");

        } catch (Exception e) {
            fail("Dates in fixture cannot be instantiated!");
        }

        assertThat("Start: Thu, 21 Dec 2000 16:01:07 End: Thu, 21 Dec 2000 16:09:00",
                TimeCalculator.differenceInMinutesBetween(start,end),is(8l));
    }

    @Test
    void testTimeCalculator_givenDatesWithSecondsDifference_yieldsZeroMinutes()  {
        Date start = null, end = null;

        try {
            start = formater.parse("Thu, 21 Dec 2000 16:01:00 +0200");
            end = formater.parse("Thu, 21 Dec 2000 16:01:07 +0200");

        } catch (Exception e) {
            fail("Dates in fixture cannot be instantiated!");
        }

        assertThat("Start: Thu, 21 Dec 2000 16:01:00 End: Thu, 21 Dec 2000 16:01:07",
                TimeCalculator.differenceInMinutesBetween(start,end),is(0l));
    }

    @Test
    void testTimeCalculator_givenSameDates_yieldsZeroDifference()  {
        Date start = null;

        try {
            start = formater.parse("Thu, 21 Dec 2000 16:01:00 +0200");

        } catch (Exception e) {
            fail("Dates in fixture cannot be instantiated!");
        }

        assertThat("Start: Thu, 21 Dec 2000 16:01:00 End: Thu, 21 Dec 2000 16:01:00",
                TimeCalculator.differenceInMinutesBetween(start,start),is(0l));
    }

 */
}