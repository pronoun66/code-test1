package com.example.service;


import com.example.dto.RobotActionResult;
import com.example.type.Direction;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.example.dto.RobotActionResult.ResultType.*;
import static org.junit.Assert.assertEquals;

/**
 * RobotActionImplTest
 */
public class RobotActionImplTest {

    private int tableXLength = 5;
    private int tableYLength = 5;

    private RobotAction robotAction = new RobotActionImpl(tableXLength, tableYLength);

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void place_successful() throws Exception {
        // Given
        int x = 1, y = 2;
        Direction direction = Direction.EAST;

        // When
        RobotActionResult result = robotAction.place(x, y, direction);

        // Verify
        assertEquals(SUCCESSFUL, result.getResult());

        RobotActionResult reportResult = robotAction.report();
        assertEquals(1, reportResult.getRobotX());
        assertEquals(2, reportResult.getRobotY());
        assertEquals(Direction.EAST, reportResult.getRobotDirection());
    }

    @Test
    public void place_xLessThan0_causeOutOfTableBoundary() throws Exception {
        // Given
        int x = -1, y = 2;
        Direction direction = Direction.EAST;

        // When
        RobotActionResult result = robotAction.place(x, y, direction);

        // Verify
        assertEquals(OUT_OF_TABLE_BOUNDARY, result.getResult());
    }

    @Test
    public void place_xEqualOrLargerThanTableXLength_causeOutOfTableBoundary() throws Exception {
        // Given
        int x = tableXLength, y = 2;
        Direction direction = Direction.EAST;

        // When
        RobotActionResult result = robotAction.place(x, y, direction);

        // Verify
        assertEquals(OUT_OF_TABLE_BOUNDARY, result.getResult());
    }

    @Test
    public void place_yLessThan0_causeOutOfTableBoundary() throws Exception {
        // Given
        int x = 1, y = -2;
        Direction direction = Direction.EAST;

        // When
        RobotActionResult result = robotAction.place(x, y, direction);

        // Verify
        assertEquals(OUT_OF_TABLE_BOUNDARY, result.getResult());
    }

    @Test
    public void place_yEqualOrLargerThanTableXLength_causeOutOfTableBoundary() throws Exception {
        // Given
        int x = 1, y = tableYLength;
        Direction direction = Direction.EAST;

        // When
        RobotActionResult result = robotAction.place(x, y, direction);

        // Verify
        assertEquals(OUT_OF_TABLE_BOUNDARY, result.getResult());
    }

    @Test
    public void place_invalidDirection_causeIllegalArgument() throws Exception {
        // Given
        int x = 1, y = 2;
        Direction direction = null;

        // When
        RobotActionResult result = robotAction.place(x, y, direction);

        // Verify
        assertEquals(ILLEGAL_ARGUMENT, result.getResult());
    }

    @Test
    public void move_successful() throws Exception {
        // Given
        robotAction.place(1, 2, Direction.EAST);
                
        // When
        RobotActionResult result = robotAction.move();

        // Verify
        assertEquals(SUCCESSFUL, result.getResult());

        RobotActionResult reportResult = robotAction.report();
        assertEquals(2, reportResult.getRobotX());
        assertEquals(2, reportResult.getRobotY());
        assertEquals(Direction.EAST, reportResult.getRobotDirection());
    }

    @Test
    public void move_noRobotPlaced_causeNotInitialised() throws Exception {
        // When
        RobotActionResult result = robotAction.move();

        // Verify
        assertEquals(NOT_INITIALISED, result.getResult());
    }

    @Test
    public void move_outTableLeftBoundary_causeOutOfTableBoundary() throws Exception {
        // Given
        robotAction.place(0, 0, Direction.WEST);

        // When
        RobotActionResult result = robotAction.move();

        // Verify
        assertEquals(OUT_OF_TABLE_BOUNDARY, result.getResult());

        // Robot's status doesn't change
        RobotActionResult reportResult = robotAction.report();
        assertEquals(0, reportResult.getRobotX());
        assertEquals(0, reportResult.getRobotY());
        assertEquals(Direction.WEST, reportResult.getRobotDirection());
    }

    @Test
    public void move_outTableRightBoundary_causeOutOfTableBoundary() throws Exception {
        // Given
        robotAction.place(4, 0, Direction.EAST);

        // When
        RobotActionResult result = robotAction.move();

        // Verify
        assertEquals(OUT_OF_TABLE_BOUNDARY, result.getResult());

        // Robot's status doesn't change
        RobotActionResult reportResult = robotAction.report();
        assertEquals(4, reportResult.getRobotX());
        assertEquals(0, reportResult.getRobotY());
        assertEquals(Direction.EAST, reportResult.getRobotDirection());
    }

