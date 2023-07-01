package MainApp;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
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

        setBackground(Color.BLACK); // sets frame background to black

        BufferedImage curseImg = new BufferedImage(12, 16, BufferedImage.TYPE_INT_ARGB);
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(curseImg, new Point(0, 0), "blank cursor");
        setCursor(blankCursor);
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
        double scaleW = 1;
        double scaleH = 1;
        if (widthIsGreater) {
            scaleW = (double)windowSize.getWidth() / (double)largestSide;
        } else {
            scaleH = (double)windowSize.getHeight() / (double)largestSide;
        }
        //System.out.println(PISlideshow.frameSize().width);

        double scaleChosen = widthIsGreater ? scaleW : scaleH;
        boolean imageWider = (int)((double)toDraw.getWidth() * scaleChosen) > windowSize.getWidth();
        boolean imageTaller = (int)((double)toDraw.getHeight() * scaleChosen) > windowSize.getHeight();

        ///g2.scale(scale, scale);
        int yOffset = 0;
        int xOffset = 0;
        if (widthIsGreater) {
            
            if (imageTaller) {
                double modScale = scaleChosen * ((double)windowSize.getHeight() / ((double)toDraw.getHeight() * scaleChosen));
                // System.out.println("Taller: " + scaleChosen + " -> " + modScale); // confirmed works
                g2.scale(modScale, modScale);
                xOffset = (int)((double)((int)windowSize.getWidth() - (int)((double)toDraw.getWidth() * modScale)) * 1f/modScale) / 2;
            } else {
                g2.scale(scaleChosen, scaleChosen);
                yOffset = (int)((double)((int)windowSize.getHeight() - (int)((double)toDraw.getHeight() * scaleChosen)) * 1f/scaleChosen) / 2;
            }
        } else {
            if (imageWider) {
                double modScale = scaleChosen * ((double)windowSize.getWidth() / ((double)toDraw.getWidth() * scaleChosen));
                // System.out.println("Wider: " + scaleChosen + " -> " + modScale); // confirmed works
                g2.scale(modScale, modScale);
                yOffset = (int)((double)((int)windowSize.getHeight() - (int)((double)toDraw.getHeight() * modScale)) * 1f/modScale) / 2;
            } else {
                g2.scale(scaleChosen, scaleChosen);
                xOffset = (int)((double)((int)windowSize.getWidth() - (int)((double)toDraw.getWidth() * scaleChosen)) * 1f/scaleChosen) / 2;
            }
        }
        g2.drawImage(toDraw, null, xOffset, yOffset);
        g2.dispose();
    }

}
