package com.hotelbeds.supplierintegrations.hackertest.detector;

import com.hotelbeds.supplierintegrations.hackertest.utils.TimeCalculator;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class HackerDetector implements HackerDetectable{

    private static int MAX_LOGIN_ATTEMPTS_ALLOWED = 5;
    private static long MINUTES_TO_WATCH = 5l;

    @Override
    public String parseLine(String lineInput) {

        // TODO
        if(!isLogLineParseable(lineInput)) {
            // Do Something if line is corrupted or does not make sense?
            // Ask if error should be thrown thus adjusting the interface
        }

        LogLine logLine = toLogLine(lineInput);

        List<LogLine> logLinesToTest =
                getLastLoginAttempsByIpAfterDate(
                        logLine.getIp(),
                        logLine.getDate().minusMinutes(MINUTES_TO_WATCH));

        int loginAttemptErrors =
                logLinesToTest.stream()
                        .filter(line -> line.getAction().compareTo(Action.SIGNIN_FAILURE) == 0)
                        .collect(Collectors.toList())
                        .size();

        if(loginAttemptErrors >= MAX_LOGIN_ATTEMPTS_ALLOWED)
            return logLine.getIp();
        else
            return null;
    }

    private List<LogLine> getLastLoginAttempsByIpAfterDate(String ip, ZonedDateTime start) {
        // TODO
        return null;
    }

    public static boolean isLogLineParseable(String lineToTest) {
        // TODO
        // check number of groups splitted by commas -> should be 4
        // check if timestamp ( epoch ) is valid
        // etc
        return true;
    }

    public static LogLine toLogLine(String lineToParse) {
        // ip,date,action,username
        final int ipPosition = 0;
        final int datePosition = 1;
        final int actionPosition = 2;
        final int usernamePosition = 3;

        String[] splittedGroups = lineToParse.split("[,]");

        return new LogLine(
                splittedGroups[ipPosition],
                TimeCalculator.stringInRFC2822ToZonedDateTime(splittedGroups[datePosition]),
                Action.fromString(splittedGroups[actionPosition]),
                splittedGroups[usernamePosition]
        );
    }
}
