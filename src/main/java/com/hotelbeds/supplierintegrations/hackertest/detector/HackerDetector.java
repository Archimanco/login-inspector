package com.hotelbeds.supplierintegrations.hackertest.detector;

import org.springframework.stereotype.Component;

import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

// food for thought: force it to be a "real" singleton to ensure that only one class keeps track of the logs?
@Component
public class HackerDetector implements HackerDetectable{

        // will use a treemap to have our data sorted
    TreeMap<Long,LogLine> storedLogs = new TreeMap<>();

    private static int MAX_LOGIN_ATTEMPTS_ALLOWED = 5;
    private static long MINUTES_TO_WATCH_IN_MILLIS = 5 * 60 *1000;

    @Override
    public String parseLine(String lineInput) {

        LogLine logLine = LogLine.toLogLine(lineInput);

            // log line is corrupted or bad formatted
        if(logLine == null)
            return null;

            // we need to hold up to 5 minutes of login, since this is just the timeframe to watch!!
            // since the method is called right a new log line is produced, we know that we are more or less on sync
        popLogsFiveMinutesBefore(logLine.getDate());
        storedLogs.put(logLine.getDate(),logLine);

        int loginAttemptErrors =
                storedLogs.entrySet().stream()
                        .filter(entry -> entry.getValue().getIp().equals(logLine.getIp()))
                        .filter(entry -> entry.getValue().getAction().getText().equals(Action.SIGNIN_FAILURE.getText()))
                        .collect(Collectors.toList())
                        .size();

        if(loginAttemptErrors >= MAX_LOGIN_ATTEMPTS_ALLOWED)
            return logLine.getIp();
        else
            return null;
    }

    private void popLogsFiveMinutesBefore(long date) {
        long newTimeWindowStart = date - MINUTES_TO_WATCH_IN_MILLIS;
        SortedMap<Long, LogLine> testvariable = storedLogs.headMap(newTimeWindowStart);
        storedLogs.headMap(newTimeWindowStart).clear();
    }
}
