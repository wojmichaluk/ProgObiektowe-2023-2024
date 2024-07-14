package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static agh.ics.oop.model.MoveDirection.*;

public class OptionsParser {
    public static List<MoveDirection> parse(String[] args){
        return Stream.of(args).map(OptionsParser::mapCommand).collect(Collectors.toList());
    }

    //funkcja używana w map()
    private static MoveDirection mapCommand(String command){
        try {
            switch (command) {
                case "f" -> {
                    return FORWARD;
                }case "b" -> {
                    return BACKWARD;
                }case "r" -> {
                    return RIGHT;
                }case "l" -> {
                    return LEFT;
                }default -> throw new IllegalArgumentException(command + " is not a legal move specification.");
            }
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
