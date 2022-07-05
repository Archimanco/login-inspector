package com.hotelbeds.supplierintegrations.hackertest.detector.utils;

import lombok.Value;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Write a function that returns the number of minutes (rounded down) between two
 * timestamps  time1 and  time2 in RFC 2822 format (ie: Thu, 21 Dec 2000 16:01:07 +0200).
 * Don’t forget about the time zones
 */
@Value
public class TimeCalculator {

    TimeZone timeZone;
    Locale locale;

    private static String FORMAT_RFC2822 = "EEE, dd MMM yyyy HH:mm:ss Z";
    private static int MIN_IN_SECONDS = 60;
    private static int MILLIS_IN_SECONDS = 1000;
    private static int MILLIS_IN_MINUTES = MILLIS_IN_SECONDS * MIN_IN_SECONDS;


    public String differenceInMinutesBetween(Timestamp start, Timestamp end){

        long differenceInMilliseconds = Math.abs(end.getTime() - start.getTime()); // milliseconds
        long differenceInMinutes = differenceInMilliseconds / MILLIS_IN_MINUTES;
        long differenceInSecondsRoundedDownToMinutes = differenceInMinutes * MIN_IN_SECONDS;

        Instant instantFromDifference = Instant.ofEpochSecond(differenceInSecondsRoundedDownToMinutes);
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instantFromDifference, timeZone.toZoneId());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_RFC2822).withLocale(locale);
        /* old formats
            SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_RFC2822);
            dateFormat.setTimeZone(this.timeZone);
            return dateFormat.format(new Date(differenceInSecondsRoundedDownToMinutes));
        */

        return zonedDateTime.format(formatter);

    }


}
