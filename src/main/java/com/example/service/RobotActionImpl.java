package com.example.service;

import com.example.dto.Robot;
import com.example.dto.RobotActionResult;
import com.example.type.Direction;

import static com.example.dto.RobotActionResult.ResultType.*;

/**
 * Implementation of RobotAction
 */
public class RobotActionImpl implements RobotAction {

    private int tableXLength = 5;
    private int tableYLength = 5;

    private Robot robot = null;

    public RobotActionImpl() {
    }

    public RobotActionImpl(int tableXLength, int tableYLength) {
        this.tableXLength = tableXLength;
        this.tableYLength = tableYLength;
    }

    public RobotActionResult place(int x, int y, Direction direction) {
        if (direction == null) {
            return new RobotActionResult().setResult(ILLEGAL_ARGUMENT);
        }

        if (x >= 0
                && x < tableXLength
                && y >= 0
                && y < tableYLength) {
            robot = new Robot();
            robot.setX(x);
            robot.setY(y);
            robot.setDirection(direction);
            return new RobotActionResult().setResult(SUCCESSFUL);
        } else {
            return new RobotActionResult().setResult(OUT_OF_TABLE_BOUNDARY);
        }
    }

    public RobotActionResult move() {
        if (robot == null) {
            return new RobotActionResult().setResult(NOT_INITIALISED);
        }

        int futureX = robot.getX() + robot.getDirection().getXMove();
        int futureY = robot.getY() + robot.getDirection().getYMove();

        if (futureX >= 0
                && futureX < tableXLength
                && futureY >= 0
                && futureY < tableYLength) {
            robot.setX(futureX);
            robot.setY(futureY);
            return new RobotActionResult().setResult(SUCCESSFUL);
        } else {
            return new RobotActionResult().setResult(OUT_OF_TABLE_BOUNDARY);
        }
    }

    public RobotActionResult left() {
        if (robot == null) {
            return new RobotActionResult().setResult(NOT_INITIALISED);
        }

        robot.setDirection(robot.getDirection().getRotateLeft());
        return new RobotActionResult().setResult(SUCCESSFUL);
    }

    public RobotActionResult right() {
        if (robot == null) {
            return new RobotActionResult().setResult(NOT_INITIALISED);
        }

        robot.setDirection(robot.getDirection().getRotateRight());
        return new RobotActionResult().setResult(SUCCESSFUL);
    }

    public RobotActionResult report() {
        if (robot == null) {
            return new RobotActionResult().setResult(NOT_INITIALISED);
        }

        RobotActionResult result = new RobotActionResult();
        result.setResult(SUCCESSFUL)
                .setRobotX(this.robot.getX())
                .setRobotY(this.robot.getY())
                .setRobotDirection(this.robot.getDirection());
        return result;
    }
}
