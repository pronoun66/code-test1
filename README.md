# Project Title

Toy Robot Simulator, which supports 'PLACE', 'MOVE', 'LEFT', 'RIGHT', 'REPORT' commands

## Sample Code

    RobotSimulator robotSimulator = new RobotSimulatorImpl();
    String output = robotSimulator.execute(input);

Input:
    
    PLACE 1,2,EAST
    MOVE
    MOVE
    LEFT
    MOVE
    REPORT

Output:

    3,3,NORTH


more example in src/main/java/sample/RobotSimulatorSample.java
    
## Building

* Maven

```
mvn clean install
```

## Running tests

* Maven

```
mvn test
```

## Built With

* Maven

## Version
1.0

## Dependency

- Java 7 up 
- Junit 4.12
- mockito-all 1.10.19 