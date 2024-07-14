package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.List;

public class Simulation implements Runnable{
    private List<Animal> animals = new ArrayList<>();
    private List<MoveDirection> directions;
    private WorldMap map;
    public Simulation(List<Vector2d> positions, List<MoveDirection> directions, WorldMap map){
        this.directions = directions;
        this.map = map;
        int length = positions.size();
        for (int i=0; i<length; i++){
            Animal animal = new Animal(positions.get(i));
            try{
                map.place(animal);
                animals.add(animal);
                map.mapChanged("Zwierze " + (animals.size() - 1) + " : " + animal.getPosition() + " umieszczone na mapie.");
            }catch(PositionAlreadyOccupiedException e){
                System.out.println(e.getMessage());
            }
        }
    }

    //na potrzeby testów
    public List<Animal> getAnimals() {
        return animals;
    }

    @Override
    public void run(){
        int length = directions.size();
        for (int i=0; i<length; i++) {
            int index = i % animals.size();
            Animal animal = animals.get(index);
            MoveDirection dir = directions.get(i);
            Vector2d previousPosition = animal.getPosition();
            map.move(animal, dir);
            Vector2d currentPosition = animal.getPosition();
            try {
                map.mapChanged("Zwierze " + index + " wykonalo ruch z " + previousPosition + " na " + currentPosition + "; orientacja: " + animal.getDirection());
                Thread.sleep(500);
            }catch(InterruptedException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
