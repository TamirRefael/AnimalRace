package Graphics;

import java.awt.Graphics;

/**
 * An interface for drawable objects that can load images and be drawn on a Graphics object.
 */
public interface IDrawable {
    /**
     * The path to the resources folder where images are stored.
     */
    public final static String PICTURE_PATH = "resources/";

    /**
     * Loads images for the drawable object.
     *
     * @param nm The name of the image file.
     * @param animalType The type of the animal to determine the specific image.
     */
    void loadImages(String nm, String animalType);

    /**
     * Draws the object on the given Graphics context.
     *
     * @param g The Graphics context on which to draw the object.
     */
    void drawObject(Graphics g);
}
