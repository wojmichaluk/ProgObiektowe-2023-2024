package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

public class GrassField extends AbstractWorldMap{
    private HashMap<Vector2d, Grass> grassTufts = new HashMap<>();

    public GrassField(int noFields) {
        int upperBound = (int)Math.sqrt(10 * noFields);
        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator(upperBound, upperBound, noFields);
        for(Vector2d grassPosition : randomPositionGenerator) {
            grassTufts.put(grassPosition, new Grass(grassPosition));
        }
    }

    @Override
    protected boolean inBounds(Vector2d position){
        return position.precedes(new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE)) && super.inBounds(position);
    }

    @Override
    public boolean canMoveTo(Vector2d position){
        return inBounds(position) && !super.isOccupied(position);
    }

    @Override
    public boolean isOccupied(Vector2d position){
        return isOccupiedByGrass(position) || super.isOccupied(position);
    }

    private boolean isOccupiedByGrass(Vector2d position){
        return grassTufts.containsKey(position);
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        WorldElement potentialAnimal = super.objectAt(position);
        return potentialAnimal == null ? grassTufts.get(position) : potentialAnimal;
    }

    @Override
    public String toString(){
        MapVisualizer vis = new MapVisualizer(this);
        Set<Vector2d> grassPositions = grassTufts.keySet();
        Set<Vector2d> animalPositions = super.getAnimals().keySet();
        int minX, minY, maxX, maxY;
        maxX = maxY = 0;
        minX = minY = Integer.MAX_VALUE;
        for (Vector2d position : grassPositions){
            int currentX = position.getX();
            int currentY = position.getY();
            if (currentX<minX){
                minX = currentX;
            }
            if (currentX>maxX){
                maxX = currentX;
            }
            if (currentY<minY){
                minY = currentY;
            }
            if (currentY>maxY){
                maxY = currentY;
            }
        }
        for (Vector2d position : animalPositions){
            int currentX = position.getX();
            int currentY = position.getY();
            if (currentX<minX){
                minX = currentX;
            }
            if (currentX>maxX){
                maxX = currentX;
            }
            if (currentY<minY) {
                minY = currentY;
            }
            if (currentY>maxY){
                maxY = currentY;
            }
        }
        return vis.draw(new Vector2d(minX,minY), new Vector2d(maxX, maxY));
    }

    @Override
    public Collection<WorldElement> getElements(){
        Collection<WorldElement> elements = super.getElements();
        for (Grass grassTuft : grassTufts.values()){
            elements.add(grassTuft);
        }
        return elements;
    }
}
