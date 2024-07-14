package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private List<Animal> animals = new ArrayList<>();
    private List<MoveDirection> directions;
    public Simulation(List<Vector2d> positions, List<MoveDirection> directions){
        this.directions = directions;
        int length = positions.size();
        for (int i=0; i<length; i++){
            animals.add(new Animal(positions.get(i)));
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
            animal.move(dir);
            System.out.println("Zwierzę " + index + " : " + animal);
        }
    }
}
