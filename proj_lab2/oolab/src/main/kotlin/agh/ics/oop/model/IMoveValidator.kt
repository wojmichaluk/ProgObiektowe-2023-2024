package agh.ics.oop.model;

interface IMoveValidator {

    /**
     * Indicate if animal can move to the given position.
     *
     * @param position
     * The position checked for the movement possibility.
     * @return True if the animal can move to that position.
     */
    fun canMoveTo(position: Vector2d): Boolean
}