package agh.ics.oop.model

/**
 * The interface responsible for interacting with the map of the world.
 * Assumes that Vector2d and MoveDirection classes are defined.
 *
 * @author apohllo, idzik
 */

//przepisałem tylko te funkcje, które będą używane w ramach BouncyMap
interface IWorldMap: IMoveValidator {

    /**
     * Place an animal on the map.
     *
     * @param animal The animal to place on the map (if not present)
     * or update its position if it is already present on the map
     */
    fun place(animal: Animal)

    /**
     * Moves an animal (if it is present on the map) according to specified direction.
     * If the move is not possible, this method has no effect.
     *
     * @param animal The animal to be possibly moved
     * @param direction Direction of the move to be possibly made
     */
    fun move(animal: Animal, direction: MoveDirection)

    /**
     * Return true if given position on the map is occupied. Should not be
     * confused with canMove since there might be empty positions where the animal
     * cannot move.
     *
     * @param position Position to check.
     * @return True if the position is occupied.
     */
    fun isOccupied(position: Vector2d): Boolean


    /**
     * Return an animal at a given position or null if it is not present.
     *
     * @param position The position of the animal.
     * @return animal or null.
     */
    fun objectAt(position: Vector2d): Animal?
}
