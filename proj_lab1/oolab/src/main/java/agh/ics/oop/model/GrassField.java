package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GrassField extends AbstractWorldMap{
    private HashMap<Vector2d, Grass> grassTufts = new HashMap<>();

    public GrassField(int noFields) {
        super(UUID.randomUUID());
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
    public Optional<WorldElement> objectAt(Vector2d position) {
        Optional<WorldElement> potentialAnimal = super.objectAt(position);
        return potentialAnimal.isPresent() ? potentialAnimal : Optional.ofNullable(grassTufts.get(position));
    }

    @Override
    public Collection<WorldElement> getElements(){
        //Collectors.toCollection() nie kompiluje się - przyjąłem, że zwrócenie listy jest rozsądne
        return Stream.concat(getAnimals().values().stream(), grassTufts.values().stream()).collect(Collectors.toList());
    }

    @Override
    public Boundary getCurrentBounds(){
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
        return new Boundary(new Vector2d(minX, minY), new Vector2d(maxX, maxY));
    }
}
