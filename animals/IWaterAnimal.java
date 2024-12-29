package animals;

/**
 * An interface for water animals that can dive and have a dive depth.
 */
public interface IWaterAnimal {
    /**
     * Dives to a specified depth.
     *
     * @param depth The depth to dive to.
     */
    void dive(double depth);

    /**
     * Gets the current dive depth of the water animal.
     *
     * @return The dive depth.
     */
    double getDiveDept();

    /**
     * Gets the maximum dive depth allowed.
     *
     * @return The maximum dive depth.
     */
    static double getMaxDive() {
        return WaterAnimal.MAX_DIVE;
    }
}
