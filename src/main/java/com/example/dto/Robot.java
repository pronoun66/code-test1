package com.example.dto;

import com.example.type.Direction;

/**
 * Robot class
 */
public class Robot {

    // x location
    private int x;
    // y location
    private int y;
    // facing direction
    private Direction direction;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
