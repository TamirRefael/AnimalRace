package Graphics;

/**
 * An interface for animals that extends the IMoveable interface.
 */
public interface IAnimal extends IMoveable {
    /**
     * Feeds the animal with the specified amount of energy.
     *
     * @param energy The amount of energy to feed the animal.
     * @return true if the animal eats the energy, false otherwise.
     */
    boolean eat(int energy);
}
