package competition;

import mobility.Point;

/**
 * Represents a racetrack for different types of competitions (Land, Water, Air).
 */
public class RaceTrack {
    private Point[] landStartPositions;
    private Point[] waterStartPositions;
    private Point[] airStartPositions;
    private Point landFinishLine;
    private Point landDiagonalFinishLine;
    private Point landHorizontalFinishLine;
    private Point[] waterFinishLines;
    private Point[] airFinishLines;

    /**
     * Constructs a new RaceTrack with predefined start and finish positions for each competition type.
     */
    public RaceTrack() {
        // Initialize start positions for land competition (only one heat)
        landStartPositions = new Point[1];
        landStartPositions[0] = new Point(25, 9);  // Heat 1

        // Initialize start positions for water competition (4 heats)
        waterStartPositions = new Point[4];
        waterStartPositions[0] = new Point(69, 80);  // Heat 1
        for (int i = 1; i < 4; i++) {
            waterStartPositions[i] = new Point(69, 83 + (i * 133));  // Heats 2-4
        }

        // Initialize start positions for air competition (5 heats)
        airStartPositions = new Point[5];
        airStartPositions[0] = new Point(64, 9);  // Heat 1
        for (int i = 1; i < 5; i++) {
            airStartPositions[i] = new Point(64, 9 + (i * 140));  // Heats 2-5
        }

        // Initialize finish line position for land competition
        landFinishLine = new Point(853, 9);
        landDiagonalFinishLine = new Point(853,566);
        landHorizontalFinishLine = new Point(25, 566);

        // Initialize finish line positions for water competition (4 heats)
        waterFinishLines = new Point[4];
        waterFinishLines[0] = new Point(770, 80);  // Heat 1
        for (int i = 1; i < 4; i++) {
            waterFinishLines[i] = new Point(770, 83 + (i * 133));  // Heats 2-4
        }

        // Initialize finish line positions for air competition (5 heats)
        airFinishLines = new Point[5];
        airFinishLines[0] = new Point(850, 9);  // Heat 1
        for (int i = 1; i < 5; i++) {
            airFinishLines[i] = new Point(850, 9 + (i * 140));  // Heats 2-5
        }
    }

    /**
     * Gets the start position for a given competition type and heat.
     *
     * @param competitionType The type of competition (Land, Water, Air).
     * @param heat The heat number.
     * @return The start position as a Point object.
     * @throws IllegalArgumentException If the competition type is invalid.
     */
    public Point getStartPosition(String competitionType, int heat) {
        switch (competitionType) {
            case "Land":
                return landStartPositions[0];
            case "Water":
                return waterStartPositions[heat - 1];
            case "Air":
                return airStartPositions[heat - 1];
            default:
                throw new IllegalArgumentException("Invalid competition type");
        }
    }

    public Point getLandDiagonalFinishLine() {
        return landDiagonalFinishLine;
    }

    public Point getLandHorizontalFinishLine() {
        return landHorizontalFinishLine;
    }


    /**
     * Gets the finish line position for a given competition type and heat.
     *
     * @param competitionType The type of competition (Land, Water, Air).
     * @param heat The heat number.
     * @return The finish line position as a Point object.
     * @throws IllegalArgumentException If the competition type is invalid.
     */
    public Point getFinishLine(String competitionType, int heat) {
        switch (competitionType) {
            case "Land":
                return landFinishLine;
            case "Water":
                return waterFinishLines[heat - 1];
            case "Air":
                return airFinishLines[heat - 1];
            default:
                throw new IllegalArgumentException("Invalid competition type");
        }
    }

    public int getTotalLandDistance() {
        int horizontal1 = Math.abs(landFinishLine.getX() - landStartPositions[0].getX());
        int vertical1 = Math.abs(landDiagonalFinishLine.getY() - landFinishLine.getY());
        int horizontal2 = Math.abs(landHorizontalFinishLine.getX() - landDiagonalFinishLine.getX());
        int vertical2 = Math.abs(landHorizontalFinishLine.getY() - landStartPositions[0].getY());

        return horizontal1 + vertical1 + horizontal2 + vertical2;
    }

}
