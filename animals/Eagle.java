package animals;

import Olympics.Medal;
import competition.RaceTrack;

import java.util.Arrays;

/**
 * Represents an Eagle, which is a type of AirAnimal.
 */
public class Eagle extends AirAnimal {
    private double altitudeOfFlight;
    private static final double MAX_ALTITUDE = 1000.0;

    /**
     * Constructs a new Eagle with the specified parameters.
     *
     * @param name             The name of the eagle.
     * @param gender           The gender of the eagle.
     * @param weight           The weight of the eagle.
     * @param speed            The speed of the eagle.
     * @param medals           An array of medals the eagle has won.
     * @param wingspan         The wingspan of the eagle.
     * @param altitudeOfFlight The altitude of flight of the eagle.
     * @param maxEnergy        The maximum energy of the eagle.
     * @param energyPerMeter   The energy consumption per meter of the eagle.
     * @param imageName        The name of the image representing the eagle.
     * @param heat             The heat number the eagle is participating in.
     * @param raceTrack        The racetrack associated with the eagle.
     */
    public Eagle(String name, Gender gender, double weight, double speed, Medal[] medals, double wingspan, double altitudeOfFlight, int maxEnergy, int energyPerMeter, String imageName, int heat, RaceTrack raceTrack) {
        super(name, gender, weight, speed, medals, wingspan, maxEnergy, energyPerMeter, Arrays.asList("Air"), heat, raceTrack);
        this.altitudeOfFlight = altitudeOfFlight;
        loadImages(imageName, "");
    }

    /**
     * Gets the sound made by the eagle.
     *
     * @return The sound made by the eagle.
     */
    public String getSound() {
        return "Clack-wack-chack";
    }

    /**
     * Sets the altitude of flight for the eagle.
     *
     * @param altitudeOfFlight The altitude to set.
     */
    public void setAltitudeOfFlight(double altitudeOfFlight) {
        if (altitudeOfFlight >= 0 && altitudeOfFlight <= MAX_ALTITUDE) {
            this.altitudeOfFlight = altitudeOfFlight;
        } else {
            System.out.println("Altitude must be between 0 and " + MAX_ALTITUDE);
        }
    }

    /**
     * Checks if this eagle is equal to another object.
     *
     * @param o The object to compare with.
     * @return true if this eagle is equal to the specified object, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Eagle)) return false;
        if (!super.equals(o)) return false;
        return ((Eagle) o).altitudeOfFlight == this.altitudeOfFlight;
    }

    /**
     * Returns a string representation of this eagle.
     *
     * @return A string representation of this eagle.
     */
    @Override
    public String toString() {
        return "Eagle -" +
                super.toString() +
                ", altitudeOfFlight=" + this.altitudeOfFlight +
                " ";
    }
}
