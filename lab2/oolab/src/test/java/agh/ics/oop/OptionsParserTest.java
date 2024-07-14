package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
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
        assertArrayEquals(result1.toArray(), parse(args1).toArray());
        assertArrayEquals(result2.toArray(), parse(args2).toArray());
        assertArrayEquals(result3.toArray(), parse(args3).toArray());
        assertArrayEquals(result4.toArray(), parse(args4).toArray());
    }
}
