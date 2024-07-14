package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static agh.ics.oop.model.MoveDirection.*;
import static agh.ics.oop.model.MoveDirection.FORWARD;
import static org.junit.jupiter.api.Assertions.*;

public class GrassFieldTest{
    @Test
    void integrationTest(){
        //w miejscach gdzie wiem, że ma się znaleźć zwierzę, używam get() bez sprawdzenia isPresent()

        //given
        GrassField field = new GrassField(10);

        Animal animal1 = new Animal(new Vector2d(1,5));
        Animal animal2 = new Animal(new Vector2d(4,7));
        Animal animal3 = new Animal(new Vector2d(0,2));
        Animal animal4 = new Animal(new Vector2d(6,3));
        Animal animal5 = new Animal(new Vector2d(8,0));
        Animal animal6 = new Animal(new Vector2d(0,2));

        //when
        try {
            field.place(animal1);
        }catch(PositionAlreadyOccupiedException e){
            System.out.println(e.getMessage());
        }

        try {
            field.place(animal2);
        }catch(PositionAlreadyOccupiedException e){
            System.out.println(e.getMessage());
        }

        try {
            field.place(animal3);
        }catch(PositionAlreadyOccupiedException e){
            System.out.println(e.getMessage());
        }

        try {
            field.place(animal4);
        }catch(PositionAlreadyOccupiedException e){
            System.out.println(e.getMessage());
        }

        try {
            field.place(animal5);
        }catch(PositionAlreadyOccupiedException e){
            System.out.println(e.getMessage());
        }

        try {
            field.place(animal6);
        }catch(PositionAlreadyOccupiedException e){
            System.out.println(e.getMessage());
        }

        //then
        assertEquals(animal1, field.objectAt(animal1.getPosition()).get());
        assertEquals(animal2, field.objectAt(animal2.getPosition()).get());
        assertEquals(animal3, field.objectAt(animal3.getPosition()).get());
        assertEquals(animal4, field.objectAt(animal4.getPosition()).get());
        assertEquals(animal5, field.objectAt(animal5.getPosition()).get());
        assertNotSame(animal6, field.objectAt(animal6.getPosition()).get());


        //when
        field.move(animal1,RIGHT);
        field.move(animal1,FORWARD);
        field.move(animal1,FORWARD);
        field.move(animal1,FORWARD);

        field.move(animal2,BACKWARD);
        field.move(animal2,BACKWARD);
        field.move(animal2,BACKWARD);

        field.move(animal3,BACKWARD);
        field.move(animal3,BACKWARD);
        field.move(animal3,BACKWARD);

        field.move(animal4,LEFT);
        field.move(animal4,LEFT);
        field.move(animal4,FORWARD);
        field.move(animal4,FORWARD);

        field.move(animal5,RIGHT);
        field.move(animal5,FORWARD);
        field.move(animal5,FORWARD);
        field.move(animal5,FORWARD);

        field.move(animal6,LEFT);
        field.move(animal6,FORWARD);
        field.move(animal6,RIGHT);
        field.move(animal6,BACKWARD);

        //then
        assertEquals(animal1,field.objectAt(new Vector2d(4,5)).get());
        assertEquals(animal2,field.objectAt(new Vector2d(4,6)).get());
        assertEquals(animal3,field.objectAt(new Vector2d(0,0)).get());
        assertEquals(animal4,field.objectAt(new Vector2d(6,1)).get());
        assertEquals(animal5,field.objectAt(new Vector2d(11,0)).get());
        assertNotEquals(true, field.objectAt(new Vector2d(0, 2)).isPresent());


        //when
        field.move(animal2,FORWARD);
        field.move(animal2,FORWARD);
        field.move(animal2,FORWARD);
        field.move(animal2,FORWARD);
        field.move(animal2,FORWARD);

        field.move(animal3,RIGHT);
        field.move(animal3,BACKWARD);
        field.move(animal3,BACKWARD);
        field.move(animal3,BACKWARD);

        //then
        assertEquals(animal2,field.objectAt(new Vector2d(4,11)).get());
        assertEquals(animal3,field.objectAt(new Vector2d(0,0)).get());
    }
}
