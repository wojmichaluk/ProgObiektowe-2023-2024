package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class RandomPositionGenerator implements Iterable<Vector2d>, Iterator<Vector2d>{

    private List<Vector2d> positions = new ArrayList<>();

    public RandomPositionGenerator(int width, int height, int grassTufts){
        List<Integer[]> possibleXYValues = new ArrayList<>();
        for (Integer i=0; i<=width; i++){
            for (Integer j=0; j<=height; j++){
                possibleXYValues.add(new Integer[]{i,j});
            }
        }
        Random random = new Random();

        //zakładam, że liczba kępek trawy jest mniejsza niż liczba pól mapy
        for (int i=0; i<grassTufts; i++){
            int index = random.nextInt(possibleXYValues.size());
            Integer[] tupleXY = possibleXYValues.get(index);
            possibleXYValues.remove(index);
            positions.add(new Vector2d(tupleXY[0], tupleXY[1]));
        }
    }

    @Override
    public Iterator<Vector2d> iterator() {
        return positions.iterator();
    }

    @Override
    public boolean hasNext() {
        return positions.iterator().hasNext();
    }

    @Override
    public Vector2d next() {
        return positions.iterator().next();
    }
}
