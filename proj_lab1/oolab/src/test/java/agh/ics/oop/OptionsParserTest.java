package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static agh.ics.oop.OptionsParser.parse;
import static org.junit.jupiter.api.Assertions.*;

public class OptionsParserTest {
    @Test
    void testParse(){
        //given
        String[] args1 = {"f", "r", "l", "nope", "b"};
        String[] args2 = {"f", "f", "l", "l", "r"};
        String[] args3 = {"l", "nie", "nadal nie", "nope", "still nope"};
        String[] args4 = {"f", "prawo", "lewo", "b", "b"};

        //when
        List<MoveDirection> result1 = new ArrayList<>();
        {
            result1.add(MoveDirection.FORWARD);
            result1.add(MoveDirection.RIGHT);
            result1.add(MoveDirection.LEFT);
            result1.add(MoveDirection.BACKWARD);
        }
        List<MoveDirection> result2 = new ArrayList<>();
        {
            result2.add(MoveDirection.FORWARD);
            result2.add(MoveDirection.FORWARD);
            result2.add(MoveDirection.LEFT);
            result2.add(MoveDirection.LEFT);
            result2.add(MoveDirection.RIGHT);
        }
        List<MoveDirection> result3 = new ArrayList<>();
        {
            result3.add(MoveDirection.LEFT);
        }
        List<MoveDirection> result4 = new ArrayList<>();
        {
            result4.add(MoveDirection.FORWARD);
            result4.add(MoveDirection.BACKWARD);
            result4.add(MoveDirection.BACKWARD);
        }

        //then
        try {
            List<MoveDirection> moves1 = parse(args1);
            assertArrayEquals(result1.toArray(), moves1.toArray());
        }catch (RuntimeException e){
            System.out.print("");
        }
        try {
            List<MoveDirection> moves2 = parse(args2);
            assertArrayEquals(result2.toArray(), moves2.toArray());
        }catch (RuntimeException e){
            System.out.print("");
        }
        try {
            List<MoveDirection> moves3 = parse(args3);
            assertArrayEquals(result3.toArray(), moves3.toArray());
        }catch (RuntimeException e){
            System.out.print("");
        }
        try {
            List<MoveDirection> moves4 = parse(args4);
            assertArrayEquals(result4.toArray(), moves4.toArray());
        }catch (RuntimeException e){
            System.out.print("");
        }
    }
}
