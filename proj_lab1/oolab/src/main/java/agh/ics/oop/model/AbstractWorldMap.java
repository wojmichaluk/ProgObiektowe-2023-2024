package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractWorldMap implements WorldMap{

    private UUID mapId;
    private Map<Vector2d, Animal> animals = new HashMap<>();

    private List<MapChangeListener> observing = new ArrayList<>();

    public AbstractWorldMap(UUID mapId){
        this.mapId = mapId;
    }

    //na potrzeby ConsoleMapDisplay
    @Override
    public UUID getId() {
        return mapId;
    }

    public void addObserver(MapChangeListener mapChangeListener) {
        observing.add(mapChangeListener);
    }

    public void removeObserver(MapChangeListener mapChangeListener) {
        observing.remove(mapChangeListener);
    }

    @Override
    public void mapChanged(String message) {
        for (MapChangeListener mapChangeListener : observing) {
            mapChangeListener.mapChanged(this, message);
        }
    }

    protected Map<Vector2d, Animal> getAnimals() {
        return animals;
    }

    protected boolean inBounds(Vector2d position){
        return position.follows(new Vector2d(0, 0));
    }

    public boolean canMoveTo(Vector2d position){
        return !isOccupied(position);
    }

    public void place(Animal animal) throws PositionAlreadyOccupiedException{
        if (canMoveTo(animal.getPosition())){
            animals.put(animal.getPosition(), animal);
        }else{
            throw new PositionAlreadyOccupiedException(animal.getPosition());
        }
    }

    public void move(Animal animal, MoveDirection direction){
        if (animals.containsValue(animal)){
            animals.remove(animal.getPosition());
            animal.move(this, direction);
            animals.put(animal.getPosition(),animal);
        }
    }

    public boolean isOccupied(Vector2d position){
        return animals.containsKey(position);
    }

    public Optional<WorldElement> objectAt(Vector2d position){
        return Optional.ofNullable(animals.get(position));
    }

    public Collection<WorldElement> getElements(){
        Collection<WorldElement> elements = new HashSet<WorldElement>();
        for (Animal animal : animals.values()){
            elements.add(animal);
        }
        return elements;
    }

    public abstract Boundary getCurrentBounds();

    @Override
    public final String toString(){
        MapVisualizer vis = new MapVisualizer(this);
        Boundary boundaries = getCurrentBounds();
        return vis.draw(boundaries.lowerLeft(), boundaries.upperRight());
    }

    public Collection<Animal> getOrderedAnimals(){
        //Collectors.toCollection() nie kompiluje się - przyjąłem, że zwrócenie listy jest rozsądne
        /*Na podstawie punktu 3. sekcji "wyrażenia lambda" w części 1. było:
        List<Animal> animalsList = new ArrayList<>(animals.values().stream().toList());
        Collections.sort(animalsList, Comparator.comparing(Animal::getPositionX).thenComparing(Animal::getPositionY));
        return animalsList;*/
        return animals.values().stream().sorted(Comparator.comparing(Animal::getPositionX).thenComparing(Animal::getPositionY)).collect(Collectors.toList());
    }
}
