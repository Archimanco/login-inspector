package com.hotelbeds.supplierintegrations.hackertest.utils;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.ZonedDateTime;

/**
 * Write a function that returns the number of minutes (rounded down) between two
 * timestamps  time1 and  time2 in RFC 2822 format (ie: Thu, 21 Dec 2000 16:01:07 +0200).
 * Don’t forget about the time zones
 */

public class TimeCalculator {

    private static int MIN_IN_SECONDS = 60;
    private static int MILLIS_IN_SECONDS = 1000;
    private static int MILLIS_IN_MINUTES = MILLIS_IN_SECONDS * MIN_IN_SECONDS;


    public static long differenceInMinutesBetween(Timestamp start, Timestamp end){

        long differenceInMilliseconds = Math.abs(end.getTime() - start.getTime());
        long differenceInMinutes = differenceInMilliseconds / MILLIS_IN_MINUTES;

        return differenceInMinutes;
    }

    public static long differenceInMinutesBetween(ZonedDateTime start, ZonedDateTime end){
        long differenceInSeconds = Duration.between(start,end).getSeconds();
        long differenceInMinutesRoundedDown = differenceInSeconds / MIN_IN_SECONDS;

        return differenceInMinutesRoundedDown;
    }
}