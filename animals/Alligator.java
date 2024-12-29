package animals;

import Olympics.Medal;
import competition.RaceTrack;

import java.util.Arrays;
import java.util.Objects;

/**
 * Represents an Alligator, which is an animal that can live both on land and in water, and can speed up.
 */
public class Alligator extends Animal implements ITerrestrialAnimals, IWaterAnimal, IReptile {
    private String areaOfLiving;
    private ITerrestrialAnimals terrestrialAnimals;
    private IWaterAnimal waterAnimal;

    /**
     * Constructs a new Alligator with the specified parameters.
     *
     * @param name             The name of the alligator.
     * @param gender           The gender of the alligator.
     * @param weight           The weight of the alligator.
     * @param speed            The speed of the alligator.
     * @param medals           An array of medals the alligator has won.
     * @param diveDepth        The dive depth of the alligator.
     * @param areaOfLiving     The area where the alligator lives.
     * @param maxEnergy        The maximum energy of the alligator.
     * @param energyPerMeter   The energy consumption per meter of the alligator.
     * @param imageName        The name of the image representing the alligator.
     * @param heat             The heat number the alligator is participating in.
     * @param competitionType  The type of competition the alligator is participating in.
     * @param raceTrack        The racetrack associated with the alligator.
     */
    public Alligator(String name, Gender gender, double weight, double speed, Medal[] medals, double diveDepth, String areaOfLiving, int maxEnergy, int energyPerMeter, String imageName, int heat, String competitionType, RaceTrack raceTrack) {
        super(raceTrack.getStartPosition(competitionType, heat), 0.0, name, gender, weight, speed, medals, maxEnergy, energyPerMeter, Arrays.asList("Land", "Water"), raceTrack, heat, competitionType);
        this.areaOfLiving = areaOfLiving;
        this.terrestrialAnimals = new TerrestrialAnimals(name, gender, weight, speed, medals, 4, maxEnergy, energyPerMeter, Arrays.asList("Land"), heat, raceTrack) {
            public String getSound() {
                return "Roar";
            }
        };
        this.waterAnimal = new WaterAnimal(name, gender, weight, speed, medals, diveDepth, maxEnergy, energyPerMeter, Arrays.asList("Water"), heat, raceTrack) {
            public String getSound() {
                return "Roar";
            }
        };
        loadImages(imageName, competitionType);
    }

    /**
     * Gets the sound made by the alligator.
     *
     * @return The sound made by the alligator.
     */
    public String getSound() {
        return "Roar";
    }

    /**
     * Checks if this alligator is equal to another object.
     *
     * @param o The object to compare with.
     * @return true if this alligator is equal to the specified object, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Alligator)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(this.areaOfLiving, ((Alligator) o).areaOfLiving);
    }

    /**
     * Returns a string representation of this alligator.
     *
     * @return A string representation of this alligator.
     */
    @Override
    public String toString() {
        return "Alligator -" +
                super.toString() +
                ", areaOfLiving=" + this.areaOfLiving +
                " ";
    }

    /**
     * Increases the speed of the alligator by a specified increment, up to a maximum of 5.
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

    /**
     * Gets the number of legs the alligator has.
     *
     * @return The number of legs.
     */
    @Override
    public int getNumberLegs() {
        return terrestrialAnimals.getNumberLegs();
    }

    /**
     * Sets the number of legs the alligator has.
     *
     * @param numberLegs The number of legs to set.
     */
    @Override
    public void setNumberLegs(int numberLegs) {
        terrestrialAnimals.setNumberLegs(numberLegs);
    }

    /**
     * Dives to a specified depth.
     *
     * @param depth The depth to dive to.
     */
    @Override
    public void dive(double depth) {
        waterAnimal.dive(depth);
    }

    /**
     * Gets the current dive depth of the alligator.
     *
     * @return The dive depth.
     */
    @Override
    public double getDiveDept() {
        return waterAnimal.getDiveDept();
    }
}
