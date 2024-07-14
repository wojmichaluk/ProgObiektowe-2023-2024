package agh.ics.oop.model;

import java.util.Map;

public enum MapDirection {
    NORTH (0),
    EAST (1),
    SOUTH (2),
    WEST (3);

    private final int value;

    MapDirection(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString(){
        return switch(this){
            case NORTH -> "Północ";
            case EAST -> "Wschód";
            case SOUTH -> "Południe";
            case WEST -> "Zachód";
        };
    }

    public MapDirection next(){
        MapDirection[] directions = MapDirection.values();
        return directions[(this.getValue() + 1) % directions.length];
    }

    public MapDirection previous(){
        MapDirection[] directions = MapDirection.values();
        return directions[(this.getValue() + directions.length - 1) % directions.length];
    }

    public Vector2d toUnitVector(){
        int index = this.getValue();
        return new Vector2d((int) Math.pow(-1 * (index % 2), (int) index / 2 + 2), (int) Math.pow(-1 + index % 2, (int) index / 2 + 2));
    }
}
