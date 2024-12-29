package Graphics;

import mobility.Point;

/**
 * An interface for objects that can move and have speed.
 */
public interface IMoveable {
    /**
     * Gets the name of the animal.
     *
     * @return The name of the animal.
     */
    String getAnimaleName();

    /**
     * Gets the speed of the animal.
     *
     * @return The speed of the animal.
     */
    int getSpeed();

    /**
     * Moves the animal to a new location.
     */
    void move();
}
