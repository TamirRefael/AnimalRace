package animals;

import Olympics.Medal;
import competition.RaceTrack;

import java.util.List;

/**
 * Represents an abstract AirAnimal that can fly and has a wingspan.
 */
public abstract class AirAnimal extends Animal {
    private double wingspan;

    /**
     * Constructs a new AirAnimal with the specified parameters.
     *
     * @param name             The name of the air animal.
     * @param gender           The gender of the air animal.
     * @param weight           The weight of the air animal.
     * @param speed            The speed of the air animal.
     * @param medals           An array of medals the air animal has won.
     * @param wingspan         The wingspan of the air animal.
     * @param maxEnergy        The maximum energy of the air animal.
     * @param energyPerMeter   The energy consumption per meter of the air animal.
     * @param competitionTypes The types of competitions the air animal can participate in.
     * @param heat             The heat number the air animal is participating in.
     * @param raceTrack        The racetrack associated with the air animal.
     */
    public AirAnimal(String name, Gender gender, double weight, double speed, Medal[] medals, double wingspan, int maxEnergy, int energyPerMeter, List<String> competitionTypes, int heat, RaceTrack raceTrack) {
        super(raceTrack.getStartPosition("Air", heat), 0.0, name, gender, weight, speed, medals, maxEnergy, energyPerMeter, competitionTypes, raceTrack, heat, "Air");
        this.wingspan = wingspan;
    }

    /**
     * Gets the wingspan of the air animal.
     *
     * @return The wingspan.
     */
    public double getWingspan() {
        return wingspan;
    }

    /**
     * Sets the wingspan of the air animal.
     *
     * @param wingspan The wingspan to set.
     */
    public void setWingspan(double wingspan) {
        this.wingspan = wingspan;
    }

    /**
     * Checks if this air animal is equal to another object.
     *
     * @param o The object to compare with.
     * @return true if this air animal is equal to the specified object, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AirAnimal)) return false;
        if (!super.equals(o)) return false;
        return ((AirAnimal) o).wingspan == this.wingspan;
    }

    /**
     * Returns a string representation of this air animal.
     *
     * @return A string representation of this air animal.
     */
    @Override
    public String toString() {
        return super.toString() +
                "wingspan=" +
                this.wingspan +
                " ";
    }
}