    @Test
    public void move_outTableTopBoundary_causeOutOfTableBoundary() throws Exception {
        // Given
        robotAction.place(0, 4, Direction.NORTH);

        // When
        RobotActionResult result = robotAction.move();

        // Verify
        assertEquals(OUT_OF_TABLE_BOUNDARY, result.getResult());

        // Robot's status doesn't change
        RobotActionResult reportResult = robotAction.report();
        assertEquals(0, reportResult.getRobotX());
        assertEquals(4, reportResult.getRobotY());
        assertEquals(Direction.NORTH, reportResult.getRobotDirection());
    }

    @Test
    public void move_outTableBottomBoundary_causeOutOfTableBoundary() throws Exception {
        // Given
        robotAction.place(0, 0, Direction.SOUTH);

        // When
        RobotActionResult result = robotAction.move();

        // Verify
        assertEquals(OUT_OF_TABLE_BOUNDARY, result.getResult());

        // Robot's status doesn't change
        RobotActionResult reportResult = robotAction.report();
        assertEquals(0, reportResult.getRobotX());
        assertEquals(0, reportResult.getRobotY());
        assertEquals(Direction.SOUTH, reportResult.getRobotDirection());
    }

    @Test
    public void left_successful() throws Exception {
        // Given
        robotAction.place(0, 0, Direction.EAST);

        // When
        RobotActionResult result = robotAction.left();

        // Verify
        assertEquals(SUCCESSFUL, result.getResult());

        RobotActionResult reportResult = robotAction.report();
        assertEquals(0, reportResult.getRobotX());
        assertEquals(0, reportResult.getRobotY());
        assertEquals(Direction.NORTH, reportResult.getRobotDirection());

        // When
        result = robotAction.left();

        // Verify
        assertEquals(SUCCESSFUL, result.getResult());

        reportResult = robotAction.report();
        assertEquals(0, reportResult.getRobotX());
        assertEquals(0, reportResult.getRobotY());
        assertEquals(Direction.WEST, reportResult.getRobotDirection());

        // When
        result = robotAction.left();

        // Verify
        assertEquals(SUCCESSFUL, result.getResult());

        reportResult = robotAction.report();
        assertEquals(0, reportResult.getRobotX());
        assertEquals(0, reportResult.getRobotY());
        assertEquals(Direction.SOUTH, reportResult.getRobotDirection());

        // When
        result = robotAction.left();

        // Verify
        assertEquals(SUCCESSFUL, result.getResult());

        reportResult = robotAction.report();
        assertEquals(0, reportResult.getRobotX());
        assertEquals(0, reportResult.getRobotY());
        assertEquals(Direction.EAST, reportResult.getRobotDirection());
    }

    @Test
    public void left_noRobotPlaced_causeNotInitialised() throws Exception {
        // When
        RobotActionResult result = robotAction.left();

        // Verify
        assertEquals(NOT_INITIALISED, result.getResult());
    }

    @Test
    public void right_successful() throws Exception {
        // Given
        robotAction.place(0, 0, Direction.EAST);

        // When
        RobotActionResult result = robotAction.right();

        // Verify
        assertEquals(SUCCESSFUL, result.getResult());

        RobotActionResult reportResult = robotAction.report();
        assertEquals(0, reportResult.getRobotX());
        assertEquals(0, reportResult.getRobotY());
        assertEquals(Direction.SOUTH, reportResult.getRobotDirection());

        // When
        result = robotAction.right();

        // Verify
        assertEquals(SUCCESSFUL, result.getResult());

        reportResult = robotAction.report();
        assertEquals(0, reportResult.getRobotX());
        assertEquals(0, reportResult.getRobotY());
        assertEquals(Direction.WEST, reportResult.getRobotDirection());

        // When
        result = robotAction.right();

        // Verify
        assertEquals(SUCCESSFUL, result.getResult());

        reportResult = robotAction.report();
        assertEquals(0, reportResult.getRobotX());
        assertEquals(0, reportResult.getRobotY());
        assertEquals(Direction.NORTH, reportResult.getRobotDirection());

        // When
        result = robotAction.right();

        // Verify
        assertEquals(SUCCESSFUL, result.getResult());

        reportResult = robotAction.report();
        assertEquals(0, reportResult.getRobotX());
        assertEquals(0, reportResult.getRobotY());
        assertEquals(Direction.EAST, reportResult.getRobotDirection());
    }

    @Test
    public void right_noRobotPlaced_causeNotInitialised() throws Exception {
        // When
        RobotActionResult result = robotAction.right();

        // Verify
        assertEquals(NOT_INITIALISED, result.getResult());
    }

    @Test
    public void report_successful() throws Exception {
        // Given
        robotAction.place(1, 2, Direction.EAST);

        // When
        RobotActionResult result = robotAction.report();

        // Verify
        assertEquals(SUCCESSFUL, result.getResult());
        assertEquals(1, result.getRobotX());
        assertEquals(2, result.getRobotY());
        assertEquals(Direction.EAST, result.getRobotDirection());
    }

    @Test
    public void report_noRobotPlaced_causeNotInitialised() throws Exception {
        // When
        RobotActionResult result = robotAction.report();

        // Verify
        assertEquals(NOT_INITIALISED, result.getResult());
    }
}