package animals;

/**
 * An interface for reptile animals that have a maximum speed and can speed up.
 */
public interface IReptile {
    /**
     * The maximum speed of the reptile.
     */
    static final int MAX_SPEED = 5;

    /**
     * Increases the speed of the reptile by a specified increment, up to the maximum speed.
     *
     * @param increment The amount to increase the speed by.
     */
    void speedUp(int increment);
}
