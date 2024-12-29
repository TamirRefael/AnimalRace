package animals;

import Olympics.Medal;
import competition.RaceTrack;

import java.util.Arrays;

/**
 * Represents a Dolphin, which is a type of WaterAnimal.
 */
public class Dolphin extends WaterAnimal {
    /**
     * Enumeration for the type of water the dolphin lives in.
     */
    public enum WaterType {Sea, Sweet}

    private WaterType waterType;

    /**
     * Constructs a new Dolphin with the specified parameters.
     *
     * @param name             The name of the dolphin.
     * @param gender           The gender of the dolphin.
     * @param weight           The weight of the dolphin.
     * @param speed            The speed of the dolphin.
     * @param medals           An array of medals the dolphin has won.
     * @param diveDept         The dive depth of the dolphin.
     * @param waterType        The type of water the dolphin lives in.
     * @param maxEnergy        The maximum energy of the dolphin.
     * @param energyPerMeter   The energy consumption per meter of the dolphin.
     * @param imageName        The name of the image representing the dolphin.
     * @param heat             The heat number the dolphin is participating in.
     * @param raceTrack        The racetrack associated with the dolphin.
     */
    public Dolphin(String name, Gender gender, double weight, double speed, Medal[] medals, double diveDept, WaterType waterType, int maxEnergy, int energyPerMeter, String imageName, int heat, RaceTrack raceTrack) {
        super(name, gender, weight, speed, medals, diveDept, maxEnergy, energyPerMeter, Arrays.asList("Water"), heat, raceTrack);
        this.waterType = waterType;
        loadImages(imageName, "");
    }

    /**
     * Gets the sound made by the dolphin.
     *
     * @return The sound made by the dolphin.
     */
    public String getSound() {
        return "Click-click";
    }

    /**
     * Checks if this dolphin is equal to another object.
     *
     * @param o The object to compare with.
     * @return true if this dolphin is equal to the specified object, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dolphin)) return false;
        if (!super.equals(o)) return false;
        return this.waterType == ((Dolphin) o).waterType;
    }

    /**
     * Returns a string representation of this dolphin.
     *
     * @return A string representation of this dolphin.
     */
    @Override
    public String toString() {
        return "Dolphin -" +
                super.toString() +
                ", waterType=" + this.waterType +
                " ";
    }
}
