package com.hotelbeds.supplierintegrations.hackertest.detector;

import lombok.Value;

@Value
public class LogLine {
    String ip;
    long date;
    Action action;
    String Name;

    final static int IDX_IP = 0;
    final static int IDX_DATE = 1;
    final static int IDX_ACTION = 2;
    final static int IDX_USERNAME = 3;

    public static LogLine toLogLine(String lineToParse) {

        if(!isLogLineParseable(lineToParse))
            return null;

        String[] splittedStrings = lineToParse.split("[,]");

        return new LogLine(
                splittedStrings[IDX_IP],
                Long.parseLong(splittedStrings[IDX_DATE]),
                Action.fromString(splittedStrings[IDX_ACTION]),
                splittedStrings[IDX_USERNAME]
        );
    }

    public static boolean isLogLineParseable(String lineToTest) {

        if(lineToTest == null)
            return false;

        String[] splittedStrings = lineToTest.split("[,]");

        if(splittedStrings.length != 4)
            return false;

        if(!isIpValid(splittedStrings[IDX_IP]))
            return false;

        if(!isDateValid(splittedStrings[IDX_DATE]))
            return false;

        if(!isActionValid(splittedStrings[IDX_ACTION]))
            return false;

        if(!isUsernameValid(splittedStrings[IDX_USERNAME]))
            return false;

        return true;
    }

    public static boolean isIpValid(String stringUnderTest) {

        if(stringUnderTest == null)
            return false;

        String zeroTo255 = "(\\d{1,2}|(0|1)\\" + "d{2}|2[0-4]\\d|25[0-5])";
        String ipMaskRegex = zeroTo255 + "\\." + zeroTo255 + "\\." + zeroTo255 + "\\." + zeroTo255;

        return stringUnderTest.matches(ipMaskRegex);
    }

    public static boolean isDateValid(String stringUnderTest) {

        if(stringUnderTest == null)
            return false;

        long longUnderTest = Long.parseLong(stringUnderTest);

        return longUnderTest >= 0;
    }

    public static boolean isActionValid(String stringUnderTest) {

        if(stringUnderTest == null)
            return false;

        return Action.fromString(stringUnderTest) != null;
    }

    public static boolean isUsernameValid(String stringUnderTest) {

        if(stringUnderTest == null)
            return false;

        String usernameMaskRegex = "([a-zA-Z]+\\.[a-zA-Z]+)";

        return stringUnderTest.matches(usernameMaskRegex);
    }
}
