package animals;

import Olympics.Medal;
import competition.RaceTrack;

import java.util.Arrays;
import java.util.Objects;

/**
 * Represents a Whale, which is a type of WaterAnimal.
 */
public class Whale extends WaterAnimal {
    private String foodType;

    /**
     * Constructs a new Whale with the specified parameters.
     *
     * @param name           The name of the whale.
     * @param gender         The gender of the whale.
     * @param weight         The weight of the whale.
     * @param speed          The speed of the whale.
     * @param medals         An array of medals the whale has won.
     * @param diveDept       The dive depth of the whale.
     * @param foodType       The type of food the whale eats.
     * @param maxEnergy      The maximum energy of the whale.
     * @param energyPerMeter The energy consumption per meter of the whale.
     * @param imageName      The name of the image representing the whale.
     * @param heat           The heat number the whale is participating in.
     * @param raceTrack      The race track associated with the whale.
     */
    public Whale(String name, Gender gender, double weight, double speed, Medal[] medals, double diveDept, String foodType, int maxEnergy, int energyPerMeter, String imageName, int heat, RaceTrack raceTrack) {
        super(name, gender, weight, speed, medals, diveDept, maxEnergy, energyPerMeter, Arrays.asList("Water"), heat, raceTrack);
        this.foodType = foodType;
        loadImages(imageName, "");
    }

    /**
     * Gets the sound made by the whale.
     *
     * @return The sound made by the whale.
     */
    public String getSound() {
        return "Splash";
    }

    /**
     * Checks if this whale is equal to another object.
     *
     * @param o The object to compare with.
     * @return true if this whale is equal to the specified object, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Whale)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(this.foodType, ((Whale) o).foodType);
    }

    /**
     * Returns a string representation of this whale.
     *
     * @return A string representation of this whale.
     */
    @Override
    public String toString() {
        return "Whale -" +
                super.toString() +
                ", foodType=" + this.foodType +
                " ";
    }
}
