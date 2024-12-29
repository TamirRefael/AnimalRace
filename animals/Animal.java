package animals;

import Graphics.IClonable;
import Graphics.IDrawable;
import Graphics.IMoveable;
import competition.RaceTrack;
import mobility.ILocatable;
import mobility.Mobile;
import mobility.Point;
import Olympics.Medal;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 * Represents an abstract Animal that can move, draw, and clone itself.
 */
public abstract class Animal extends Mobile implements ILocatable, IMoveable, IDrawable, IClonable {
    /**
     * Enumeration for the gender of the animal.
     */
    public enum Gender {MALE, FEMALE}

    /**
     * Enumeration for the orientation of the animal.
     */
    public enum Orientation {NORTH, SOUTH, EAST, WEST}

    private String name;
    private Gender gender;
    private double weight;
    private double speed;
    private Medal[] medals;
    private Point position;
    private Orientation orientation;
    private int size = 50;
    private int id;
    private int maxEnergy;
    private int energyPerMeter;
    private int energyAmount;
    private int energyConsumption;
    private BufferedImage img1, img2, img3, img4;
    private List<String> competitionTypes;
    private RaceTrack raceTrack;
    private int heat;
    private String competitionspecificType;

    /**
     * Constructs a new Animal with the specified parameters.
     *
     * @param location         The location of the animal.
     * @param totalDistance    The total distance the animal has moved.
     * @param name             The name of the animal.
     * @param gender           The gender of the animal.
     * @param weight           The weight of the animal.
     * @param speed            The speed of the animal.
     * @param medals           An array of medals the animal has won.
     * @param maxEnergy        The maximum energy of the animal.
     * @param energyPerMeter   The energy consumption per meter of the animal.
     * @param competitionTypes The types of competitions the animal can participate in.
     * @param raceTrack        The racetrack associated with the animal.
     */
    public Animal(Point location, double totalDistance, String name, Gender gender, double weight, double speed, Medal[] medals, int maxEnergy, int energyPerMeter, List<String> competitionTypes, RaceTrack raceTrack, int heat, String competitionspecificType) {
        super(location, totalDistance);
        this.name = name;
        this.gender = gender;
        this.weight = weight;
        this.speed = speed;
        this.medals = medals;
        this.position = location;
        this.maxEnergy = maxEnergy;
        this.energyPerMeter = energyPerMeter;
        this.orientation = Orientation.EAST;
        this.energyAmount = maxEnergy;
        this.energyConsumption = 0;
        this.competitionTypes = competitionTypes;
        this.raceTrack = raceTrack;
        this.heat = heat;
        this.competitionspecificType = competitionspecificType;
    }

    /**
     * Gets the x-coordinate of the finish line.
     *
     * @return The x-coordinate of the finish line.
     */
    public int getFinishLineX() {
        if (competitionspecificType.equals("Land")) {
            return raceTrack.getTotalLandDistance(); // עבור חיה יבשתית, נחזיר את המרחק הכולל
        } else {
            return raceTrack.getFinishLine(competitionspecificType, heat).getX(); // עבור חיות אחרות, נשמור על ההתנהגות המקורית
        }
    }


    public String getcompetitionspecificType()
    {
        return competitionspecificType;
    }

    public int getheat()
    {
        return heat;
    }

    /**
     * Gets the list of competition types the animal can participate in.
     *
     * @return The list of competition types.
     */
    public List<String> getCompetitionTypes() {
        return competitionTypes;
    }

    /**
     * Gets the sound made by the animal.
     *
     * @return The sound made by the animal.
     */
    public abstract String getSound();

    /**
     * Makes the animal produce its sound.
     */
    public final void makeSound() {
        System.out.println("Animal " + name + " said " + getSound());
    }

    /**
     * Adds a specified distance to the total distance the animal has moved.
     *
     * @param distance The distance to add.
     */
    public void addTotalDistance(double distance) {
        if (distance > 0) {
            setTotalDistance(distance);
        }
    }

    /**
     * Calculates the distance between the animal's current location and a specified point.
     *
     * @param point The point to calculate the distance to.
     * @return The distance to the specified point.
     */
    public double calcDistance(Point point) {
        double distance = 0.0;

        switch (orientation) {
            case EAST:
                // תנועה מזרחה - יש להוסיף את ההבדל בציר ה-X
                distance = point.getX() - position.getX();
                break;
            case WEST:
                // תנועה מערבה - יש להפחית את ההבדל בציר ה-X
                distance = position.getX() - point.getX();
                break;
            case NORTH:
                // תנועה צפונה - יש להפחית את ההבדל בציר ה-Y
                distance = position.getY() - point.getY();
                break;
            case SOUTH:
                // תנועה דרומה - יש להוסיף את ההבדל בציר ה-Y
                distance = point.getY() - position.getY();
                break;
        }

        return Math.abs(distance); // החזרה של המרחק בערך מוחלט
    }


