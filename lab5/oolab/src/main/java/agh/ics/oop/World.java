package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.List;

public class World {
    public static void main(String[] args) {
        List<MoveDirection> directions = OptionsParser.parse(args);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        GrassField grassFieldExample = new GrassField(10);
        Simulation simulation = new Simulation(positions, directions, grassFieldExample);
        simulation.run();
    }
}
