package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static agh.ics.oop.model.MoveDirection.*;
import static org.junit.jupiter.api.Assertions.*;


public class RectangularMapTest {
    @Test
    void integrationTest(){
        //w miejscach gdzie wiem, że ma się znaleźć zwierzę, używam get() bez sprawdzenia isPresent()

        //given
        RectangularMap map = new RectangularMap(10,10);

        Animal animal1 = new Animal(new Vector2d(1,5));
        Animal animal2 = new Animal(new Vector2d(4,7));
        Animal animal3 = new Animal(new Vector2d(0,2));
        Animal animal4 = new Animal(new Vector2d(6,3));
        Animal animal5 = new Animal(new Vector2d(0,2));
        Animal animal6 = new Animal(new Vector2d(4,7));

        //when
        try {
            map.place(animal1);
        }catch(PositionAlreadyOccupiedException e){
            System.out.println(e.getMessage());
        }

        try {
            map.place(animal2);
        }catch(PositionAlreadyOccupiedException e){
            System.out.println(e.getMessage());
        }

        try {
            map.place(animal3);
        }catch(PositionAlreadyOccupiedException e){
            System.out.println(e.getMessage());
        }

        try {
            map.place(animal4);
        }catch(PositionAlreadyOccupiedException e){
            System.out.println(e.getMessage());
        }

        try {
            map.place(animal5);
        }catch(PositionAlreadyOccupiedException e){
            System.out.println(e.getMessage());
        }

        try {
            map.place(animal6);
        }catch(PositionAlreadyOccupiedException e){
            System.out.println(e.getMessage());
        }

        //then
        assertEquals(animal1, map.objectAt(animal1.getPosition()).get());
        assertEquals(animal2, map.objectAt(animal2.getPosition()).get());
        assertEquals(animal3, map.objectAt(animal3.getPosition()).get());
        assertEquals(animal4, map.objectAt(animal4.getPosition()).get());
        assertNotSame(animal5, map.objectAt(animal5.getPosition()).get());
        assertNotSame(animal6, map.objectAt(animal6.getPosition()).get());

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
        assertEquals(animal1,map.objectAt(new Vector2d(4,5)).get());
        assertEquals(animal2,map.objectAt(new Vector2d(4,6)).get());
        assertEquals(animal3,map.objectAt(new Vector2d(0,0)).get());
        assertEquals(animal4,map.objectAt(new Vector2d(6,1)).get());
        assertNotEquals(true, map.objectAt(new Vector2d(0,2)).isPresent());
        assertNotEquals(true, map.objectAt(new Vector2d(4,7)).isPresent());
    }
}