    /**
     * Loads images for the animal based on its name and type.
     *
     * @param nm         The name of the image file.
     * @param animalType The type of the animal (e.g., "Terrestrial").
     */
    public void loadImages(String nm, String animalType) {
        try {
            img1 = null;
            img2 = null;
            img3 = null;
            img4 = null;

            try {
                img1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Graphics/" + nm + ".png")));
            } catch (IOException e) {
                System.out.println("Cannot load image: " + nm);
                e.printStackTrace();
                img1 = null;
            }

            if (img1 != null) {
                if ("Terrestrial".equals(animalType)) {
                    img2 = createFlippedImage(img1, false, false, 90);  // Rotate 90 degrees with clockwise for South
                    img3 = createFlippedImage(img1, true, false, 0);    // Flip horizontally for West
                    img4 = createFlippedImage(img1, false, false, -90); // Rotate 90 degrees counterclockwise for North
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Creates a flipped image based on the original image and specified parameters.
     *
     * @param img             The original image.
     * @param horizontal      Whether to flip the image horizontally.
     * @param vertical        Whether to flip the image vertically.
     * @param rotationDegrees The degrees to rotate the image.
     * @return The flipped and/or rotated image.
     */
    private BufferedImage createFlippedImage(BufferedImage img, boolean horizontal, boolean vertical, int rotationDegrees) {
        int w = img.getWidth();
        int h = img.getHeight();

        // Create a new BufferedImage with the correct type and size
        BufferedImage rotatedImage = new BufferedImage(w, h, img.getType());
        Graphics2D g2d = rotatedImage.createGraphics();

        // Apply rotation if needed
        if (rotationDegrees != 0) {
            double theta = Math.toRadians(rotationDegrees);
            g2d.rotate(theta, w / 2, h / 2);
        }

        // Draw the original image onto the rotatedImage
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();

        // Create a new BufferedImage to apply flipping
        BufferedImage flippedImage = new BufferedImage(w, h, img.getType());
        Graphics2D g = flippedImage.createGraphics();

        // Apply flipping based on the horizontal and vertical flags
        if (horizontal && vertical) {
            g.drawImage(rotatedImage, 0, 0, w, h, w, h, 0, 0, null);
        } else if (horizontal) {
            g.drawImage(rotatedImage, 0, 0, w, h, w, 0, 0, h, null);
        } else if (vertical) {
            g.drawImage(rotatedImage, 0, 0, w, h, 0, h, w, 0, null);
        } else {
            g.drawImage(rotatedImage, 0, 0, null);
        }

        g.dispose();
        return flippedImage;
    }


    /**
     * Draws the animal on the screen.
     *
     * @param g The graphics context to use for drawing.
     */
    public void drawObject(Graphics g) {
        switch (orientation) {
            case EAST:
                g.drawImage(img1, position.getX(), position.getY() - size / 10, size, size, null);
                break;
            case SOUTH:
                g.drawImage(img2, position.getX(), position.getY() - size / 10, size, size, null);
                break;
            case WEST:
                g.drawImage(img3, position.getX(), position.getY() - size / 10, size, size, null);
                break;
            case NORTH:
                g.drawImage(img4, position.getX() - size / 2, position.getY() - size / 10, size, size, null);
                break;
        }
    }

    /**
     * Gets the racetrack associated with the animal.
     *
     * @return The racetrack.
     */
    public RaceTrack getRaceTrack() {
        return raceTrack;
    }

    /**
     * Moves the animal, updating its position and energy based on its speed and energy consumption.
     */
    public void move() {
        if (energyAmount > 0) {
            if (competitionspecificType.equals("Land")) {
                double newX = position.getX();
                double newY = position.getY();

                // תנועה אופקית ראשונה
                if (position.getX() < raceTrack.getFinishLine("Land", heat).getX() && position.getY() == raceTrack.getStartPosition("Land", heat).getY()) {
                    newX = position.getX() + speed;
                    if (newX > raceTrack.getFinishLine("Land", heat).getX()) {
                        newX = raceTrack.getFinishLine("Land", heat).getX();
                    }
                    Point newLocation = new Point((int) newX, position.getY());
                    addTotalDistance(calcDistance(newLocation));
                    position = newLocation;

                    orientation = Orientation.EAST;
                }
                // תנועה אנכית
                else if (position.getX() == raceTrack.getLandDiagonalFinishLine().getX() && position.getY() < raceTrack.getLandDiagonalFinishLine().getY()) {
                    newY = position.getY() + speed;
                    if (newY > raceTrack.getLandDiagonalFinishLine().getY()) {
                        newY = raceTrack.getLandDiagonalFinishLine().getY();
                    }
                    Point newLocation = new Point(position.getX(), (int) newY);
                    addTotalDistance(calcDistance(newLocation));
                    position = newLocation;

                    orientation = Orientation.SOUTH;
                }
                // תנועה אופקית שנייה
                else if (position.getY() == raceTrack.getLandHorizontalFinishLine().getY() && position.getX() > raceTrack.getLandHorizontalFinishLine().getX()) {
                    newX = position.getX() - speed;
                    if (newX < raceTrack.getLandHorizontalFinishLine().getX()) {
                        newX = raceTrack.getLandHorizontalFinishLine().getX();
                    }
                    Point newLocation = new Point((int) newX, position.getY());
                    addTotalDistance(calcDistance(newLocation));
                    position = newLocation;

                    orientation = Orientation.WEST;
                }
                // תנועה אנכית לסיום
                else if (position.getX() == raceTrack.getStartPosition("Land", heat).getX() && position.getY() > raceTrack.getStartPosition("Land", heat).getY()) {
                    newY = position.getY() - speed;
                    if (newY < raceTrack.getStartPosition("Land", heat).getY()) {
                        newY = raceTrack.getStartPosition("Land", heat).getY();
                    }
                    Point newLocation = new Point(position.getX(), (int) newY);
                    addTotalDistance(calcDistance(newLocation));
                    position = newLocation;

                    orientation = Orientation.NORTH;
                }

                if (energyAmount - energyPerMeter * speed < 0) {
                    energyAmount = 0;
                } else {
                    energyAmount -= energyPerMeter * speed;
                }
            } else {
                // התנהגות קיימת עבור חיות שאינן יבשתיות
                double newX = position.getX() + speed;
                if(newX > getFinishLineX()) {
                    newX = getFinishLineX();
                }
                Point newLocation = new Point((int) newX, position.getY());

                addTotalDistance(calcDistance(newLocation));
                position = newLocation;

                if (energyAmount - energyPerMeter * speed < 0) {
                    energyAmount = 0;
                } else {
                    energyAmount -= energyPerMeter * speed;
                }
            }
        }
    }



    /**
     * Gets the current location of the animal.
     *
     * @return The current location as a Point object.
     */
    public Point getLocation() {
        return position;
    }

    /**
     * Feeds the animal by increasing its energy amount.
     *
     * @param energy The amount of energy to add.
     */
    public void feed(int energy) {
        this.energyAmount += energy;
    }

    /**
     * Gets the name of the animal.
     *
     * @return The name of the animal.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the name of the animal (for compatibility with IMoveable interface).
     *
     * @return The name of the animal.
     */
    public String getAnimaleName() {
        return this.name;
    }

    /**
     * Gets the total energy consumption of the animal.
     *
     * @return The total energy consumption.
     */
    public int getEnergyConsumption() {
        energyConsumption = (int) (getTotalDistance() * energyPerMeter);
        return energyConsumption;
    }

    /**
     * Gets the current amount of energy the animal has.
     *
     * @return The current energy amount.
     */
    public int getEnergyAmount() {
        return energyAmount;
    }

    /**
     * Gets the total distance the animal has moved.
     *
     * @return The total distance.
     */
    public double getTotaldistance() {
        return getTotalDistance();
    }

    /**
     * Gets the speed of the animal.
     *
     * @return The speed.
     */
    public int getSpeed() {
        return (int) speed;
    }

    /**
     * Sets the speed of the animal by incrementing it.
     *
     * @param increment The amount to increment the speed by.
     */
    protected void setSpeed(int increment) {
        this.speed += increment;
    }

    /**
     * Feeds the animal by adding energy and updating energy consumption.
     *
     * @param energy The amount of energy to add.
     * @return true if the feeding is successful.
     */
    public boolean eat(int energy) {
        this.energyAmount += energy;
        this.energyConsumption += energy;
        return true;
    }

    /**
     * Gets the size of the animal.
     *
     * @return The size.
     */
    public int getSize() {
        return size;
    }

    /**
     * Sets the size of the animal.
     *
     * @param size The size to set.
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Gets the orientation of the animal.
     *
     * @return The orientation.
     */
    public String getOrientation() {
        return orientation.name();
    }

    /**
     * Gets the image of the animal.
     *
     * @return The image as a BufferedImage object.
     */
    public BufferedImage Getimage() {
        return img1;
    }

    /**
     * Sets the orientation of the animal.
     *
     * @param orientation The orientation to set.
     */
    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    /**
     * Checks if this animal is equal to another object.
     *
     * @param o The object to compare with.
     * @return true if this animal is equal to the specified object, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal)) return false;
        if (!super.equals(o)) return false;
        Animal animal = (Animal) o;
        return Double.compare(animal.weight, weight) == 0 &&
                Double.compare(animal.speed, speed) == 0 &&
                size == animal.size &&
                id == animal.id &&
                maxEnergy == animal.maxEnergy &&
                energyPerMeter == animal.energyPerMeter &&
                Objects.equals(name, animal.name) &&
                gender == animal.gender &&
                Arrays.equals(medals, animal.medals) &&
                Objects.equals(position, animal.position) &&
                orientation == animal.orientation;
    }

    /**
     * Returns a string representation of this animal.
     *
     * @return A string representation of this animal.
     */
    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", weight=" + weight +
                ", speed=" + speed +
                ", medals=" + Arrays.toString(medals) +
                ", position=" + position +
                ", orientation=" + orientation +
                ", size=" + size +
                ", id=" + id +
                ", maxEnergy=" + maxEnergy +
                ", energyPerMeter=" + energyPerMeter +
                "} " + super.toString();
    }
}
