package mobility;

/**
 * An interface for objects that have a location and can be moved to a new location.
 */
public interface ILocatable {

    /**
     * Gets the current location of the object.
     *
     * @return The current location as a Point object.
     */
    Point getLocation();

    /**
     * Sets the location of the object to the specified point.
     *
     * @param point The new location to set.
     * @return true if the location was successfully set, false if the point is null.
     */
    boolean setLocation(Point point);

    /**
     * Gets the x-coordinate of the finish line.
     *
     * @return The x-coordinate of the finish line.
     */
    int getFinishLineX();
}
