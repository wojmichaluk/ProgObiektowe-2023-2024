package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapDirectionTest {
    @Test
    void testNext(){
        //then
        assertEquals(MapDirection.NORTH,MapDirection.WEST.next());
        assertEquals(MapDirection.EAST,MapDirection.NORTH.next());
        assertEquals(MapDirection.SOUTH,MapDirection.EAST.next());
        assertEquals(MapDirection.WEST,MapDirection.SOUTH.next());
    }

    @Test
    void testPrevious(){
        //then
        assertEquals(MapDirection.NORTH,MapDirection.EAST.previous());
        assertEquals(MapDirection.EAST,MapDirection.SOUTH.previous());
        assertEquals(MapDirection.SOUTH,MapDirection.WEST.previous());
        assertEquals(MapDirection.WEST,MapDirection.NORTH.previous());
    }

    @Test
    void testToUnitVector(){
        //then
        assertEquals("(0,1)",MapDirection.NORTH.toUnitVector().toString());
        assertEquals("(1,0)",MapDirection.EAST.toUnitVector().toString());
        assertEquals("(0,-1)",MapDirection.SOUTH.toUnitVector().toString());
        assertEquals("(-1,0)",MapDirection.WEST.toUnitVector().toString());
    }
}
