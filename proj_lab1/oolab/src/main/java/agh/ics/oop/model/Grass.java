package agh.ics.oop.model;

import java.util.Objects;

public class Grass implements WorldElement {
    private Vector2d position;

    public Grass(Vector2d position) {
        this.position = position;
    }

    public Vector2d getPosition() {
        return position;
    }

    //zmienione na potrzeby etykiety
    @Override
    public String toString() {
        return "Trawa";
    }

    @Override
    public String imagePath(){
        return "images/grass.png";
    }

    //na potrzeby getElements()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grass grass = (Grass) o;
        return Objects.equals(position, grass.position);
    }

    //na potrzeby getElements()
    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}
