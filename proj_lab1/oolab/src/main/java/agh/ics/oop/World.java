package agh.ics.oop;

import agh.ics.oop.model.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class World {
    public static void main(String[] args){
        List<MoveDirection> directions = OptionsParser.parse(args);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        GrassField grassFieldExample = new GrassField(10);
        RectangularMap rectangularMapExample = new RectangularMap(10, 10);

        ConsoleMapDisplay consoleMapDisplay = new ConsoleMapDisplay();
        grassFieldExample.addObserver(consoleMapDisplay);
        rectangularMapExample.addObserver(consoleMapDisplay);
        Simulation grassFieldSimulation = new Simulation(positions, directions, grassFieldExample);
        Simulation rectangularMapSimulation = new Simulation(positions, directions, rectangularMapExample);
        SimulationEngine simulationEngine = new SimulationEngine(List.of(rectangularMapSimulation, grassFieldSimulation));

        simulationEngine.runSync();
        System.out.println();
        simulationEngine.runAsync();
        try {
            simulationEngine.awaitSimulationsEnd();
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
        System.out.println("System zakończył działanie.");

        ConsoleMapDisplay AnotherConsoleMapDisplay = new ConsoleMapDisplay();
        List<Simulation> tooManySimulations = new ArrayList<>();
        for (int i = 0; i < 10000; i++){
            if (i % 2 == 0){
                GrassField tempGrassField = new GrassField(10);
                tempGrassField.addObserver(AnotherConsoleMapDisplay);
                tooManySimulations.add(new Simulation(positions, directions, tempGrassField));
            }
            else{
                RectangularMap tempRectangularMap = new RectangularMap(10, 10);
                tempRectangularMap.addObserver(AnotherConsoleMapDisplay);
                tooManySimulations.add(new Simulation(positions, directions, tempRectangularMap));
            }
        }

        SimulationEngine tooManySimulationsEngine = new SimulationEngine(tooManySimulations);
        tooManySimulationsEngine.runAsyncInThreadPool();
        try {
            tooManySimulationsEngine.awaitSimulationsEnd();
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
}
