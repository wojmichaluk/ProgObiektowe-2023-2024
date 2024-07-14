package agh.ics.oop.model;

import java.util.Objects;

public class Animal implements WorldElement {
    private MapDirection direction = MapDirection.NORTH;
    private Vector2d position;

    public Animal(){
        this(new Vector2d(2,2));
    }

    public Animal(Vector2d position){
        this.position = position;
    }

    public MapDirection getDirection() {
        return direction;
    }

    public Vector2d getPosition() {
        return position;
    }

    //na potrzeby komparatora
    public int getPositionX(){
        return this.position.getX();
    }

    //na potrzeby komparatora
    public int getPositionY(){
        return this.position.getY();
    }

    //zmienione na potrzeby etykiety
    @Override
    public String toString() {
        return "Z " + position.toString();
    }

    public boolean isAt(Vector2d position){
        return this.position.equals(position);
    }

    public void move(MoveValidator validator, MoveDirection direction){
        Vector2d newPosition = position;
        switch(direction){
            case LEFT -> this.direction = this.direction.previous();
            case RIGHT -> this.direction = this.direction.next();
            case FORWARD, BACKWARD -> newPosition = direction == MoveDirection.FORWARD ? position.add(this.direction.toUnitVector()) : position.subtract(this.direction.toUnitVector());
        }
        if (validator.canMoveTo(newPosition)) {
            this.position = newPosition;
        }
    }

    @Override
    public String imagePath(){
        return switch(this.direction){
            case NORTH -> "images/up.png";
            case EAST -> "images/right.png";
            case SOUTH -> "images/down.png";
            case WEST -> "images/left.png";
        };
    }

    //na potrzeby getElements()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(position, animal.position);
    }

    //na potrzeby getElements()
    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}
