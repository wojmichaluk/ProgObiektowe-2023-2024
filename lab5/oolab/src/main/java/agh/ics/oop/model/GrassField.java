package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

/*Na podstawie punktu nr 4 instrukcji było:
public class GrassField implements WorldMap {
    private HashMap<Vector2d, Grass> grassTufts = new HashMap<>();
    private HashMap<Vector2d, Animal> animals = new HashMap<>();

    public GrassField(int noFields) {
        Random rand = new Random();
        int upperBound = (int)Math.sqrt(10 * noFields);
        while (grassTufts.size() < noFields){
            int x = rand.nextInt(upperBound + 1);
            int y = rand.nextInt(upperBound + 1);
            Grass tempGrass = new Grass(new Vector2d(x, y));
            if (!grassTufts.containsKey(tempGrass.getPosition())){
                grassTufts.put(tempGrass.getPosition(), tempGrass);
            }
        }
    }

    //pomocnicza funkcja
    private boolean inBounds(Vector2d position){
        return position.precedes(new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE)) && position.follows(new Vector2d(0,0));
    }

    public boolean canMoveTo(Vector2d position){
        return inBounds(position) && !isOccupiedByAnimal(position);
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
        return isOccupiedByAnimal(position) || isOccupiedByGrass(position);
    }

    //rozbicie funkcji isOccupied na dwie na potrzeby zadania
    private boolean isOccupiedByAnimal(Vector2d position){
        return animals.containsKey(position);
    }

    private boolean isOccupiedByGrass(Vector2d position){
        return grassTufts.containsKey(position);
    }

    public WorldElement objectAt(Vector2d position) {
        Animal potentialAnimal = animals.get(position);
        return potentialAnimal == null ? grassTufts.get(position) : potentialAnimal;
    }

    @Override
    public String toString(){
        MapVisualizer vis = new MapVisualizer(this);
        Set<Vector2d> grassPositions = grassTufts.keySet();
        Set<Vector2d> animalPositions = animals.keySet();
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
}*/
public class GrassField extends AbstractWorldMap{
    private HashMap<Vector2d, Grass> grassTufts = new HashMap<>();

    public GrassField(int noFields) {
        Random rand = new Random();
        int upperBound = (int)Math.sqrt(10 * noFields);
        while (grassTufts.size() < noFields){
            int x = rand.nextInt(upperBound + 1);
            int y = rand.nextInt(upperBound + 1);
            Grass tempGrass = new Grass(new Vector2d(x, y));
            if (!grassTufts.containsKey(tempGrass.getPosition())){
                grassTufts.put(tempGrass.getPosition(), tempGrass);
            }
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
