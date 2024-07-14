package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import static agh.ics.oop.OptionsParser.parse;

public class World {
    public static void main(String[] args) {
        System.out.println("system wystartował");
        /*Na podstawie instrukcji z punktu 11. było:
        run(args);*/
        run(parse(args));
        System.out.println("system zakończył działanie");
    }
    static void run(MoveDirection[] args){
    /*Na podstawie instrukcji z punktu 11. było:
    static void run(String[] args)*/
        System.out.println("Start");
        /*Na podstawie instrukcji z punktu 9. było:
        System.out.println("zwierzak idzie do przodu");*/
        int i=0;
        while (i<args.length-1){
            System.out.print(args[i]+",");
            i+=1;
        }
        if (args.length > 0){
            System.out.println(args[i]);
        }
        for (i=0; i<args.length; i++) {
            /*Na podstawie instrukcji z punktu 15. było:
            String command = args[i];
            switch(command){
                case "f" -> System.out.println("zwierzak idzie do przodu");
                case "b" -> System.out.println("zwierzak idzie do tyłu");
                case "r" -> System.out.println("zwierzak skręca w prawo");
                case "l" -> System.out.println("zwierzak skręca w lewo");
                default -> System.out.println("nie znaleziono opcji");
            }*/
            switch (args[i]) {
                case FORWARD -> System.out.println("zwierzak idzie do przodu");
                case BACKWARD -> System.out.println("zwierzak idzie do tyłu");
                case RIGHT -> System.out.println("zwierzak skręca w prawo");
                case LEFT -> System.out.println("zwierzak skręca w lewo");
                default -> System.out.println("nie znaleziono opcji");
            }
        }
        System.out.println("Stop");
    }
}
