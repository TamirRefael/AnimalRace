package competition;

import animals.Animal;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents a competition that involves animals.
 */
public class Competition {
    private String type;
    private List<Animal> animals;
    private int competitionId;
    private static int idCounter = 0;
    private int numHeats;

    /**
     * Constructs a new Competition with the specified type and number of heats.
     *
     * @param type The type of competition (e.g., Land, Water, Air).
     * @param numHeats The number of heats in the competition.
     */
    public Competition(String type, int numHeats) {
        this.type = type;
        this.animals = new ArrayList<>();
        this.competitionId = ++idCounter;
        this.numHeats = numHeats;
    }

    /**
     * Gets the type of the competition.
     *
     * @return The type of the competition.
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the unique ID of the competition.
     *
     * @return The competition ID.
     */
    public int getCompetitionId() {
        return competitionId;
    }

    /**
     * Gets the number of heats in the competition.
     *
     * @return The number of heats.
     */
    public int getNumHeats() {
        return numHeats;
    }

    /**
     * Adds an animal to the competition if it is compatible.
     *
     * @param animal The animal to add.
     * @throws IllegalArgumentException If the animal is not compatible with the competition.
     */
    public void addAnimal(Animal animal) {
        if (isAnimalCompatible(animal)) {
            animals.add(animal);
        } else {
            throw new IllegalArgumentException("Animal not compatible with this competition.");
        }
    }

    /**
     * Checks if the specified animal is compatible with the competition.
     *
     * @param animal The animal to check.
     * @return true if the animal is compatible, false otherwise.
     */
    public boolean isAnimalCompatible(Animal animal) {
        return animal.getCompetitionTypes().contains(type);
    }

    /**
     * Gets the list of animals participating in the competition.
     *
     * @return A list of animals.
     */
    public List<Animal> getAnimals() {
        return animals;
    }

    /**
     * Returns a string representation of the competition.
     *
     * @return A string representation of the competition.
     */
    @Override
    public String toString() {
        return "Competition: " + type + " (ID: " + competitionId + ")";
    }
}
