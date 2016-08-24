package com.example.service;


import com.example.dto.RobotMoveResult;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static com.example.dto.RobotMoveResult.ResultType.*;
import static org.junit.Assert.assertEquals;

/**
 * RobotMoveImplTest
 */
public class RobotMoveImplTest {

    private RobotMoveImpl robotMove = new RobotMoveImpl();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void runCommands() throws Exception {
        // Given
        List<String> commands = new ArrayList<String>();
        commands.add("PLACE 0,0,NORTH");
        commands.add("MOVE");
        commands.add("REPORT");

        // When
        String result = robotMove.runCommands(commands);

        // Verify
        assertEquals("0,1,NORTH", result);

        // Given
        commands = new ArrayList<String>();
        commands.add("PLACE 0,0,NORTH");
        commands.add("LEFT");
        commands.add("REPORT");

        // When
        result = robotMove.runCommands(commands);

        // Verify
        assertEquals("0,0,WEST", result);

        // Given
        commands = new ArrayList<String>();
        commands.add("PLACE 1,2,EAST");
        commands.add("MOVE");
        commands.add("MOVE");
        commands.add("LEFT");
        commands.add("MOVE");
        commands.add("REPORT");

        // When
        result = robotMove.runCommands(commands);

        // Verify
        assertEquals("3,3,NORTH", result);
    }

    @Test
    public void runCommand_place() throws Exception {
        // Given
        String command = "PLACE 1,2,EAST";

        // When
        RobotMoveResult result = robotMove.runCommand(command);

        // Verify
        assertEquals(SUCCESSFUL, result.getResult());

        RobotMoveResult reportResult = robotMove.runCommand("REPORT");
        assertEquals(1, reportResult.getRobotX());
        assertEquals(2, reportResult.getRobotY());
        assertEquals("EAST", reportResult.getRobotDirection());
    }

    @Test
    public void runCommand_place_xLessThan0() throws Exception {
        // Given
        String command = "PLACE -1,2,EAST";

        // When
        RobotMoveResult result = robotMove.runCommand(command);

        // Verify
        assertEquals(OUT_OF_TABLE_BOUNDARY, result.getResult());
    }

    @Test
    public void runCommand_place_xEqualOrLargerThanTableXLength() throws Exception {
        // Given
        String command = "PLACE " + RobotMoveImpl.TABLE_X_LENGTH + ",2,EAST";

        // When
        RobotMoveResult result = robotMove.runCommand(command);

        // Verify
        assertEquals(OUT_OF_TABLE_BOUNDARY, result.getResult());
    }

    @Test
    public void runCommand_place_yLessThan0() throws Exception {
        // Given
        String command = "PLACE 1,-2,EAST";

        // When
        RobotMoveResult result = robotMove.runCommand(command);

        // Verify
        assertEquals(OUT_OF_TABLE_BOUNDARY, result.getResult());
    }

    @Test
    public void runCommand_place_yEqualOrLargerThanTableXLength() throws Exception {
        // Given
        String command = "PLACE 1," + RobotMoveImpl.TABLE_Y_LENGTH + ",EAST";

        // When
        RobotMoveResult result = robotMove.runCommand(command);

        // Verify
        assertEquals(OUT_OF_TABLE_BOUNDARY, result.getResult());
    }

    @Test
    public void runCommand_place_invalidDirection() throws Exception {
        // Given
        String command = "PLACE 1,2,NO";

        // When
        RobotMoveResult result = robotMove.runCommand(command);

        // Verify
        assertEquals(ILLEGAL_ARGUMENT, result.getResult());
    }

    @Test
    public void runCommand_move() throws Exception {
        // Given
        robotMove.runCommand("PLACE 1,2,EAST");

        // When
        RobotMoveResult result = robotMove.runCommand("MOVE");

        // Verify
        assertEquals(SUCCESSFUL, result.getResult());

        RobotMoveResult reportResult = robotMove.runCommand("REPORT");
        assertEquals(2, reportResult.getRobotX());
        assertEquals(2, reportResult.getRobotY());
        assertEquals("EAST", reportResult.getRobotDirection());
    }

    @Test
    public void runCommand_move_noRobotPlaced() throws Exception {
        // When
        RobotMoveResult result = robotMove.runCommand("MOVE");

        // Verify
        assertEquals(NOT_INITIALISED, result.getResult());
    }

    @Test
    public void runCommand_move_outTableLeftBoundary() throws Exception {
        // Given
        robotMove.runCommand("PLACE 0,0,WEST");

        // When
        RobotMoveResult result = robotMove.runCommand("MOVE");

        // Verify
        assertEquals(OUT_OF_TABLE_BOUNDARY, result.getResult());

        RobotMoveResult reportResult = robotMove.runCommand("REPORT");
        assertEquals(0, reportResult.getRobotX());
        assertEquals(0, reportResult.getRobotY());
        assertEquals("WEST", reportResult.getRobotDirection());
    }

    @Test
    public void runCommand_move_outTableRightBoundary() throws Exception {
        // Given
        robotMove.runCommand("PLACE 4,0,EAST");

        // When
        RobotMoveResult result = robotMove.runCommand("MOVE");

        // Verify
        assertEquals(OUT_OF_TABLE_BOUNDARY, result.getResult());

        RobotMoveResult reportResult = robotMove.runCommand("REPORT");
        assertEquals(4, reportResult.getRobotX());
        assertEquals(0, reportResult.getRobotY());
        assertEquals("EAST", reportResult.getRobotDirection());
    }

