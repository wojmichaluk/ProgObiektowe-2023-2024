package agh.ics.oop.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileMapDisplay implements MapChangeListener{
    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        String path = "map_" + worldMap.getId().toString() + ".log";
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path, true))){
            bufferedWriter.append(message);
            bufferedWriter.append("\n");
            bufferedWriter.append(worldMap.toString());
            bufferedWriter.append("\n\n");
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
