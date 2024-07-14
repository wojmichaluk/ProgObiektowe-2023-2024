package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

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
        RectangularMap rectangularMapExample = new RectangularMap(10, 10);
        rectangularMapExample.place(tested);
        rectangularMapExample.place(blockade);

        //when
        tested.move(rectangularMapExample, RIGHT);
        tested.move(rectangularMapExample, FORWARD);

        //then
        assertEquals(new Vector2d(3, 1), tested.getPosition());
        assertNotEquals(blockade.getPosition(), tested.getPosition());


        //when
        tested.move(rectangularMapExample, FORWARD);
        tested.move(rectangularMapExample, FORWARD);

        assertNotEquals(new Vector2d(5, 1), tested.getPosition());
        assertEquals(new Vector2d(3, 1), tested.getPosition());

        //when
        tested.move(rectangularMapExample, LEFT);
        tested.move(rectangularMapExample, BACKWARD);
        tested.move(rectangularMapExample, BACKWARD);
        tested.move(rectangularMapExample, BACKWARD);

        //then
        assertEquals(new Vector2d(3, 0), tested.getPosition());
        assertNotEquals(new Vector2d(3, -2), tested.getPosition());
    }
}
