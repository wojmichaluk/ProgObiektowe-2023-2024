package agh.ics.oop;

import agh.ics.oop.model.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimulationTest {
    @Test
    void integrationTest(){
        //given
        Vector2d position1 = new Vector2d(2, 2);
        Vector2d position2 = new Vector2d(1, 3);
        Vector2d position3 = new Vector2d(2, 2);
        Vector2d position4 = new Vector2d(2, 1);

        List<MoveDirection> directions = List.of(MoveDirection.RIGHT, MoveDirection.FORWARD,
                MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.FORWARD,
                MoveDirection.BACKWARD, MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.BACKWARD,
                MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.LEFT,
                MoveDirection.RIGHT);

        List<Vector2d> positions = List.of(position1, position2, position3, position4);
        RectangularMap rectangularMapExample = new RectangularMap(10, 10);
        Simulation simulation = new Simulation(positions, directions, rectangularMapExample);

        //when
        simulation.run();

        //then
        List<Animal> animals = simulation.getAnimals();
        assertEquals(MapDirection.SOUTH, animals.get(0).getDirection());
        assertEquals(MapDirection.WEST, animals.get(1).getDirection());
        assertEquals(MapDirection.NORTH, animals.get(2).getDirection());
        assertTrue(animals.get(0).isAt(new Vector2d(2, 1)));
        assertTrue(animals.get(1).isAt(new Vector2d(1, 5)));
        assertTrue(animals.get(2).isAt(new Vector2d(2, 0)));
    }
}

