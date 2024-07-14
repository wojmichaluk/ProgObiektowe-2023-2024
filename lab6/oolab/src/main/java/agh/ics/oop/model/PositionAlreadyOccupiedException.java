package agh.ics.oop.model;

public class PositionAlreadyOccupiedException extends Exception{

    private String message;
    public PositionAlreadyOccupiedException(Vector2d position) {
        message = "Position " + position + " is already occupied";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
