package com.hotelbeds.supplierintegrations.hackertest.detector;

// change to DetectorOperations and make a REST API?  -   AlGut [05/07/2022]
// since we are going the self implementation route, rather than exposing/injecting via Spring
// we will change the interface accordingly

public interface HackerDetectable {

    String parseLine(String line);
}

