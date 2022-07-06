package com.hotelbeds.supplierintegrations.hackertest.detector;

public enum Action {
    SIGNIN_SUCCESS("SIGNIN_SUCCESS"),
    SIGNIN_FAILURE("SIGNIN_FAILURE");

    private String text;

    Action(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static Action fromString(String text) {
        for (Action action : Action.values()) {
            if (action.text.equalsIgnoreCase(text)) {
                return action;
            }
        }
        return null;
    }
}
