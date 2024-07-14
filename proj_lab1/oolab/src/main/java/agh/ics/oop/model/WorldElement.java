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

    /**
     * Returns a path to the graphical representation of a world element.
     *
     * @return Filepath to the image corresponding to direction (for animal) or grass.
     */
    String imagePath();
}
