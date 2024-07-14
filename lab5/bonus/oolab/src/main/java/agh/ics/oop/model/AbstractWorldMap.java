package agh.ics.oop.model;

import java.util.*;

public abstract class AbstractWorldMap implements WorldMap{
    private Map<Vector2d, Animal> animals = new HashMap<>();

    protected Map<Vector2d, Animal> getAnimals() {
        return animals;
    }

    protected boolean inBounds(Vector2d position){
        return position.follows(new Vector2d(0, 0));
    }

    public boolean canMoveTo(Vector2d position){
        return !isOccupied(position);
    }

    public boolean place(Animal animal){
        if (canMoveTo(animal.getPosition())){
            animals.put(animal.getPosition(), animal);
            return true;
        }else{
            return false;
        }
    }

    public void move(Animal animal, MoveDirection direction){
        if (animals.containsValue(animal)){
            animals.remove(animal.getPosition());
            animal.move(this, direction);
            place(animal);
        }
    }

    public boolean isOccupied(Vector2d position){
        return animals.containsKey(position);
    }

    public WorldElement objectAt(Vector2d position){
        return animals.get(position);
    }

    public abstract String toString();

    public Collection<WorldElement> getElements(){
        Collection<WorldElement> elements = new HashSet<WorldElement>();
        for (Animal animal : animals.values()){
            elements.add(animal);
        }
        return elements;
    }
}
