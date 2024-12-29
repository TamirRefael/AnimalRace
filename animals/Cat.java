package animals;

import Olympics.Medal;
import competition.RaceTrack;

import java.util.Arrays;

/**
 * Represents a Cat, which is a type of TerrestrialAnimal.
 */
public class Cat extends TerrestrialAnimals {
    private boolean castrated;


    /**
     * Constructs a new Cat with the specified parameters.
     *
     * @param name             The name of the cat.
     * @param gender           The gender of the cat.
     * @param weight           The weight of the cat.
     * @param speed            The speed of the cat.
     * @param medals           An array of medals the cat has won.
     * @param noLegs           The number of legs the cat has.
     * @param castrated        Whether the cat is castrated.
     * @param maxEnergy        The maximum energy of the cat.
     * @param energyPerMeter   The energy consumption per meter of the cat.
     * @param imageName        The name of the image representing the cat.
     * @param heat             The heat number the cat is participating in.
     * @param raceTrack        The racetrack associated with the cat.
     */
    public Cat(String name, Gender gender, double weight, double speed, Medal[] medals, int noLegs, boolean castrated, int maxEnergy, int energyPerMeter, String imageName, int heat, RaceTrack raceTrack) {
        super(name, gender, weight, speed, medals, noLegs, maxEnergy, energyPerMeter, Arrays.asList("Land"), heat, raceTrack);
        this.castrated = castrated;
        loadImages(imageName, "Terrestrial");
    }

    /**
     * Gets the sound made by the cat.
     *
     * @return The sound made by the cat.
     */
    public String getSound() {
        return "Meow";
    }

    /**
     * Checks if this cat is equal to another object.
     *
     * @param o The object to compare with.
     * @return true if this cat is equal to the specified object, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cat)) return false;
        if (!super.equals(o)) return false;
        return this.castrated == ((Cat) o).castrated;
    }

    /**
     * Returns a string representation of this cat.
     *
     * @return A string representation of this cat.
     */
    @Override
    public String toString() {
        return "Cat -" +
                super.toString() +
                ", castrated= " + this.castrated +
                " ";
    }
}
