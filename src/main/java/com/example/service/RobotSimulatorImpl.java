package com.example.service;

import com.example.dto.RobotActionResult;
import com.example.type.Direction;

import static com.example.dto.RobotActionResult.ResultType.SUCCESSFUL;

/**
 * Implementation of RobotSimulator
 */
public class RobotSimulatorImpl implements RobotSimulator {

    private RobotAction robotAction = new RobotActionImpl();

    public String execute(String input) {
        String[] commands = input.split("\n");
        int count = 1;
        String result = null;
        for (String command : commands) {
            try {
                result = run(robotAction, command);
            } catch (IllegalArgumentException e) {
                return "IllegalArgumentException on line " + count + ": " + command;
            } catch (IllegalStateException e) {
                return "IllegalStateException on line " + count + ": " + command;
            }
            count++;
        }
        return result;
    }

    private String run(RobotAction robotSimulator, String command) {
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
            throw new IllegalArgumentException();
        }
        // all commands except "REPORT" return empty string by default
        return "";
    }

    private void runPlaceCommand(RobotAction robotSimulator, String command) {
        String[] args = command.substring(6).split(",");
        if (args.length != 3) {
            throw new IllegalArgumentException();
        }

        int x = Integer.valueOf(args[0].trim());
        int y = Integer.valueOf(args[1].trim());
        Direction direction = Direction.valueOf(args[2].trim());

        RobotActionResult result = robotSimulator.place(x, y, direction);
        if (!result.getResult().equals(SUCCESSFUL)) {
            throw new IllegalStateException();
        }
    }

    private String runReportCommand(RobotAction robotSimulator) {
        RobotActionResult result = robotSimulator.report();
        if (!result.getResult().equals(SUCCESSFUL)) {
            throw new IllegalStateException();
        }

        return result.getRobotX() + ","
                + result.getRobotY() + ","
                + result.getRobotDirection();
    }
}
