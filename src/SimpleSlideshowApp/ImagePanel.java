// Simple Java Slideshow - A simple java GUI app designed to display a collection of images like a slideshow.
// Copyright (C) 2023  Matthew MacDonald
// 
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
// 
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.

// You should have received a copy of the GNU General Public License
// along with this program.  If not, see <https://www.gnu.org/licenses/>.

package SimpleSlideshowApp;

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

        double scaleChosen = widthIsGreater ? scaleW : scaleH;
        boolean imageWider = (int)((double)toDraw.getWidth() * scaleChosen) > windowSize.getWidth();
        boolean imageTaller = (int)((double)toDraw.getHeight() * scaleChosen) > windowSize.getHeight();

        int yOffset = 0;
        int xOffset = 0;
        if (widthIsGreater) {
            
            if (imageTaller) {
                double modScale = scaleChosen * ((double)windowSize.getHeight() / ((double)toDraw.getHeight() * scaleChosen));

                g2.scale(modScale, modScale);
                xOffset = (int)((double)((int)windowSize.getWidth() - (int)((double)toDraw.getWidth() * modScale)) * 1f/modScale) / 2;
            } else {
                g2.scale(scaleChosen, scaleChosen);
                yOffset = (int)((double)((int)windowSize.getHeight() - (int)((double)toDraw.getHeight() * scaleChosen)) * 1f/scaleChosen) / 2;
            }
        } else {
            if (imageWider) {
                double modScale = scaleChosen * ((double)windowSize.getWidth() / ((double)toDraw.getWidth() * scaleChosen));

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
