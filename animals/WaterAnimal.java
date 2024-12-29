package animals;

import Olympics.Medal;
import competition.RaceTrack;

import java.util.List;

/**
 * Represents a water animal that can dive to a certain depth.
 */
public abstract class WaterAnimal extends Animal implements IWaterAnimal {
    static final double MAX_DIVE = -800.0;
    private double diveDept;

    /**
     * Constructs a new WaterAnimal with the specified parameters.
     *
     * @param name             The name of the water animal.
     * @param gender           The gender of the water animal.
     * @param weight           The weight of the water animal.
     * @param speed            The speed of the water animal.
     * @param medals           An array of medals the water animal has won.
     * @param diveDept         The dive depth of the water animal.
     * @param maxEnergy        The maximum energy of the water animal.
     * @param energyPerMeter   The energy consumption per meter of the water animal.
     * @param competitionTypes The types of competitions the water animal can participate in.
     * @param heat             The heat number the water animal is participating in.
     * @param raceTrack        The race track associated with the water animal.
     */
    public WaterAnimal(String name, Gender gender, double weight, double speed, Medal[] medals, double diveDept, int maxEnergy, int energyPerMeter, List<String> competitionTypes, int heat, RaceTrack raceTrack) {
        super(raceTrack.getStartPosition("Water", heat), 0.0, name, gender, weight, speed, medals, maxEnergy, energyPerMeter, competitionTypes, raceTrack, heat, "Water");
        this.diveDept = diveDept;
    }

    /**
     * Gets the current dive depth of the water animal.
     *
     * @return The dive depth.
     */
    public double getDiveDept() {
        return this.diveDept;
    }

    /**
     * Dives to a specified depth, respecting the maximum dive depth.
     *
     * @param depth The depth to dive to.
     */
    public void dive(double depth) {
        if (diveDept + depth >= MAX_DIVE) {
            diveDept += depth;
        } else {
            System.out.println("Cannot dive deeper than " + MAX_DIVE);
        }
    }

    /**
     * Gets the maximum dive depth allowed.
     *
     * @return The maximum dive depth.
     */
    public static double getMaxDive() {
        return MAX_DIVE;
    }

    /**
     * Checks if this water animal is equal to another object.
     *
     * @param o The object to compare with.
     * @return true if this water animal is equal to the specified object, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WaterAnimal)) return false;
        if (!super.equals(o)) return false;
        return ((WaterAnimal) o).diveDept == this.diveDept;
    }

    /**
     * Returns a string representation of this water animal.
     *
     * @return A string representation of this water animal.
     */
    @Override
    public String toString() {
        return super.toString() +
                "diveDept=" +
                this.diveDept +
                " ";
    }
}
