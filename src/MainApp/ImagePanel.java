package MainApp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

    private ImageManagement image;
    private BufferedImage noImage;

    public ImagePanel(ImageManagement imageManagement) throws IOException {
        noImage = ImageIO.read(ImagePanel.class.getClassLoader().getResource("no-image.png"));
        image = imageManagement;
    }

    /**
     * <p>
     * (Re)draw the component in the GUI.
     * </p>
     * 
     * @param g The Graphics component to draw the image on.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage toDraw = null;
        if (image.hasImage()) {
            toDraw = image.getCurrent();
        } else {
            toDraw = noImage;
        }
        Graphics2D g2  = (Graphics2D) g.create();

        Dimension windowSize = getSize();

        boolean widthIsGreater = toDraw.getWidth() >= toDraw.getHeight();
        int largestSide = (widthIsGreater ? toDraw.getWidth() : toDraw.getHeight());
        double scale = 1;
        if (widthIsGreater) {
            scale = (double)windowSize.getWidth() / (double)largestSide;
        } else {
            scale = (double)windowSize.getHeight() / (double)largestSide;
        }
        //System.out.println(PISlideshow.frameSize().width);

        g2.scale(scale, scale);
        if (widthIsGreater) {
            int yOffset = (int)((double)((int)windowSize.getHeight() - (int)((double)toDraw.getHeight() * scale)) * 1f/scale) / 2;
            
            g2.drawImage(toDraw, null, 0, yOffset);
        } else {
            int xOffset = (int)((double)((int)windowSize.getWidth() - (int)((double)toDraw.getWidth() * scale)) * 1f/scale) / 2;

            g2.drawImage(toDraw, null, xOffset, 0);
        }
        g2.dispose();
    }

}
