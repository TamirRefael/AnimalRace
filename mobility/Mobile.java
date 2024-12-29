package mobility;

import java.util.Objects;

/**
 * Represents a mobile entity that can move from one point to another and keep track of the distance traveled.
 */
public abstract class Mobile implements ILocatable {
    private Point location;
    private double totalDistance;

    /**
     * Constructs a new Mobile instance with the specified location and total distance traveled.
     *
     * @param location      The initial location of the mobile entity.
     * @param totalDistance The initial total distance traveled. Must be non-negative.
     * @throws IllegalArgumentException if location is null or totalDistance is negative.
     */
    public Mobile(Point location, double totalDistance) {
        if (location == null || totalDistance < 0.0) {
            throw new IllegalArgumentException("Invalid arguments for constructing Mobile");
        }
        this.location = location;
        this.totalDistance = totalDistance;
    }

    /**
     * Adds the specified distance to the total distance traveled by this mobile entity.
     *
     * @param distance The distance to add.
     */
    public abstract void addTotalDistance(double distance);

    /**
     * Calculates the distance between this mobile entity's current location and the specified point.
     *
     * @param point The point to calculate the distance to.
     * @return The calculated distance.
     */
    public abstract double calcDistance(Point point);

    /**
     * Moves the mobile entity to a new location. The specifics of the movement are defined by subclasses.
     */
    public abstract void move();

    /**
     * Gets the current location of the mobile entity.
     *
     * @return The current location.
     */
    @Override
    public Point getLocation() {
        return location;
    }

    /**
     * Sets the location of the mobile entity.
     *
     * @param point The new location to set.
     * @return true if the location was successfully set, false if the point is null.
     */
    @Override
    public boolean setLocation(Point point) {
        if (point == null) {
            return false;
        }
        this.location = point;
        return true;
    }

    /**
     * Gets the total distance traveled by the mobile entity.
     *
     * @return The total distance traveled.
     */
    public double getTotalDistance() {
        return totalDistance;
    }

    /**
     * Sets the total distance traveled by the mobile entity. If the new total distance exceeds the finish line,
     * it is capped at the finish line.
     *
     * @param totalDistance The distance to set.
     */
    public void setTotalDistance(double totalDistance) {
        if (this.totalDistance + totalDistance > getFinishLineX()) {
            this.totalDistance = getFinishLineX();
        } else {
            this.totalDistance += totalDistance;
        }
    }


    public abstract int getFinishLineX();

    /**
     * Compares this mobile entity to the specified object. The result is true if and only if the argument is not null
     * and is a Mobile object that has the same location and total distance as this object.
     *
     * @param o The object to compare this Mobile against.
     * @return true if the given object represents a Mobile equivalent to this mobile, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mobile)) return false;
        Mobile mobile = (Mobile) o;
        return Double.compare(mobile.totalDistance, totalDistance) == 0 &&
                Objects.equals(location, mobile.location);
    }

    /**
     * Returns a string representation of this Mobile.
     *
     * @return A string representation of this Mobile.
     */
    @Override
    public String toString() {
        return "location:" + this.location +
                ", totalDistance=" + this.totalDistance +
                " ";
    }
}
