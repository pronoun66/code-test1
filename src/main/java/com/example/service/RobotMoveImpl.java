package com.example.service;

import com.example.dto.Robot;
import com.example.dto.RobotMoveResult;
import com.example.type.Direction;

import java.util.List;

import static com.example.dto.RobotMoveResult.ResultType.*;

/**
 * Implementation of RobotMove
 */
public class RobotMoveImpl implements RobotMove {

    protected static final int TABLE_X_LENGTH = 5;
    protected static final int TABLE_Y_LENGTH = 5;

    private Robot robot = null;

    public String runCommands(List<String> commands) {
        RobotMoveResult result = new RobotMoveResult();
        for (String command : commands) {
            result = runCommand(command);
        }

        if (result.getRobotDirection() == null) {
            return "Last command should be 'REPORT'";
        }

        return result.getRobotX() + ","
                + result.getRobotY() + ","
                + result.getRobotDirection();
    }

    protected RobotMoveResult runCommand(String command) {
        if (command.contains("PLACE")) {
            return placeCommand(command);
        } else if (command.equals("MOVE")) {
            return moveCommand();
        } else if (command.equals("LEFT")) {
            return rotateCommand(command);
        } else if (command.equals("RIGHT")) {
            return rotateCommand(command);
        } else if (command.equals("REPORT")) {
            return reportCommand();
        } else {
            return new RobotMoveResult().setResult(ILLEGAL_ARGUMENT);
        }
    }

    private RobotMoveResult placeCommand(String command) {
        int x;
        int y;
        Direction direction;

        try {
            String[] strings = command.substring(6).split(",");
            x = Integer.valueOf(strings[0]);
            y = Integer.valueOf(strings[1]);
            direction = Direction.valueOf(strings[2]);
        } catch (IllegalArgumentException e) {
            return new RobotMoveResult().setResult(ILLEGAL_ARGUMENT);
        }

        if (x >= 0
                && x < TABLE_X_LENGTH
                && y >= 0
                && y < TABLE_Y_LENGTH) {
            robot = new Robot();
            robot.setX(x);
            robot.setY(y);
            robot.setDirection(direction);
            return new RobotMoveResult().setResult(SUCCESSFUL);
        } else {
            return new RobotMoveResult().setResult(OUT_OF_TABLE_BOUNDARY);
        }
    }

    private RobotMoveResult moveCommand() {
        if (robot == null) {
            return new RobotMoveResult().setResult(NOT_INITIALISED);
        }

        int futureX = robot.getX() + robot.getDirection().getXMove();
        int futureY = robot.getY() + robot.getDirection().getYMove();

        if (futureX >= 0
                && futureX < TABLE_X_LENGTH
                && futureY >= 0
                && futureY < TABLE_Y_LENGTH) {
            robot.setX(futureX);
            robot.setY(futureY);
            return new RobotMoveResult().setResult(SUCCESSFUL);
        } else {
            return new RobotMoveResult().setResult(OUT_OF_TABLE_BOUNDARY);
        }
    }

    private RobotMoveResult rotateCommand(String rotateDirection) {
        if (robot == null) {
            return new RobotMoveResult().setResult(NOT_INITIALISED);
        }

        if (rotateDirection.equals("LEFT")) {
            robot.setDirection(robot.getDirection().getRotateLeft());
        } else {
            robot.setDirection(robot.getDirection().getRotateRight());
        }
        return new RobotMoveResult().setResult(SUCCESSFUL);
    }

    private RobotMoveResult reportCommand() {
        if (robot == null) {
            return new RobotMoveResult().setResult(NOT_INITIALISED);
        }

        RobotMoveResult result = new RobotMoveResult();
        result.setResult(SUCCESSFUL)
                .setRobotX(this.robot.getX())
                .setRobotY(this.robot.getY())
                .setRobotDirection(this.robot.getDirection().name());
        return result;
    }
}
