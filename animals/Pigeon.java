package animals;

import Olympics.Medal;
import competition.RaceTrack;

import java.util.Arrays;
import java.util.Objects;

/**
 * Represents a Pigeon, which is a type of AirAnimal.
 */
public class Pigeon extends AirAnimal {
    private String family;

    /**
     * Constructs a new Pigeon with the specified parameters.
     *
     * @param name             The name of the pigeon.
     * @param gender           The gender of the pigeon.
     * @param weight           The weight of the pigeon.
     * @param speed            The speed of the pigeon.
     * @param medals           An array of medals the pigeon has won.
     * @param wingspan         The wingspan of the pigeon.
     * @param family           The family of the pigeon.
     * @param maxEnergy        The maximum energy of the pigeon.
     * @param energyPerMeter   The energy consumption per meter of the pigeon.
     * @param imageName        The name of the image representing the pigeon.
     * @param heat             The heat number the pigeon is participating in.
     * @param raceTrack        The race track associated with the pigeon.
     */
    public Pigeon(String name, Gender gender, double weight, double speed, Medal[] medals, double wingspan, String family, int maxEnergy, int energyPerMeter, String imageName, int heat, RaceTrack raceTrack) {
        super(name, gender, weight, speed, medals, wingspan, maxEnergy, energyPerMeter, Arrays.asList("Air"), heat, raceTrack);
        this.family = family;
        loadImages(imageName, "");
    }

    /**
     * Gets the sound made by the pigeon.
     *
     * @return The sound made by the pigeon.
     */
    public String getSound() {
        return "Arr-rar-rar-rar-raah";
    }

    /**
     * Checks if this pigeon is equal to another object.
     *
     * @param o The object to compare with.
     * @return true if this pigeon is equal to the specified object, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pigeon)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(((Pigeon) o).family, this.family);
    }

    /**
     * Returns a string representation of this pigeon.
     *
     * @return A string representation of this pigeon.
     */
    @Override
    public String toString() {
        return "Pigeon -" +
                super.toString() +
                ", family=" + this.family +
                " ";
    }
}
