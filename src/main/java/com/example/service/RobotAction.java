package com.example.service;

import com.example.dto.RobotActionResult;
import com.example.type.Direction;

/**
 * Robot move operations
 */
public interface RobotAction {

    /**
     * Place a robot
     * @param x location of X
     * @param y location of Y
     * @param direction facing direction
     * @return RobotActionResult
     */
    RobotActionResult place(int x, int y, Direction direction);

    /**
     * Move a step toward facing direction. Robot won't step forward if it is going to fall off
     * table
     * @return RobotActionResult
     */
    RobotActionResult move();

    /**
     * Turn left
     * @return RobotActionResult
     */
    RobotActionResult left();

    /**
     * Turn right;
     * @return
     */
    RobotActionResult right();

    /**
     * Report robot's status
     * @return x,y,direction
     */
    RobotActionResult report();
}
