package animals;

import Olympics.Medal;
import mobility.Point;

import java.util.List;
import competition.RaceTrack;

/**
 * Represents a terrestrial animal that lives on land and has legs.
 */
public abstract class TerrestrialAnimals extends Animal implements ITerrestrialAnimals {
    private int NumberLegs;


    /**
     * Constructs a new TerrestrialAnimals with the specified parameters.
     *
     * @param name             The name of the terrestrial animal.
     * @param gender           The gender of the terrestrial animal.
     * @param weight           The weight of the terrestrial animal.
     * @param speed            The speed of the terrestrial animal.
     * @param medals           An array of medals the terrestrial animal has won.
     * @param noLegs           The number of legs the terrestrial animal has.
     * @param maxEnergy        The maximum energy of the terrestrial animal.
     * @param energyPerMeter   The energy consumption per meter of the terrestrial animal.
     * @param competitionTypes The types of competitions the terrestrial animal can participate in.
     * @param heat             The heat number the terrestrial animal is participating in.
     * @param raceTrack        The racetrack associated with the terrestrial animal.
     */
    public TerrestrialAnimals(String name, Gender gender, double weight, double speed, Medal[] medals, int noLegs, int maxEnergy, int energyPerMeter, List<String> competitionTypes, int heat, RaceTrack raceTrack) {
        super(raceTrack.getStartPosition("Land", heat), 0.0, name, gender, weight, speed, medals, maxEnergy, energyPerMeter, competitionTypes, raceTrack, heat, "Land");
        this.NumberLegs = noLegs;
    }

    /**
     * Gets the number of legs of the terrestrial animal.
     *
     * @return The number of legs.
     */
    public int getNumberLegs() {
        return this.NumberLegs;
    }

    /**
     * Sets the number of legs of the terrestrial animal.
     *
     * @param numberLegs The number of legs to set.
     */
    public void setNumberLegs(int numberLegs) {
        this.NumberLegs = numberLegs;
    }

    /**
     * Checks if this terrestrial animal is equal to another object.
     *
     * @param o The object to compare with.
     * @return true if this terrestrial animal is equal to the specified object, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TerrestrialAnimals)) return false;
        if (!super.equals(o)) return false;
        return this.NumberLegs == ((TerrestrialAnimals) o).NumberLegs;
    }

    /**
     * Returns a string representation of this terrestrial animal.
     *
     * @return A string representation of this terrestrial animal.
     */
    @Override
    public String toString() {
        return super.toString() +
                ", number of Legs=" +
                this.NumberLegs +
                " ";
    }
}
