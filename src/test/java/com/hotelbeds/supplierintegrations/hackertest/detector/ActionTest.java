package com.hotelbeds.supplierintegrations.hackertest.detector;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class ActionTest {

    @Test
    void testGetText() {
        Action actionFailure = Action.SIGNIN_FAILURE;
        Action actionSuccess = Action.SIGNIN_SUCCESS;

        assertThat("Signin failure text",
                actionFailure.getText(),is("SIGNIN_FAILURE"));
        assertThat("Signin success text",
                actionSuccess.getText(),is("SIGNIN_SUCCESS"));
    }

    @Test
    void testFromString() {
        Action actionFailure = Action.fromString("SIGNIN_FAILURE");
        Action actionSuccess = Action.fromString("SIGNIN_SUCCESS");

        assertThat("Signin failure is created",
                actionFailure,is(Action.SIGNIN_FAILURE));
        assertThat("Signin success is created",
                actionSuccess,is(Action.SIGNIN_SUCCESS));
    }
}