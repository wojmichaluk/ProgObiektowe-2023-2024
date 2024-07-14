package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static agh.ics.oop.model.MoveDirection.*;
import static agh.ics.oop.model.MoveDirection.BACKWARD;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class AnimalTest {
    @Test
    void testToString(){
        //given
        Animal tested = new Animal(new Vector2d(2,1));

        //then
        assertEquals("(2,1)", tested.toString());
        assertEquals(tested.getPosition().toString(), tested.toString());
        assertNotEquals("(2,2)", tested.toString());
        assertNotEquals(new Animal().toString(), tested.toString());
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

        //when
        tested.move(RIGHT);
        tested.move(FORWARD);

        //then
        assertEquals(new Vector2d(3, 1), tested.getPosition());
        assertNotEquals(new Vector2d(2, 2), tested.getPosition());


        //when
        tested.move(FORWARD);
        tested.move(FORWARD);

        assertNotEquals(new Vector2d(5, 1), tested.getPosition());
        assertEquals(new Vector2d(4, 1), tested.getPosition());

        //when
        tested.move(LEFT);
        tested.move(BACKWARD);
        tested.move(BACKWARD);
        tested.move(BACKWARD);

        //then
        assertEquals(new Vector2d(4, 0), tested.getPosition());
        assertNotEquals(new Vector2d(4, -2), tested.getPosition());
    }
}
