package com.example.excep;

/**
 * RobotException
 */
public class RobotException extends Exception {

    public RobotException(String message) {
        super(message);
    }

    public RobotException(String message, Exception e) {
        super(message, e);
    }
}
