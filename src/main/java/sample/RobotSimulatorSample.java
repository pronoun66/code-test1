package sample;

import com.example.service.RobotSimulator;
import com.example.service.RobotSimulatorImpl;

/**
 * Sample for RobotSimulator
 */
public class RobotSimulatorSample {

    public static void main(String[] args) {
        RobotSimulator robotSimulator = new RobotSimulatorImpl();

        String input = "PLACE 0,0,NORTH\n"
                + "MOVE\n"
                + "REPORT";

        String output = robotSimulator.execute(input);

        System.out.println("=== CASE 1 ===");
        System.out.println("Input:\n" + input);
        System.out.println();
        System.out.println("Output:\n" + output);
        System.out.println();

        input = "PLACE 0,0,NORTH\n"
                +"LEFT\n"
                + "REPORT";

        output = robotSimulator.execute(input);

        System.out.println("=== CASE 2 ===");
        System.out.println("Input:\n" + input);
        System.out.println();
        System.out.println("output:\n" + output);
        System.out.println();

        input = "PLACE 1,2,EAST\n"
                + "MOVE\n"
                + "MOVE\n"
                + "LEFT\n"
                + "MOVE\n"
                + "REPORT";

        output = robotSimulator.execute(input);

        System.out.println("=== CASE 3 ===");
        System.out.println("Input:\n" + input);
        System.out.println();
        System.out.println("Output:\n" + output);
    }
}
