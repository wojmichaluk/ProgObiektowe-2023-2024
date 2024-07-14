package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.List;

public class World {
    public static void main(String[] args) {
        List<MoveDirection> directions = OptionsParser.parse(args);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        RectangularMap rectMapExample = new RectangularMap(6,6);
        Simulation simulation = new Simulation(positions, directions, rectMapExample);
        simulation.run();

        TextMap textMap = new TextMap();
        textMap.place("Ala");
        textMap.place("ma");
        textMap.place("sowoniedźwiedzia");
        System.out.println(textMap);
        textMap.move("ma", MoveDirection.RIGHT);
        textMap.move("ma", MoveDirection.FORWARD);
        textMap.move("ma", MoveDirection.FORWARD);
        System.out.println(textMap);
    }
}
