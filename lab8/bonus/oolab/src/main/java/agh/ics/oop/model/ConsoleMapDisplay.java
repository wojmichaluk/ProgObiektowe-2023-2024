package agh.ics.oop.model;

public class ConsoleMapDisplay implements MapChangeListener {
    private int howManyUpdates = 0;
    @Override
    public synchronized void mapChanged(WorldMap worldMap, String message){
        System.out.println("Map ID: " + worldMap.getId());
        System.out.println(message);
        System.out.print(worldMap);
        System.out.println("Number of already done updates: " + ++howManyUpdates + "\n");
    }
}
