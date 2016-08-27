package com.example.dto;

import com.example.type.Direction;

/**
 * Result of robot's movement
 */
public class RobotActionResult {

    // Type of result
    private ResultType result;
    // robot's x location
    private int robotX;
    // robot's y location
    private int robotY;
    // robot's facing direction
    private Direction robotDirection;

    public ResultType getResult() {
        return result;
    }

    public RobotActionResult setResult(ResultType result) {
        this.result = result;
        return this;
    }

    public int getRobotX() {
        return robotX;
    }

    public RobotActionResult setRobotX(int robotX) {
        this.robotX = robotX;
        return this;
    }

    public int getRobotY() {
        return robotY;
    }

    public RobotActionResult setRobotY(int robotY) {
        this.robotY = robotY;
        return this;
    }

    public Direction getRobotDirection() {
        return robotDirection;
    }

    public void setRobotDirection(Direction robotDirection) {
        this.robotDirection = robotDirection;
    }

    public enum ResultType {
        SUCCESSFUL,
        FAILED,
        NOT_INITIALISED,
        OUT_OF_TABLE_BOUNDARY,
        ILLEGAL_ARGUMENT
    }
}
