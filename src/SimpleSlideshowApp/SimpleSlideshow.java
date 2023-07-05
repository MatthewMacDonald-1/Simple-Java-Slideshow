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
import java.io.File;
import java.io.IOException;
import java.net.URI;

public class SimpleSlideshow {

    // From args
    /** Is unset if equal to -1 */
    private static int customCacheSize = -1;
    private static boolean useNoUI = false;
    private static int timeDelayOverride = 2000;

    static protected JFrame mainFrame = null;
    
    static protected ImageManagement imageManagement;
    static final private Dimension DEFAULT_MIN_WINDOW_SIZE = new Dimension(800, 500);

    // Main Panels
    static protected SettingsPanel settingsPanel = null;
    static protected ImagePanel imagePanel = null;

    static protected JPanel currentPanel = null;

    private static boolean isFirstTime = true;

    /**
     * <p></p>
     *    
     * <p>Note: if you aren't calling this function for the first time you should dispose the <code>JFrame</code> <code>mainFrame</code> before calling this function.</p>
     * @param setUndecorated Set <code>true</code> if using full screen otherwise <code>false</code>
     * @throws IOException Throws an <code>IOException</code> if the class constructors can't find expected packaged images.
     */
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

        SlideshowMenuActions slideshowMenuActions = new SlideshowMenuActions(useNoUI);
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

        // Contains configuration variables for the program.
        if (isFirstTime) {
            imageManagement = customCacheSize != -1 ? new ImageManagement(customCacheSize) : new ImageManagement();

            settingsPanel = new SettingsPanel(imageManagement);

            imagePanel = new ImagePanel(imageManagement);

            if (useNoUI) {
                currentPanel = imagePanel;
            } else {
                currentPanel = settingsPanel;
            }            

            isFirstTime = false;
        }
        // Configuration end

        // Set default panel
        mainFrame.add(currentPanel, BorderLayout.CENTER);

        mainFrame.setUndecorated(setUndecorated);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    /**
     * Change the window name.
     * @param newWindowName New window name to set.
     */
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

    /**
     * Returns the current pixel dimensions of the application window.
     * @return
     */
    protected static Dimension frameSize() {
        return mainFrame.getSize();
    }


    /**
     * Returns the index of the candidate string if it exists in the array otherwise -1.
     * @param candidate
     * @param arr
     * @return
     */
    private static int stringArrIncludes(String candidate, String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (candidate.equals(arr[i])) return i;
        }
        return -1;
    }

    /**
     * Analyze and parse the args array passed to the main static function and set variables.
     * @param args Main function args string array
     */
    private static void argScanner(String[] args) {
        if (args.length >= 1) {
            final String[] flags = { "cache", "no_ui", "time_delay" };
            int[] seenTimes = new int[flags.length];

            for (int i = 0; i < args.length; i++) {
                int index = stringArrIncludes(args[i], args);
                if (index != -1) {
                    if (seenTimes[index] == 0) {

                        if (args[i].equals(flags[0])) {
                            if (i + 1 < args.length) {
                                // if index exists try to parse int
                                try {
                                    customCacheSize = Integer.parseInt(args[i + 1]);
                                    if (customCacheSize != -1) System.out.println("Custom cache size set to: " + customCacheSize);
                                } catch (NumberFormatException e) {
                                    // continue without custom cache size
                                    System.out.println("Invalid custom cache size: " + args[i + 1] + ". Continuing using default: " + ImageManagement.DEFAULT_NUM_IMAGES + ".");
                                }
                            }
                        }
                        else if (args[i].equals(flags[1])) {
                            useNoUI = true; // configure settings
                        }
                        else if (args[i].equals(flags[2])) {
                            if (i + 1 < args.length) {
                                // if index exists try to parse int
                                try {
                                    timeDelayOverride = Integer.parseInt(args[i + 1]);
                                    if (timeDelayOverride != -1) System.out.println("Image time delay override size set to: " + timeDelayOverride + " ms");
                                } catch (NumberFormatException e) {
                                    // continue without custom cache size
                                    System.out.println("Invalid time delay: " + args[i + 1] + ". Continuing using defaults: " + 2000 + " ms.");
                                }
                            }
                        }

                        seenTimes[index]++;
                    } else {
                        System.out.println("Flag '" + args[i] + "' at index '" + i + "' ignored.");
                    }
                }
            }
        }
    }
    

    public static void main(String[] args) throws Exception {

        argScanner(args);

        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Create Start GUI                    
                    createAndShowGUI(false);
                    if (useNoUI) {
                                         
                        URI URIpath = SimpleSlideshow.class.getProtectionDomain().getCodeSource().getLocation().toURI();
                        File directory = new File(URIpath).getParentFile();

                        System.out.println(directory.getAbsolutePath() + " " + directory.isDirectory());
                        settingsPanel.startWithNoUI(directory, timeDelayOverride);
                        // switchToImagePanel();     
                        // try {
                        //     WindowMenuActions.changeWindow(WindowMenuActions.ScreenState.fullScreen, mainFrame);
                        // } catch (IOException e) {
                        //     // Do nothing
                        // }
                    } else {
                        switchToSettingsPanel();
                    }
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.exit(1);
                }
            }
        });

    }
}
