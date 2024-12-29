package animals;

import Olympics.Medal;
import competition.RaceTrack;

import java.util.Arrays;
import java.util.Objects;

/**
 * Represents a Dog, which is a type of TerrestrialAnimal.
 */
public class Dog extends TerrestrialAnimals {
    private String breed;

    /**
     * Constructs a new Dog with the specified parameters.
     *
     * @param name             The name of the dog.
     * @param gender           The gender of the dog.
     * @param weight           The weight of the dog.
     * @param speed            The speed of the dog.
     * @param medals           An array of medals the dog has won.
     * @param noLegs           The number of legs the dog has.
     * @param breed            The breed of the dog.
     * @param maxEnergy        The maximum energy of the dog.
     * @param energyPerMeter   The energy consumption per meter of the dog.
     * @param imageName        The name of the image representing the dog.
     * @param heat             The heat number the dog is participating in.
     * @param raceTrack        The racetrack associated with the dog.
     */
    public Dog(String name, Gender gender, double weight, double speed, Medal[] medals, int noLegs, String breed, int maxEnergy, int energyPerMeter, String imageName, int heat, RaceTrack raceTrack) {
        super(name, gender, weight, speed, medals, noLegs, maxEnergy, energyPerMeter, Arrays.asList("Land"), heat, raceTrack);
        this.breed = breed;
        loadImages(imageName, "Terrestrial");
    }

    /**
     * Gets the sound made by the dog.
     *
     * @return The sound made by the dog.
     */
    public String getSound() {
        return "Woof Woof";
    }

    /**
     * Checks if this dog is equal to another object.
     *
     * @param o The object to compare with.
     * @return true if this dog is equal to the specified object, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dog)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(this.breed, ((Dog) o).breed);
    }

    /**
     * Returns a string representation of this dog.
     *
     * @return A string representation of this dog.
     */
    @Override
    public String toString() {
        return "Dog -" +
                super.toString() +
                ", breed=" + this.breed +
                " ";
    }
}
