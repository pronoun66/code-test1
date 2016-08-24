package com.example.dto;

/**
 * Result of robot's movement
 */
public class RobotMoveResult {

    // Type of result
    private ResultType result;
    // robot's x location
    private int robotX;
    // robot's y location
    private int robotY;
    // robot's facing direction
    private String robotDirection;

    public ResultType getResult() {
        return result;
    }

    public RobotMoveResult setResult(ResultType result) {
        this.result = result;
        return this;
    }

    public int getRobotX() {
        return robotX;
    }

    public RobotMoveResult setRobotX(int robotX) {
        this.robotX = robotX;
        return this;
    }

    public int getRobotY() {
        return robotY;
    }

    public RobotMoveResult setRobotY(int robotY) {
        this.robotY = robotY;
        return this;
    }

    public String getRobotDirection() {
        return robotDirection;
    }

    public RobotMoveResult setRobotDirection(String robotDirection) {
        this.robotDirection = robotDirection;
        return this;
    }

    public enum ResultType {
        SUCCESSFUL,
        NOT_INITIALISED,
        OUT_OF_TABLE_BOUNDARY,
        ILLEGAL_ARGUMENT
    }
}
