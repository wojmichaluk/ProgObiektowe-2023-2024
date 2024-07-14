package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.ArrayList;
import java.util.List;

import static agh.ics.oop.model.MoveDirection.*;

public class OptionsParser {
    public static List<MoveDirection> parse(String[] args){
        List<MoveDirection> list = new ArrayList<>();
        for (String command : args){
            try {
                switch (command) {
                    case "f" -> list.add(FORWARD);
                    case "b" -> list.add(BACKWARD);
                    case "r" -> list.add(RIGHT);
                    case "l" -> list.add(LEFT);
                    default -> throw new IllegalArgumentException(command + " is not a legal move specification.");
                }
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
                throw new RuntimeException(e.getMessage());
            }
        }
        return list;
    }
}
