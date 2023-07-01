package MainApp;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

public class ImageManagement {

    /**
     * The image index used to return the image that is the current
     */
    private int currentActiveImageIndex = -1;

    private int currentCachedImageIndex = -1;
    private File[] imageFiles = null;

    private int timeDisplayMS = 2000;

    /**
     * Current images in memory
     */
    private BufferedImage[] cachedImages = null;

    /**
     * <p>The default number of images for the program to keep in memory.</p>
     * <p>Prev, current, next</p>
     */
    private static final int DEFAULT_NUM_IMAGES = 3;
    private final int maxCacheSize;

    /**
     * Initialize using defaults
     */
    public ImageManagement() {
        this(DEFAULT_NUM_IMAGES);
    }

    /**
     * Initialize with a custom image cache size.
     */
    public ImageManagement(int cachedImagesSize) {
        if (cachedImagesSize < 2) throw new RuntimeException("Image cache size is less then minimum of 2!");
        cachedImages = new BufferedImage[cachedImagesSize];
        maxCacheSize = cachedImagesSize;
    }

    public boolean hasImage() {
        if (currentActiveImageIndex == -1 || currentActiveImageIndex == -1) {
            return false;
        }
        if (cachedImages != null) {
            if (currentCachedImageIndex < cachedImages.length || currentCachedImageIndex >= 0) {
                if (cachedImages[currentCachedImageIndex] != null) {
                    return true;
                }
            }
        }
        return false;
    }

    public BufferedImage getCurrent() {
        return cachedImages[currentCachedImageIndex];
    }

    public boolean setImageFiles(File[] imageFiles) {
        if (imageFiles.length >= 1) {
            this.imageFiles = imageFiles;
            if (imageFiles.length < maxCacheSize) {
                cachedImages = new BufferedImage[imageFiles.length];
            } else {
                cachedImages = new BufferedImage[maxCacheSize];
            }
            prepFromStart();
            return true;
        }
        return false;
    }

    private void prepFromStart() {
        currentCachedImageIndex = 0;
        currentActiveImageIndex = 0;
        for (int i = 0; i < cachedImages.length && i < imageFiles.length; i++) {
            try {
                cachedImages[i] = ImageIO.read(imageFiles[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void goToNextImage() {
        currentActiveImageIndex ++;
        if (currentActiveImageIndex >= imageFiles.length) currentActiveImageIndex = 0;
        currentCachedImageIndex ++;
        if (currentCachedImageIndex >= cachedImages.length) currentCachedImageIndex = 0;

        // load image 2 places ahead
        int loadToIndex = (currentCachedImageIndex + (cachedImages.length - 1) >= cachedImages.length ? currentCachedImageIndex + (cachedImages.length - 1) - cachedImages.length : currentCachedImageIndex + (cachedImages.length - 1));
        int loadFromImageFilesIndex = (currentActiveImageIndex + (cachedImages.length - 1) >= imageFiles.length ? currentActiveImageIndex + (cachedImages.length - 1) - imageFiles.length : currentActiveImageIndex + (cachedImages.length - 1));

        try {
            cachedImages[loadToIndex] = ImageIO.read(imageFiles[loadFromImageFilesIndex]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setTimeDisplayMS(int timeDisplayMS) {
        this.timeDisplayMS = timeDisplayMS;
    }

    public int getTimeDisplayMS() {
        return timeDisplayMS;
    }


    Timer slideshowTimer = null;

    public void startSlideshow() {
        if (imageFiles == null) return;
        
        slideshowTimer = new Timer();
        slideshowTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                goToNextImage();
                PISlideshow.imagePanel.repaint();
            }
        }, 0, timeDisplayMS);
    }

    public void restartSlideshow() {
        if (imageFiles == null) return;

        prepFromStart();
        startSlideshow();
    }

    public void stopSlideshow() {
        if (slideshowTimer == null) return;
        slideshowTimer.cancel();
    }

}
