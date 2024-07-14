package agh.ics.oop.model;

import java.util.Objects;

import static agh.ics.oop.model.MoveDirection.FORWARD;

public class Text {
    //pomocnicza klasa
    private String string;
    private MapDirection direction = MapDirection.NORTH;

    public Text(String string) {
        this.string = string;
    }

    //metoda move() ma inn a sygnaturę niż w innych implementacjach -
    //uznałem, że tak będzie najbardziej efektywnie
    public Integer move(MoveValidator validator, MoveDirection direction, Integer index){
        switch (direction) {
            case LEFT -> this.direction = this.direction.previous();
            case RIGHT -> this.direction = this.direction.next();
            case FORWARD, BACKWARD -> {
                if (this.direction == MapDirection.EAST || this.direction == MapDirection.WEST){
                    int x = this.direction.toUnitVector().getX();
                    Integer neighbourIndex = direction == FORWARD ? index + x : index - x;
                    if (validator.canMoveTo(new Vector2d(neighbourIndex, 0))) {
                        return neighbourIndex;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return string;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Text text = (Text) o;
        return Objects.equals(string, text.string);
    }

    @Override
    public int hashCode() {
        return Objects.hash(string);
    }
}
