package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static agh.ics.oop.model.MoveDirection.*;
import static agh.ics.oop.model.MoveDirection.FORWARD;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TextMapTest {
    @Test
    void integrationTest() {
        //given
        TextMap map = new TextMap();

        String string1 = "zadanie";
        String string2 = "bonusowe";
        String string3 = "na";
        String string4 = "srebrna";
        String string5 = "skrzynke";
        String string6 = "bonusowe";
        String string7 = "srebrna";

        //then
        assertTrue(map.place(string1));
        assertTrue(map.place(string2));
        assertTrue(map.place(string3));
        assertTrue(map.place(string4));
        assertTrue(map.place(string5));
        assertFalse(map.place(string6));
        assertFalse(map.place(string7));

        assertFalse(map.isOccupied(0));
        assertTrue(map.isOccupied(1));
        assertTrue(map.isOccupied(2));
        assertTrue(map.isOccupied(3));
        assertTrue(map.isOccupied(4));
        assertTrue(map.isOccupied(5));
        assertFalse(map.isOccupied(6));
        assertFalse(map.isOccupied(7));


        //when
        map.move(string1, RIGHT);
        map.move(string1, FORWARD);
        map.move(string1, RIGHT);
        map.move(string1, FORWARD);

        map.move(string2, BACKWARD);
        map.move(string2, BACKWARD);
        map.move(string2, BACKWARD);

        map.move(string3, FORWARD);
        map.move(string3, FORWARD);
        map.move(string3, LEFT);

        map.move(string4, RIGHT);
        map.move(string4, BACKWARD);
        map.move(string4, LEFT);

        map.move(string5, RIGHT);
        map.move(string5, RIGHT);
        map.move(string5, RIGHT);

        //then
        assertEquals(string1, map.objectAt(2));
        assertEquals(string2, map.objectAt(1));
        assertEquals(string3, map.objectAt(4));
        assertEquals(string4, map.objectAt(3));
        assertEquals(string5, map.objectAt(5));
        assertNull(map.objectAt(6));
        assertNull(map.objectAt(7));


        //when
        map.move(string1, FORWARD);
        map.move(string1, LEFT);
        map.move(string1, FORWARD);

        map.move(string2, LEFT);
        map.move(string2, FORWARD);
        map.move(string2, FORWARD);

        map.move(string3, BACKWARD);
        map.move(string3, BACKWARD);
        map.move(string3, BACKWARD);

        map.move(string4, FORWARD);
        map.move(string4, BACKWARD);
        map.move(string4, LEFT);

        map.move(string5, FORWARD);
        map.move(string5, RIGHT);
        map.move(string5, RIGHT);

        //then
        assertEquals(string1, map.objectAt(4));
        assertEquals(string2, map.objectAt(1));
        assertEquals(string3, map.objectAt(5));
        assertEquals(string4, map.objectAt(2));
        assertEquals(string5, map.objectAt(3));
    }
}
