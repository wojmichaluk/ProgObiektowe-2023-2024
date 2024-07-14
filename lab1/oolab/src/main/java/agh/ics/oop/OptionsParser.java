package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.Arrays;

import static agh.ics.oop.model.MoveDirection.*;

public class OptionsParser {
    public static MoveDirection[] parse(String[] args){
        MoveDirection[] tab = new MoveDirection[args.length];
        int realLength = 0;
        for (int i=0; i<args.length; i++){
            String command = args[i];
            switch(command){
                case "f" -> tab[realLength++]=FORWARD;
                case "b" -> tab[realLength++]=BACKWARD;
                case "r" -> tab[realLength++]=RIGHT;
                case "l" -> tab[realLength++]=LEFT;
                default -> {}
            }
        }
        return Arrays.copyOfRange(tab,0,realLength);
    }
}
