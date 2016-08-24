package com.example.service;

import java.util.List;

/**
 * Robot move operations
 */
public interface RobotMove {
    /**
     * run list of string commands such as "PLACE x,y,direction", "MOVE", "LEFT", "RIGHT", "REPORT"
     * @param commands list of command
     * @return result of report command
     */
    String runCommands(List<String> commands);
}
