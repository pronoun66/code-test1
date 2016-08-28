package com.example.excep;

/**
 * Robot error code for RobotException
 */
public enum RobotErrorCode {
    INVALID_ARGUMENT("INVALID_ARGUMENT"),
    INVALID_COMMAND("INVALID_COMMAND"),
    UNSUPPORTED_COMMAND("UNSUPPORTED_COMMAND");

    private String message;

    RobotErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
