package agh.ics.oop.model;

import java.util.HashMap;
import java.util.Map;

public class TextMap implements WorldMap<String, Integer> {
    private int currentLength = 0;
    private Map<Integer, Text> texts = new HashMap<>();

    public boolean canMoveTo(Vector2d position){
        return position.precedes(new Vector2d(currentLength,0)) && position.follows(new Vector2d(1,0));
    }

    public boolean place(String string){
        Text text = new Text(string);
        //zakładam, że napisy nie mogą się powtarzać
        if (texts.containsValue(text)){
            return false;
        }else{
            texts.put(++currentLength, text);
            return true;
        }
    }

    //pomocnicza funkcja
    private Integer findKey(String string){
        for (Integer i : texts.keySet()){
            if (texts.get(i).toString().equals(string)){
                return i;
            }
        }
        return null;
    }

    //pomocnicza funkcja
    private void swap(Integer index){
        Text textOne = texts.remove(index);
        Text textTwo = texts.remove(index+1);
        texts.put(index, textTwo);
        texts.put(index + 1, textOne);
    }

    public void move(String string, MoveDirection direction){
        Integer index = findKey(string);
        if (index != null) {
            Text text = texts.get(index);
            Integer neighbourIndex = text.move(this, direction, index);
            if (neighbourIndex != null){
                swap(Math.min(index, neighbourIndex));
            }
        }
    }

    public boolean isOccupied(Integer position){
        return 0<position && position<= currentLength;
    }

    public String objectAt(Integer position){
        Text text = texts.get(position);
        return text == null ? null : text.toString();
    }

    @Override
    public String toString() {
        return texts.values().toString();
    }
}
