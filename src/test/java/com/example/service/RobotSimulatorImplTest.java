package com.example.service;

import com.example.dto.RobotActionResult;
import com.example.type.Direction;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static com.example.dto.RobotActionResult.ResultType.FAILED;
import static com.example.dto.RobotActionResult.ResultType.ILLEGAL_ARGUMENT;
import static com.example.dto.RobotActionResult.ResultType.SUCCESSFUL;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * RobotSimulatorImplTest
 */
@RunWith(MockitoJUnitRunner.class)
public class RobotSimulatorImplTest {

    @InjectMocks
    private RobotSimulator robotSimulator = new RobotSimulatorImpl();

    @Spy
    private RobotAction robotAction = new RobotActionImpl();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void execute_commands_successful() throws Exception {
        // Given
        String input = "PLACE 1,2,EAST\n"
                + "MOVE\n"
                + "MOVE\n"
                + "LEFT\n"
                + "MOVE\n"
                + "REPORT";

        // When
        String result = robotSimulator.execute(input);

        // Verify
        assertEquals("3,3,NORTH", result);
    }

    @Test
    public void execute_PlaceCommand_callPlaceOfRobotAction() throws Exception {
        // Given
        String input = "PLACE 0,0,NORTH";

        // When
        robotSimulator.execute(input);

        // Verify
        verify(robotAction, times(1)).place(anyInt(), anyInt(), any(Direction.class));
    }

    @Test
    public void execute_PlaceCommand_invalidXFormat() throws Exception {
        // Given
        String input = "PLACE x,0,NORTH";

        // When
        String result = robotSimulator.execute(input);

        // Verify
        assertEquals("IllegalArgumentException on line 1: " + input,  result);
    }

    @Test
    public void execute_PlaceCommand_invalidYFormat() throws Exception {
        // Given
        String input = "PLACE 0,y,NORTH";

        // When
        String result = robotSimulator.execute(input);

        // Verify
        assertEquals("IllegalArgumentException on line 1: " + input,  result);
    }

    @Test
    public void execute_placeCommand_invalidDirectionFormat() throws Exception {
        // Given
        String input = "PLACE 0,0,XXXXXX";

        // When
        String result = robotSimulator.execute(input);

        // Verify
        assertEquals("IllegalArgumentException on line 1: " + input,  result);
    }

    @Test
    public void execute_placeCommand_incompleteArguments() throws Exception {
        // Given, missing direction
        String input = "PLACE 0,0";

        // When
        String result = robotSimulator.execute(input);

        // Verify
        assertEquals("IllegalArgumentException on line 1: " + input,  result);
    }

    @Test
    public void execute_placeCommand_failedResult_throwIllegalStateException() throws Exception {
        // Given
        String input = "PLACE 0,0,NORTH";
        RobotActionResult robotActionResult = new RobotActionResult();
        robotActionResult.setResult(FAILED);
        doReturn(robotActionResult).when(robotAction).place(anyInt(), anyInt(), any
                (Direction.class));

        // When
        String result = robotSimulator.execute(input);

        // Verify
        assertEquals("IllegalStateException on line 1: " + input,  result);
    }

    @Test
    public void execute_moveCommand_callMoveOfRobotAction() throws Exception {
        // Given
        String input = "MOVE";

        // When
        robotSimulator.execute(input);

        // Verify
        verify(robotAction, times(1)).move();
    }

    @Test
    public void execute_leftCommand_callLeftOfRobotAction() throws Exception {
        // Given
        String input = "LEFT";

        // When
        robotSimulator.execute(input);

        // Verify
        verify(robotAction, times(1)).left();
    }

    @Test
    public void execute_rightCommand_callRightOfRobotAction() throws Exception {
        // Given
        String input = "RIGHT";

        // When
        robotSimulator.execute(input);

        // Verify
        verify(robotAction, times(1)).right();
    }

    @Test
    public void execute_reportCommand_callReportOfRobotAction() throws Exception {
        // Given
        String input = "REPORT";

        // When
        robotSimulator.execute(input);

        // Verify
        verify(robotAction, times(1)).report();
    }

    @Test
    public void execute_reportCommand_successfulResult_correctMessageFormat() throws
            Exception {
        // Given
        String input = "REPORT";

        int x = 0, y = 0;
        Direction direction = Direction.EAST;

        RobotActionResult robotActionResult = new RobotActionResult();
        robotActionResult.setResult(SUCCESSFUL);
        robotActionResult.setRobotX(x);
        robotActionResult.setRobotY(y);
        robotActionResult.setRobotDirection(direction);
        doReturn(robotActionResult).when(robotAction).report();

        String expect = x + "," + y + "," + direction;

        // When
        String result = robotSimulator.execute(input);

        // Verify
        assertEquals(expect,  result);
    }

    @Test
    public void execute_reportCommand_failedResult_throwIllegalStateException() throws Exception {
        // Given
        String input = "REPORT";
        RobotActionResult robotActionResult = new RobotActionResult();
        robotActionResult.setResult(FAILED);
        doReturn(robotActionResult).when(robotAction).place(anyInt(), anyInt(), any
                (Direction.class));

        // When
        String result = robotSimulator.execute(input);

        // Verify
        assertEquals("IllegalStateException on line 1: " + input,  result);
    }

    @Test
    public void execute_unsupportedCommand_invalidDirectionFormat() throws Exception {
        // Given
        String input = "X";

        // When
        String result = robotSimulator.execute(input);

        // Verify
        assertEquals("IllegalArgumentException on line 1: " + input,  result);
    }

    @Test
    public void execute__showLineAndMessage_whenIllegalArgumentException() throws Exception {
        // Given
        String input = "PLACE 1,2,EAST\n"
                + "MOVE\n"
                + "MOVE\n"
                + "X";

        // When
        String result = robotSimulator.execute(input);

        // Verify
        assertEquals("IllegalArgumentException on line 4: X",  result);
    }
}