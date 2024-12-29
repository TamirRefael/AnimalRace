package mobility;

/**
 * Represents a point in a 2D coordinate system.
 */
public class Point {
    protected int x;
    private int y;

    /**
     * Constructs a new Point with the specified coordinates.
     *
     * @param x The x-coordinate of the point. Must be non-negative.
     * @param y The y-coordinate of the point. Must be non-negative.
     * @throws IllegalArgumentException if any of the coordinates are negative.
     */
    public Point(int x, int y) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Coordinates must be non-negative");
        }
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the x-coordinate of this point.
     *
     * @return The x-coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y-coordinate of this point.
     *
     * @return The y-coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * Calculates the distance between this point and another point.
     *
     * @param other The other point to calculate the distance to.
     * @return The distance between the two points.
     */
    public double distance(Point other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Compares this point to the specified object. The result is true if and only if the argument is not null
     * and is a Point object that has the same coordinates as this point.
     *
     * @param o The object to compare this Point against.
     * @return true if the given object represents a Point equivalent to this point, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    /**
     * Returns a string representation of this Point.
     *
     * @return A string representation of this Point.
     */
    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
