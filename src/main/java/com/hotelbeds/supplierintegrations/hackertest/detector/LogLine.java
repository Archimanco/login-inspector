package com.hotelbeds.supplierintegrations.hackertest.detector;

import lombok.Value;

import java.time.ZonedDateTime;

/**
 * The log lines will be in the following format:
 * ip,date,action,username
 * IP look like  80.238.9.179 Date is in the epoch format like  1336129471 Action is one of the following:
 * SIGNIN_SUCCESS or  SIGNIN_FAILURE Username is a String like Will.Smith
 * A log line will therefore look like this: 80.238.9.179,133612947,SIGNIN_SUCCESS,Will.Smith
 */

@Value
public class LogLine {
    String ip;
    ZonedDateTime date;
    Enum action;
    String Name;
}
