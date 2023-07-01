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

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;


import javax.swing.WindowConstants;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class SimpleSlideshow {

    // From args
    /** Is unset if equal to -1 */
    private static int customCacheSize = -1;

    static protected JFrame mainFrame = null;
    
    static protected ImageManagement imageManagement;
    static final private Dimension DEFAULT_MIN_WINDOW_SIZE = new Dimension(800, 500);

    // Main Panels
    static protected SettingsPanel settingsPanel = null;
    static protected ImagePanel imagePanel = null;

    static protected JPanel currentPanel = null;

    private static boolean isFirstTime = true;

    protected static void createAndShowGUI(boolean setUndecorated) throws IOException {
        mainFrame = new JFrame("Simple Java Slideshow");
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setMinimumSize(DEFAULT_MIN_WINDOW_SIZE);

        // Window Icon
        // Image image = ImageIO.read(SimpleSlideshow.class.getClassLoader().getResource("icon.png"));
        // mainFrame.setIconImage(image);

        // Create JMenuBar
        JMenuBar menuBar = new JMenuBar();

        WindowMenuActions windowActions = new WindowMenuActions();
        menuBar.add(windowActions.createMenu());

        SlideshowMenuActions slideshowMenuActions = new SlideshowMenuActions();
        menuBar.add(slideshowMenuActions.createMenu());

        menuBar.setVisible(false);
        mainFrame.setJMenuBar(menuBar);

        mainFrame.addMouseMotionListener(new MouseAdapter() {

            @Override
            public void mouseMoved(MouseEvent e) {
                if (mainFrame.getJMenuBar().isVisible()) {
                    try {
                        Robot robot = new Robot();
                        robot.keyPress(KeyEvent.VK_ESCAPE);
                    } catch (AWTException ex) {
                    }

                }
                mainFrame.getJMenuBar().setVisible(e.getY() < 50);
            }
        });


        if (isFirstTime) {
            imageManagement = customCacheSize != -1 ? new ImageManagement(customCacheSize) : new ImageManagement();
            //imageManagement = new ImageManagement();
            settingsPanel = new SettingsPanel(imageManagement);

            imagePanel = new ImagePanel(imageManagement);

            currentPanel = settingsPanel;

            isFirstTime = false;
        }
        

        // Set default panel
        mainFrame.add(currentPanel, BorderLayout.CENTER);

        mainFrame.setUndecorated(setUndecorated);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    protected static void setWindowName(String newWindowName) {
        mainFrame.setTitle(newWindowName);
    }

    protected static void switchToSettingsPanel() {
        currentPanel = settingsPanel;

        SimpleSlideshow.setWindowName("Simple Java Slideshow - Settings");


        try {
            if (WindowMenuActions.current != WindowMenuActions.ScreenState.windowed) {
                WindowMenuActions.changeWindow(WindowMenuActions.ScreenState.windowed, mainFrame);
            } else {
                mainFrame.dispose();
                createAndShowGUI(false);
            }
        } catch (IOException e) {
            // Do nothing
        }
    }

    protected static void switchToImagePanel() {
        currentPanel = imagePanel;

        mainFrame.remove(settingsPanel);
        mainFrame.add(imagePanel);

        SimpleSlideshow.setWindowName("Simple Java Slideshow");

        mainFrame.pack();
        mainFrame.setVisible(true);

        try {
            WindowMenuActions.changeWindow(WindowMenuActions.ScreenState.fullScreen, mainFrame);
        } catch (IOException e) {
            // Do nothing
        }
        
    }

    protected static Dimension frameSize() {
        return mainFrame.getSize();
    }

    

    public static void main(String[] args) throws Exception {

        if (args.length >= 1) {
            try {
                customCacheSize = Integer.parseInt(args[0]);
                if (customCacheSize != -1) System.out.println("Custom cache size set to: " + customCacheSize);
            } catch (NumberFormatException e) {
                // continue without custom cache size
                System.out.println("Invalid custom cache size. Continuing using defaults.");
            }
            
        }
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Create Start GUI                    
                    createAndShowGUI(false);
                    switchToSettingsPanel();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.exit(1);
                }
            }
        });

    }
}
