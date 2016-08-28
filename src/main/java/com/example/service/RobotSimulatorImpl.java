package com.example.service;

import com.example.dto.RobotActionResult;
import com.example.excep.RobotException;
import com.example.type.Direction;

import static com.example.dto.RobotActionResult.ResultType.SUCCESSFUL;
import static com.example.excep.RobotErrorCode.*;

/**
 * Implementation of RobotSimulator
 */
public class RobotSimulatorImpl implements RobotSimulator {

    private RobotAction robotAction;

    public RobotSimulatorImpl() {
        this.robotAction = new RobotActionImpl();
    }

    public RobotSimulatorImpl(RobotAction robotAction) {
        this.robotAction = robotAction;
    }

    public String execute(String input) {
        String[] commands = input.split("\n");
        int count = 1;
        String result = null;
        for (String command : commands) {
            try {
                result = run(robotAction, command);
            } catch (RobotException e) {
                return e.getMessage() + " on line " + count + ": " + command;
            }
            count++;
        }
        return result;
    }

    private String run(RobotAction robotSimulator, String command) throws RobotException {
        if (command.startsWith("PLACE")) {
            runPlaceCommand(robotSimulator, command);
        } else if (command.equals("MOVE")) {
            robotSimulator.move();
        } else if (command.equals("LEFT")) {
            robotSimulator.left();
        } else if (command.equals("RIGHT")) {
            robotSimulator.right();
        } else if (command.equals("REPORT")) {
            return runReportCommand(robotSimulator);
        } else {
            throw new RobotException(UNSUPPORTED_COMMAND.getMessage());
        }
        // all commands except "REPORT" return empty string by default
        return "";
    }

    private void runPlaceCommand(RobotAction robotSimulator, String command) throws RobotException {
        try {
            String[] args = command.substring(6).split(",");
            if (args.length != 3) {
                throw new IllegalArgumentException();
            }

            int x = Integer.valueOf(args[0].trim());
            int y = Integer.valueOf(args[1].trim());
            Direction direction = Direction.valueOf(args[2].trim());

            RobotActionResult result = robotSimulator.place(x, y, direction);
            if (!result.getResult().equals(SUCCESSFUL)) {
                throw new RobotException(INVALID_COMMAND.getMessage());
            }
        } catch (IllegalArgumentException e) {
            throw new RobotException(INVALID_ARGUMENT.getMessage());
        }
    }

    private String runReportCommand(RobotAction robotSimulator) throws RobotException {
        RobotActionResult result = robotSimulator.report();
        if (!result.getResult().equals(SUCCESSFUL)) {
            throw new RobotException(INVALID_COMMAND.getMessage());
        }

        return result.getRobotX() + ","
                + result.getRobotY() + ","
                + result.getRobotDirection();
    }
}
