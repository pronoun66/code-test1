package com.example.service;

import com.example.dto.RobotActionResult;
import com.example.type.Direction;

/**
 * Robot move operations
 */
public interface RobotAction {
    RobotActionResult place(int x, int y, Direction direction);

    RobotActionResult move();

    RobotActionResult left();

    RobotActionResult right();

    RobotActionResult report();
}
