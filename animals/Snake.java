package animals;

import Olympics.Medal;
import competition.RaceTrack;

import java.util.Arrays;

/**
 * Represents a Snake, which is a type of TerrestrialAnimal.
 */
public class Snake extends TerrestrialAnimals implements IReptile {
    /**
     * Enumeration for the level of poison in the snake.
     */
    public enum Poisonous {Low, Mid, High}

    private Poisonous poisonous;
    private double length;

    /**
     * Constructs a new Snake with the specified parameters.
     *
     * @param name             The name of the snake.
     * @param gender           The gender of the snake.
     * @param weight           The weight of the snake.
     * @param speed            The speed of the snake.
     * @param medals           An array of medals the snake has won.
     * @param noLegs           The number of legs the snake has.
     * @param poisonous        The level of poison in the snake.
     * @param length           The length of the snake.
     * @param maxEnergy        The maximum energy of the snake.
     * @param energyPerMeter   The energy consumption per meter of the snake.
     * @param imageName        The name of the image representing the snake.
     * @param heat             The heat number the snake is participating in.
     * @param raceTrack        The race track associated with the snake.
     */
    public Snake(String name, Gender gender, double weight, double speed, Medal[] medals, int noLegs, Poisonous poisonous, double length, int maxEnergy, int energyPerMeter, String imageName, int heat, RaceTrack raceTrack) {
        super(name, gender, weight, speed, medals, noLegs, maxEnergy, energyPerMeter, Arrays.asList("Land"), heat, raceTrack);
        this.poisonous = poisonous;
        this.length = length;
        loadImages(imageName, "Terrestrial");
    }

    /**
     * Gets the sound made by the snake.
     *
     * @return The sound made by the snake.
     */
    public String getSound() {
        return "ssssssss";
    }

    /**
     * Checks if this snake is equal to another object.
     *
     * @param o The object to compare with.
     * @return true if this snake is equal to the specified object, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Snake)) return false;
        if (!super.equals(o)) return false;
        return ((Snake) o).length == this.length &&
                this.poisonous == ((Snake) o).poisonous;
    }

    /**
     * Returns a string representation of this snake.
     *
     * @return A string representation of this snake.
     */
    @Override
    public String toString() {
        return "Snake -" +
                super.toString() +
                ", poisonous=" + this.poisonous +
                ", length=" + this.length +
                " ";
    }

    /**
     * Increases the speed of the snake by a specified increment, up to a maximum of 5.
     *
     * @param increment The amount to increase the speed by.
     */
    public void speedUp(int increment) {
        if (getSpeed() > 5 || getSpeed() + increment > 5) {
            System.out.print("Can't increment the speed, it is already at maximum");
        } else if (getSpeed() + increment <= 5) {
            setSpeed(increment);
            System.out.print("The speed boost was successful");
        }
    }
}
