package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {
    @Test
    void testEquals(){
        //given
        Vector2d tested = new Vector2d(-2,1);

        //then
        assertEquals(tested, tested);
        assertEquals(tested, new Vector2d(-2, 1));
        assertNotEquals(tested, new Vector2d(2, -1));
        assertNotEquals(tested, 421.68);
    }

    @Test
    void testToString(){
        //then
        assertEquals("(-2,1)",new Vector2d(-2,1).toString());
        assertEquals("(-4,6)",new Vector2d(-4,6).toString());
        assertEquals("(4,3)",new Vector2d(4,3).toString());
        assertNotEquals("(-2,1)",new Vector2d(2,-1).toString());
    }

    @Test
    void testPrecedes(){
        //given
        Vector2d tested = new Vector2d(2, 3);

        //then
        assertTrue(tested.precedes(tested));
        assertTrue(tested.precedes(new Vector2d(4,5)));
        assertFalse(tested.precedes(new Vector2d(1,4)));
        assertFalse(tested.precedes(new Vector2d(3,-1)));
    }

    @Test
    void testFollows(){
        //given
        Vector2d tested = new Vector2d(2, 3);

        //then
        assertTrue(tested.follows(tested));
        assertTrue(tested.follows(new Vector2d(-2,1)));
        assertFalse(tested.follows(new Vector2d(3,5)));
        assertFalse(tested.follows(new Vector2d(3,-1)));
    }

    @Test
    void testUpperRight(){
        //given
        Vector2d vector1 = new Vector2d(2, 3);
        Vector2d vector2 = new Vector2d(0, 2);
        Vector2d vector3 = new Vector2d(-1, 5);
        Vector2d vector4 = new Vector2d(3, 1);

        //when
        Vector2d result1And2 = new Vector2d(2, 3);
        Vector2d result3And4 = new Vector2d(3, 5);
        Vector2d result1And3 = new Vector2d(2, 5);
        Vector2d result2And4 = new Vector2d(3, 2);

        //then
        assertEquals(result1And2, vector2.upperRight(vector1));
        assertEquals(result3And4, vector3.upperRight(vector4));
        assertEquals(result1And3, vector1.upperRight(vector3));
        assertEquals(result2And4, vector4.upperRight(vector2));
    }

    @Test
    void testLowerLeft(){
        //given
        Vector2d vector1 = new Vector2d(2, 3);
        Vector2d vector2 = new Vector2d(0, 2);
        Vector2d vector3 = new Vector2d(-1, 5);
        Vector2d vector4 = new Vector2d(3, 1);

        //when
        Vector2d result1And2 = new Vector2d(0, 2);
        Vector2d result3And4 = new Vector2d(-1, 1);
        Vector2d result1And3 = new Vector2d(-1, 3);
        Vector2d result2And4 = new Vector2d(0, 1);

        //then
        assertEquals(result1And2, vector2.lowerLeft(vector1));
        assertEquals(result3And4, vector3.lowerLeft(vector4));
        assertEquals(result1And3, vector1.lowerLeft(vector3));
        assertEquals(result2And4, vector4.lowerLeft(vector2));
    }

    @Test
    void testAdd(){
        //given
        Vector2d vector1 = new Vector2d(2, 3);
        Vector2d vector2 = new Vector2d(0, 2);
        Vector2d vector3 = new Vector2d(-1, 5);
        Vector2d vector4 = new Vector2d(3, 1);

        //when
        Vector2d result1And2 = new Vector2d(2, 5);
        Vector2d result3And4 = new Vector2d(2, 6);
        Vector2d result1And3 = new Vector2d(1, 8);
        Vector2d result2And4 = new Vector2d(3, 3);

        //then
        assertEquals(result1And2, vector2.add(vector1));
        assertEquals(result3And4, vector3.add(vector4));
        assertEquals(result1And3, vector1.add(vector3));
        assertEquals(result2And4, vector4.add(vector2));
    }

    @Test
    void testSubtract(){
        //given
        Vector2d vector1 = new Vector2d(2, 3);
        Vector2d vector2 = new Vector2d(0, 2);
        Vector2d vector3 = new Vector2d(-1, 5);
        Vector2d vector4 = new Vector2d(3, 1);

        //when
        Vector2d result1And2 = new Vector2d(-2, -1);
        Vector2d result3And4 = new Vector2d(-4, 4);
        Vector2d result1And3 = new Vector2d(3, -2);
        Vector2d result2And4 = new Vector2d(3, -1);

        //then
        assertEquals(result1And2, vector2.subtract(vector1));
        assertEquals(result3And4, vector3.subtract(vector4));
        assertEquals(result1And3, vector1.subtract(vector3));
        assertEquals(result2And4, vector4.subtract(vector2));
    }

    @Test
    void testOpposite(){
        //given
        Vector2d vector1 = new Vector2d(2, 3);
        Vector2d vector2 = new Vector2d(0, 2);
        Vector2d vector3 = new Vector2d(-1, 5);
        Vector2d vector4 = new Vector2d(3, 1);

        //when
        Vector2d result1 = new Vector2d(-2, -3);
        Vector2d result2 = new Vector2d(0, -2);
        Vector2d result3 = new Vector2d(1, -5);
        Vector2d result4 = new Vector2d(-3, -1);

        //then
        assertEquals(result1, vector1.opposite());
        assertEquals(result2, vector2.opposite());
        assertEquals(result3, vector3.opposite());
        assertEquals(result4, vector4.opposite());
    }
}
