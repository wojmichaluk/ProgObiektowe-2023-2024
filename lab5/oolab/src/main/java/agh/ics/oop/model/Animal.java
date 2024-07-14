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

    @Override
    public String toString() {
        return direction.toString();
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
