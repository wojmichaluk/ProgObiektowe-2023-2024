package agh.ics.oop.model;

public class Animal {
    private MapDirection direction = MapDirection.NORTH;
    private Vector2d position;

    public Animal(){
        this(new Vector2d(2,2));
    }

    public Animal(Vector2d position){
        this.position = position;
    }

    //na potrzeby testów integracyjnych
    public MapDirection getDirection() {
        return direction;
    }

    //na potrzeby testów integracyjnych
    public Vector2d getPosition() {
        return position;
    }

    @Override
    public String toString() {
        //gdybyśmy potrzebowali pełnych informacje o zwierzęciu
        //return position.toString() + ", " + direction.toString();

        //informacje o pozycji, zgodnie z poleceniem w instrukcji co do Simulation.run()
        return position.toString();
    }

    public boolean isAt(Vector2d position){
        return this.position.equals(position);
    }

    public void move(MoveDirection direction){
        switch(direction){
            case LEFT -> this.direction = this.direction.previous();
            case RIGHT -> this.direction = this.direction.next();
            case FORWARD, BACKWARD-> {
                Vector2d newPosition = direction == MoveDirection.FORWARD ? position.add(this.direction.toUnitVector()) : position.subtract(this.direction.toUnitVector());
                if (newPosition.follows(new Vector2d(0,0)) && newPosition.precedes(new Vector2d(4,4))){
                    position = newPosition;
                }
            }
        }
    }
}
