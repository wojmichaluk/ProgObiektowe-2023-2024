package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static agh.ics.oop.model.MoveDirection.*;
import static org.junit.jupiter.api.Assertions.*;

public class AnimalTest {
    @Test
    void testToString(){
        //given
        Animal tested = new Animal(new Vector2d(2,1));

        //then
        assertEquals("N", tested.toString());
        assertEquals(MapDirection.NORTH.toString(), tested.toString());
        assertNotEquals("S", tested.toString());
        assertNotEquals(MapDirection.EAST.toString(), tested.toString());
    }

    @Test
    void testIsAt(){
        //given
        Animal tested = new Animal(new Vector2d(2,1));

        //then
        assertTrue(tested.isAt(new Vector2d(2, 1)));
        assertTrue(tested.isAt(tested.getPosition()));
        assertFalse(tested.isAt(new Vector2d(2, 0)));
        assertFalse(tested.isAt(new Vector2d(1, 3)));
    }

    @Test
    void testMove(){
        //given
        Animal tested = new Animal(new Vector2d(2,1));
        Animal blockade = new Animal(new Vector2d(4,1));
        GrassField grassFieldExample = new GrassField(10);
        try {
            grassFieldExample.place(tested);
            grassFieldExample.place(blockade);
        }catch(PositionAlreadyOccupiedException e){
            System.out.println("This won't happen in this case, but is needed due to place() call");
        }

        //when
        tested.move(grassFieldExample, RIGHT);
        tested.move(grassFieldExample, FORWARD);

        //then
        assertEquals(new Vector2d(3, 1), tested.getPosition());
        assertNotEquals(blockade.getPosition(), tested.getPosition());


        //when
        tested.move(grassFieldExample, FORWARD);
        tested.move(grassFieldExample, FORWARD);

        assertNotEquals(new Vector2d(5, 1), tested.getPosition());
        assertEquals(new Vector2d(3, 1), tested.getPosition());

        //when
        tested.move(grassFieldExample, LEFT);
        tested.move(grassFieldExample, BACKWARD);
        tested.move(grassFieldExample, BACKWARD);
        tested.move(grassFieldExample, BACKWARD);

        //then
        assertEquals(new Vector2d(3, 0), tested.getPosition());
        assertNotEquals(new Vector2d(3, -2), tested.getPosition());
    }

    @Test
    void testOrder(){
        //given
        Animal animal1 = new Animal(new Vector2d(1, 5));
        Animal animal2 = new Animal(new Vector2d(3, 1));
        Animal animal3 = new Animal(new Vector2d(2, 2));
        Animal animal4 = new Animal(new Vector2d(2, 4));
        List<Animal> animals = new ArrayList<>(List.of(animal1, animal2, animal3, animal4));
        RectangularMap mapExample = new RectangularMap(5, 5);
        for (Animal animal : animals){
            try {
                mapExample.place(animal);
            }catch(PositionAlreadyOccupiedException e){
                System.out.println(e.getMessage());
            }
        }

        //when
        Collection<Animal> orderedAnimals = mapExample.getOrderedAnimals();

        //then
        assertEquals(List.of(animal1, animal3, animal4, animal2), orderedAnimals);


        //given
        animal1 = new Animal(new Vector2d(6, 2));
        animal2 = new Animal(new Vector2d(4, 7));
        animal3 = new Animal(new Vector2d(4, 2));
        animal4 = new Animal(new Vector2d(4, 4));
        Animal animal5 = new Animal(new Vector2d(1, 10));
        Animal animal6 = new Animal(new Vector2d(5, 3));
        animals = new ArrayList<>(List.of(animal1, animal2, animal3, animal4, animal5, animal6));
        mapExample = new RectangularMap(10, 10);
        for (Animal animal : animals){
            try {
                mapExample.place(animal);
            }catch(PositionAlreadyOccupiedException e){
                System.out.println(e.getMessage());
            }
        }

        //when
        orderedAnimals = mapExample.getOrderedAnimals();

        //then
        assertEquals(List.of(animal5, animal3, animal4, animal2, animal6, animal1), orderedAnimals);
    }
}
