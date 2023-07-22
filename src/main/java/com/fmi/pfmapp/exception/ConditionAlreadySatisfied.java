package com.fmi.pfmapp.exception;

public class ConditionAlreadySatisfied extends RuntimeException {
    public ConditionAlreadySatisfied(String message) {
        super(message);
    }

    public ConditionAlreadySatisfied(String message, Throwable cause) {
        super(message, cause);
    }
}
