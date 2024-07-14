package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap implements WorldMap <Animal, Vector2d>{
    private Map<Vector2d, Animal> animals = new HashMap<>();
    private int width;
    private int height;

    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    //pomocnicza funkcja
    private boolean inBounds(Vector2d position){
        return position.precedes(new Vector2d(width, height)) && position.follows(new Vector2d(0,0));
    }
    public boolean canMoveTo(Vector2d position){
        return inBounds(position) && !isOccupied(position);
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

    public Animal objectAt(Vector2d position){
        return animals.get(position);
    }

    @Override
    public String toString() {
        MapVisualizer vis = new MapVisualizer(this);
        return vis.draw(new Vector2d(0, 0), new Vector2d(width, height));
    }
}
