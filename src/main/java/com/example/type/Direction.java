package com.example.type;

/**
 * Direction enum
 */
public enum Direction {
    NORTH,
    SOUTH,
    WEST,
    EAST;

    // movement on x axis
    private int xMove;
    // movement on y axis
    private int yMove;
    // next direction after turn left
    private Direction rotateLeft;
    // next direction after turn right
    private Direction rotateRight;

    static {
        initial(NORTH, 0, 1, Direction.WEST, Direction.EAST);
        initial(SOUTH, 0, -1, Direction.EAST, Direction.WEST);
        initial(WEST, -1, 0, Direction.SOUTH, Direction.NORTH);
        initial(EAST, 1, 0, Direction.NORTH, Direction.SOUTH);
    }

    private static void initial(Direction direction, int xMove, int yMove, Direction rotateLeft,
                          Direction
            rotateRight) {
        direction.xMove = xMove;
        direction.yMove = yMove;
        direction.rotateLeft = rotateLeft;
        direction.rotateRight = rotateRight;
    }

    public int getXMove() {
        return xMove;
    }

    public int getYMove() {
        return yMove;
    }

    public Direction getRotateLeft() {
        return rotateLeft;
    }

    public Direction getRotateRight() {
        return rotateRight;
    }
}