    @Test
    public void runCommand_move_outTableTopBoundary() throws Exception {
        // Given
        robotMove.runCommand("PLACE 0,4,NORTH");

        // When
        RobotMoveResult result = robotMove.runCommand("MOVE");

        // Verify
        assertEquals(OUT_OF_TABLE_BOUNDARY, result.getResult());

        RobotMoveResult reportResult = robotMove.runCommand("REPORT");
        assertEquals(0, reportResult.getRobotX());
        assertEquals(4, reportResult.getRobotY());
        assertEquals("NORTH", reportResult.getRobotDirection());
    }

    @Test
    public void runCommand_move_outTableBottomBoundary() throws Exception {
        // Given
        robotMove.runCommand("PLACE 0,0,SOUTH");

        // When
        RobotMoveResult result = robotMove.runCommand("MOVE");

        // Verify
        assertEquals(OUT_OF_TABLE_BOUNDARY, result.getResult());

        RobotMoveResult reportResult = robotMove.runCommand("REPORT");
        assertEquals(0, reportResult.getRobotX());
        assertEquals(0, reportResult.getRobotY());
        assertEquals("SOUTH", reportResult.getRobotDirection());
    }

    @Test
    public void runCommand_left() throws Exception {
        // Given
        robotMove.runCommand("PLACE 0,0,EAST");

        // When
        RobotMoveResult result = robotMove.runCommand("LEFT");

        // Verify
        assertEquals(SUCCESSFUL, result.getResult());

        RobotMoveResult reportResult = robotMove.runCommand("REPORT");
        assertEquals(0, reportResult.getRobotX());
        assertEquals(0, reportResult.getRobotY());
        assertEquals("NORTH", reportResult.getRobotDirection());

        // When
        result = robotMove.runCommand("LEFT");

        // Verify
        assertEquals(SUCCESSFUL, result.getResult());

        reportResult = robotMove.runCommand("REPORT");
        assertEquals(0, reportResult.getRobotX());
        assertEquals(0, reportResult.getRobotY());
        assertEquals("WEST", reportResult.getRobotDirection());

        // When
        result = robotMove.runCommand("LEFT");

        // Verify
        assertEquals(SUCCESSFUL, result.getResult());

        reportResult = robotMove.runCommand("REPORT");
        assertEquals(0, reportResult.getRobotX());
        assertEquals(0, reportResult.getRobotY());
        assertEquals("SOUTH", reportResult.getRobotDirection());

        // When
        result = robotMove.runCommand("LEFT");

        // Verify
        assertEquals(SUCCESSFUL, result.getResult());

        reportResult = robotMove.runCommand("REPORT");
        assertEquals(0, reportResult.getRobotX());
        assertEquals(0, reportResult.getRobotY());
        assertEquals("EAST", reportResult.getRobotDirection());
    }

    @Test
    public void runCommand_left_noRobotPlaced() throws Exception {
        // When
        RobotMoveResult result = robotMove.runCommand("LEFT");

        // Verify
        assertEquals(NOT_INITIALISED, result.getResult());
    }

    @Test
    public void runCommand_right() throws Exception {
        // Given
        robotMove.runCommand("PLACE 0,0,EAST");

        // When
        RobotMoveResult result = robotMove.runCommand("RIGHT");

        // Verify
        assertEquals(SUCCESSFUL, result.getResult());

        RobotMoveResult reportResult = robotMove.runCommand("REPORT");
        assertEquals(0, reportResult.getRobotX());
        assertEquals(0, reportResult.getRobotY());
        assertEquals("SOUTH", reportResult.getRobotDirection());

        // When
        result = robotMove.runCommand("RIGHT");

        // Verify
        assertEquals(SUCCESSFUL, result.getResult());

        reportResult = robotMove.runCommand("REPORT");
        assertEquals(0, reportResult.getRobotX());
        assertEquals(0, reportResult.getRobotY());
        assertEquals("WEST", reportResult.getRobotDirection());

        // When
        result = robotMove.runCommand("RIGHT");

        // Verify
        assertEquals(SUCCESSFUL, result.getResult());

        reportResult = robotMove.runCommand("REPORT");
        assertEquals(0, reportResult.getRobotX());
        assertEquals(0, reportResult.getRobotY());
        assertEquals("NORTH", reportResult.getRobotDirection());

        // When
        result = robotMove.runCommand("RIGHT");

        // Verify
        assertEquals(SUCCESSFUL, result.getResult());

        reportResult = robotMove.runCommand("REPORT");
        assertEquals(0, reportResult.getRobotX());
        assertEquals(0, reportResult.getRobotY());
        assertEquals("EAST", reportResult.getRobotDirection());
    }

    @Test
    public void runCommand_right_noRobotPlaced() throws Exception {
        // When
        RobotMoveResult result = robotMove.runCommand("RIGHT");

        // Verify
        assertEquals(NOT_INITIALISED, result.getResult());
    }

    @Test
    public void runCommand_report_noRobotPlaced() throws Exception {
        // When
        RobotMoveResult result = robotMove.runCommand("REPORT");

        // Verify
        assertEquals(NOT_INITIALISED, result.getResult());
    }

    @Test
    public void runCommand_unKnownCommand() throws Exception {
        // When
        RobotMoveResult result = robotMove.runCommand("XXXXXX");

        // Verify
        assertEquals(ILLEGAL_ARGUMENT, result.getResult());
    }

}