package com.example.service;

/**
 * RobotSimulator
 */
public interface RobotSimulator {

    /**
     * Execute commands including "PLACE", "MOVE", "LEFT", "RIGHT", "REPORT", which are divided
     * by line break "\n"
     * @param input input commands
     * @return output of the last command
     */
    String execute(String input);
}
