package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static agh.ics.oop.model.MoveDirection.*;
import static org.junit.jupiter.api.Assertions.*;


public class RectangularMapTest {
    @Test
    void integrationTest(){
        //uwaga: testy przygotowane na poprzednim laboratorium spełniają wymogi
        //instrukcji z laboratorium 5., więc zostawiam je tu bez zmian.

        //given
        RectangularMap map = new RectangularMap(10,10);

        Animal animal1 = new Animal(new Vector2d(1,5));
        Animal animal2 = new Animal(new Vector2d(4,7));
        Animal animal3 = new Animal(new Vector2d(0,2));
        Animal animal4 = new Animal(new Vector2d(6,3));
        Animal animal5 = new Animal(new Vector2d(10,12));
        Animal animal6 = new Animal(new Vector2d(4,7));

        //then
        assertTrue(map.place(animal1));
        assertTrue(map.place(animal2));
        assertTrue(map.place(animal3));
        assertTrue(map.place(animal4));
        assertFalse(map.place(animal5));
        assertFalse(map.place(animal6));


        //when
        map.move(animal1,RIGHT);
        map.move(animal1,FORWARD);
        map.move(animal1,FORWARD);
        map.move(animal1,FORWARD);

        map.move(animal2,BACKWARD);
        map.move(animal2,BACKWARD);
        map.move(animal2,BACKWARD);

        map.move(animal3,BACKWARD);
        map.move(animal3,BACKWARD);
        map.move(animal3,BACKWARD);

        map.move(animal4,LEFT);
        map.move(animal4,LEFT);
        map.move(animal4,FORWARD);
        map.move(animal4,FORWARD);

        map.move(animal5,RIGHT);
        map.move(animal5,RIGHT);

        map.move(animal6,LEFT);
        map.move(animal6,FORWARD);

        //then
        assertEquals(animal1,map.objectAt(new Vector2d(4,5)));
        assertEquals(animal2,map.objectAt(new Vector2d(4,6)));
        assertEquals(animal3,map.objectAt(new Vector2d(0,0)));
        assertEquals(animal4,map.objectAt(new Vector2d(6,1)));
        assertNull(map.objectAt(new Vector2d(10,12)));
        assertNull(map.objectAt(new Vector2d(4,7)));
    }
}
