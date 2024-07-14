package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap extends AbstractWorldMap{
    private int width;
    private int height;

    public RectangularMap(int width, int height) {
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
    public String toString() {
        MapVisualizer vis = new MapVisualizer(this);
        return vis.draw(new Vector2d(0, 0), new Vector2d(width, height));
    }
}
