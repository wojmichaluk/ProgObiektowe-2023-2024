package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private List<Animal> animals = new ArrayList<>();
    private List<MoveDirection> directions;
    private WorldMap<Animal, Vector2d> map;
    public Simulation(List<Vector2d> positions, List<MoveDirection> directions, WorldMap <Animal, Vector2d> map){
        this.directions = directions;
        this.map = map;
        int length = positions.size();
        for (int i=0; i<length; i++){
            Animal animal = new Animal(positions.get(i));
            if (map.place(animal)){
                animals.add(animal);
            }
        }
    }

    //na potrzeby testów
    public List<Animal> getAnimals() {
        return animals;
    }

    public void run(){
        int length = directions.size();
        for (int i=0; i<length; i++) {
            int index = i % animals.size();
            Animal animal = animals.get(index);
            MoveDirection dir = directions.get(i);
            map.move(animal, dir);
            System.out.println("Zwierzę " + index + " : " + animal);
            System.out.println(map);
        }
    }
}
