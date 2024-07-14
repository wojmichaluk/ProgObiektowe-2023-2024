package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RectangularMap extends AbstractWorldMap{
    private int width;
    private int height;

    public RectangularMap(int width, int height) {
        super(UUID.randomUUID());
        this.width = width;
        this.height = height;
    }

    @Override
    protected boolean inBounds(Vector2d position) {
        return position.precedes(new Vector2d(width, height)) && super.inBounds(position);
    }

    @Override
    public boolean canMoveTo(Vector2d position){
        return inBounds(position) && super.canMoveTo(position);
    }

    @Override
    public Boundary getCurrentBounds(){
        Vector2d lowerLeft = new Vector2d(0, 0);
        Vector2d upperRight = new Vector2d(width, height);
        return new Boundary(lowerLeft, upperRight);
    }
}
