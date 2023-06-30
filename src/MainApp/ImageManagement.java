package MainApp;

import java.awt.image.BufferedImage;

public class ImageManagement {

    /**
     * The image index used to return the image that is the current
     */
    private int currentActiveImage = 0;

    /**
     * Current images in memory
     */
    private BufferedImage[] images = null;

    /**
     * The default number of images for the program to keep in memory
     */
    private static int DEFAULT_NUM_IMAGES = 2;

    public ImageManagement() {
        images = new BufferedImage[DEFAULT_NUM_IMAGES];
    }

    public boolean hasImage() {
        if (images != null) {
            if (currentActiveImage >= images.length || currentActiveImage < 0) {
                if (images[currentActiveImage] != null) {
                    return true;
                }
            }
        }
        return false;
    }

    public BufferedImage getCurrent() {
        return images[currentActiveImage];
    }

}
