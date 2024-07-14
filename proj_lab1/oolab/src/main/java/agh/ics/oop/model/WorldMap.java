package agh.ics.oop.model;

import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * The interface responsible for interacting with the map of the world.
 * Assumes that Vector2d and MoveDirection classes are defined.
 *
 * @author apohllo, idzik
 */
public interface WorldMap extends MoveValidator {

    /**
     * Place a animal on the map.
     *
     * @param animal The animal to place on the map.
     * The animal cannot be placed if the move is not valid.
     */
    void place(Animal animal) throws PositionAlreadyOccupiedException;

    /**
     * Moves an animal (if it is present on the map) according to specified direction.
     * If the move is not possible, this method has no effect.
     */
    void move(Animal animal, MoveDirection direction);

    /**
     * Return true if given position on the map is occupied. Should not be
     * confused with canMove since there might be empty positions where the animal
     * cannot move.
     *
     * @param position Position to check.
     * @return True if the position is occupied.
     */
    boolean isOccupied(Vector2d position);

    /**
     * Return an Optional of world element (animal, grass) at a given position.
     *
     * @param position The position of the animal.
     * @return Optional of world element.
     */
    Optional<WorldElement> objectAt(Vector2d position);

    /**
     * Return a collection of world elements (animal and/or grass) together.
     *
     * @return Collection of world elements.
     */
    Collection<WorldElement> getElements();

    /**
     * Return boundaries for currently used map.
     *
     * @return Boundary Record object, containing Vector2d lowerLeft and Vector2d upperRight,
     * meaning left lower corner and upper right corner of the map, respectively.
     */
    Boundary getCurrentBounds();

    /**
     * Updates map state after every animal move.
     *
     * @param message Message including move details to be displayed.
     */
     void mapChanged(String message);

    /**
     * Returns unique identifier for the map.
     *
     * @return Unique UUID type identifier for the given map.
     */
     UUID getId();

    /**
     * Returns collection of animals ordered by their positions - firstly x-coordinate is considered,
     * for equal x-coordinates this method compares y-coordinates. Orders ascending.
     *
     * @return Collection of animals sorted as described above.
     */
     Collection<Animal> getOrderedAnimals();
}
