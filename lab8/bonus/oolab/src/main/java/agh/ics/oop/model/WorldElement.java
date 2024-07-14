package agh.ics.oop.model;

public interface WorldElement {
    /**
     * Returns Vector2d type vector representing position of some world element.
     *
     * @return position of the world element
     */
    Vector2d getPosition();

    /**
     * Returns a string representing a world element.
     *
     * @return String representation of the world element.
     */
    String toString();
}
