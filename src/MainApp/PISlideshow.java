package MainApp;

import javax.swing.JFrame;

import javax.swing.WindowConstants;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import java.awt.Image;
import javax.imageio.ImageIO;

public class PISlideshow {

    // From args
    /** Is unset if equal to -1 */
    private static int customCacheSize = -1;

    // 
    static private JFrame fullscreenWidnow = null;
    static private JFrame mainFrame = null;
    static private GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
    static private ImageManagement imageManagement;
    static final private Dimension DEFAULT_MIN_WINDOW_SIZE = new Dimension(800, 500);

    // Main Panels
    static private SettingsPanel settingsPanel = null;
    static protected ImagePanel imagePanel = null;

    private static void createAndShowGUI() throws Exception { 
        mainFrame = new JFrame("Rasp PI Slideshow");
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setMinimumSize(DEFAULT_MIN_WINDOW_SIZE);

        // Window Icon
        // Image image = ImageIO.read(PISlideshow.class.getClassLoader().getResource("icon.png"));
        // mainStartWindow.setIconImage(image);

        imageManagement = customCacheSize != -1 ? new ImageManagement(customCacheSize) : new ImageManagement();

        settingsPanel = new SettingsPanel(imageManagement);

        imagePanel = new ImagePanel(imageManagement);

        // Set default panel
        mainFrame.add(settingsPanel, BorderLayout.CENTER);

        //switchToImagePanel();

        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    protected static void setWindowName(String newWindowName) {
        mainFrame.setTitle(newWindowName);
    }

    protected static void switchToSettingsPanel() {
        mainFrame.remove(imagePanel);
        mainFrame.add(settingsPanel, BorderLayout.CENTER);

        PISlideshow.setWindowName("Rasp PI Slideshow - Settings");

        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    protected static void switchToImagePanel() {
        mainFrame.remove(settingsPanel);
        mainFrame.add(imagePanel);

        PISlideshow.setWindowName("Rasp PI Slideshow");

        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    protected static Dimension frameSize() {
        return mainFrame.getSize();
    }

    public static void main(String[] args) throws Exception {

        if (args.length >= 1) {
            try {
                customCacheSize = Integer.parseInt(args[0]);
                System.out.println("Custom cache size set to: " + customCacheSize);
            } catch (NumberFormatException e) {
                // continue without custom cache size
                System.out.println("Invalid custom cache size. Continuing using defaults.");
            }
            
        }
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Create Start GUI                    
                    createAndShowGUI();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.exit(1);
                }
            }
        });

    }
}
