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

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpinnerNumberModel;

public class SettingsPanel extends JPanel {
    
    private ImageManagement imageManagement = null;

    private JFileChooser chooser = null;
    private final String CHOOSER_TITLE = "Select image folder";

    JPanel panel = this;

    private int timeDisplayMS = 2000;
    private boolean hasChosenFilesAndFolder = false;
    private File[] imageFilesFromChosenDirectory = null;

    public SettingsPanel(ImageManagement imageManagement) {
        this.imageManagement = imageManagement;

        SimpleSlideshow.setWindowName("Rasp PI Slideshow - Settings");

        initComponents();
    }

    private javax.swing.JButton chooseImageDirectoryButton;
    private javax.swing.JButton chooseImageDirectoryButton1;
    private java.awt.Label displayTimeLabel;
    private java.awt.Label displayTimeLabel1;
    private javax.swing.JSpinner displayTimeSpinner;
    private SpinnerNumberModel displayTimeSpinner1Model = new SpinnerNumberModel(2000, 1000, 10000, 500);
    private javax.swing.JSpinner displayTimeSpinner1;
    private java.awt.TextArea imageFilesTextArea;
    private java.awt.TextArea imageFilesTextArea1;
    private java.awt.TextArea imageFilesTextArea2;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JToggleButton repeatToggleButton;
    private javax.swing.JToggleButton repeatToggleButton1;
    private javax.swing.JToggleButton repeatToggleButton2;
    private javax.swing.JLabel settingsHeaderLabel;
    private javax.swing.JLabel settingsHeaderLabel1;
    private javax.swing.JButton startSlideshowButton;
    private javax.swing.JButton startSlideshowButton1;
       
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        settingsHeaderLabel = new javax.swing.JLabel();
        chooseImageDirectoryButton = new javax.swing.JButton();
        displayTimeSpinner = new javax.swing.JSpinner();
        displayTimeLabel = new java.awt.Label();
        imageFilesTextArea = new java.awt.TextArea();
        startSlideshowButton = new javax.swing.JButton();
        repeatToggleButton = new javax.swing.JToggleButton();
        repeatToggleButton1 = new javax.swing.JToggleButton();
        imageFilesTextArea1 = new java.awt.TextArea();
        settingsHeaderLabel1 = new javax.swing.JLabel();
        chooseImageDirectoryButton1 = new javax.swing.JButton();
        displayTimeLabel1 = new java.awt.Label();
        displayTimeSpinner1 = new javax.swing.JSpinner(displayTimeSpinner1Model);
        repeatToggleButton2 = new javax.swing.JToggleButton();
        startSlideshowButton1 = new javax.swing.JButton();
        imageFilesTextArea2 = new java.awt.TextArea();

        jFrame1.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        settingsHeaderLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        settingsHeaderLabel.setText("Settings");
        settingsHeaderLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        chooseImageDirectoryButton.setText("Choose image directory");
        chooseImageDirectoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseImageDirectoryButtonActionPerformed(evt);
            }
        });

        displayTimeLabel.setText("Image display time in milliseconds");

        imageFilesTextArea.setEditable(false);

        startSlideshowButton.setText("Start");
        startSlideshowButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startSlideshowButtonActionPerformed(evt);
            }
        });

        repeatToggleButton.setText("Repeat Infinitely");

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jFrame1Layout.createSequentialGroup()
                        .addComponent(repeatToggleButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(startSlideshowButton))
                    .addGroup(jFrame1Layout.createSequentialGroup()
                        .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(imageFilesTextArea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(chooseImageDirectoryButton)
                                .addGroup(jFrame1Layout.createSequentialGroup()
                                    .addComponent(displayTimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(displayTimeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(settingsHeaderLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(settingsHeaderLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chooseImageDirectoryButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(displayTimeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(displayTimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imageFilesTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startSlideshowButton)
                    .addComponent(repeatToggleButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        repeatToggleButton1.setText("Repeat Infinitely");

        imageFilesTextArea1.setEditable(false);

        settingsHeaderLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        settingsHeaderLabel1.setText("Settings");
        settingsHeaderLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        chooseImageDirectoryButton1.setText("Choose image directory");
        chooseImageDirectoryButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseImageDirectorButton1ActionPerformed(evt);
            }
        });

        displayTimeLabel1.setText("Image display time in milliseconds");

        repeatToggleButton2.setText("Repeat Infinitely");

        startSlideshowButton1.setText("Start");
        startSlideshowButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startSlideshowButton1ActionPerformed(evt);
            }
        });

        imageFilesTextArea2.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(settingsHeaderLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(repeatToggleButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(startSlideshowButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chooseImageDirectoryButton1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(displayTimeLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(displayTimeSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 90, Short.MAX_VALUE))
                    .addComponent(imageFilesTextArea2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(settingsHeaderLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(chooseImageDirectoryButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(displayTimeLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(displayTimeSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imageFilesTextArea2, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(repeatToggleButton2)
                    .addComponent(startSlideshowButton1))
                .addContainerGap())
        );
    }// </editor-fold>                        

    private void chooseImageDirectoryButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                          
        // Can't be called
    }                                                         

    private void startSlideshowButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                     
        // Can't be called
    }                                                    

    private void chooseImageDirectorButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        chooser = new JFileChooser(); 
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle(CHOOSER_TITLE);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        
        if (chooser.showOpenDialog(panel) == JFileChooser.APPROVE_OPTION) {
        } else {
            // System.out.println("No Selection ");
            JOptionPane.showMessageDialog(null, "No folder selected.", "User Input ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // TODO create an array of all the image file paths in the selected directory and its subfolders.
        File selectedDirectory = chooser.getSelectedFile();
        if (!(selectedDirectory.exists() && selectedDirectory.isDirectory())) {
            JOptionPane.showMessageDialog(null, "Please select a genuine folder", "User Input ERROR", JOptionPane.ERROR_MESSAGE);
            return; // Directory not valid
        }

        File[] filesInFolder = selectedDirectory.listFiles();

        ArrayList<File> foundImages = new ArrayList<File>();

        findImageFiles(filesInFolder, 0, foundImages);

        File[] imageFiles = new File[foundImages.size()];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < imageFiles.length; i++) {
            imageFiles[i] = foundImages.get(i);
            sb.append(imageFiles[i].toString());
            if (i != imageFiles.length - 1) sb.append('\n');
        }
        imageFilesTextArea2.setText(sb.toString());

        // Then set variables for passing on start if there is at least on image present
        imageFilesFromChosenDirectory = imageFiles;
        hasChosenFilesAndFolder = true;
    }

    static final FilenameFilter imageFileFilter = new FilenameFilter() {
        public boolean accept(File dir, String name) {
            String lowercaseName = name.toLowerCase();
            if (lowercaseName.endsWith(".png") || lowercaseName.endsWith(".jpg") || lowercaseName.endsWith(".jpeg") || lowercaseName.endsWith(".gif") || lowercaseName.endsWith(".BMP") || lowercaseName.endsWith(".WBMP")) {
               return true;
            } else {
               return false;
            }
         }
    }; 
    
    /**
     * <p>Uses <code>imageFileFilter</code> as a filter for supported image formats.</p>
     * @param a
     * @param i
     * @param output
     * 
     * @see imageFileFilter
     */
    private void findImageFiles(File[] a, int i, ArrayList<File> output) {
        if (i == a.length) { // base case for recursion
            return;
        }

        if (a[i].isFile()) {
            if (imageFileFilter.accept(a[i], a[i].getName())) {
                output.add(a[i]); // add to output
            }
        } else if (a[i].isDirectory()) {
            findImageFiles(a[i].listFiles(), 0, output);
        }

        findImageFiles(a, i + 1, output);
    }

    private void startSlideshowButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                                      
        
        if (hasChosenFilesAndFolder) {
            if (timeDisplayMS >= 500 && timeDisplayMS <= 10000) {
                // Do nothing
            } else {
                if (timeDisplayMS < 0) {
                    JOptionPane.showMessageDialog(null, "Time delay must be positive.", "ERROR", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Warning. The Normal image time delay is between is 1,000 to 10,000 milliseconds, you put " + timeDisplayMS + ".", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
            imageManagement.setTimeDisplayMS(timeDisplayMS);
            if (!imageManagement.setImageFiles(imageFilesFromChosenDirectory)) JOptionPane.showMessageDialog(null, "ERROR", "ERROR", JOptionPane.ERROR_MESSAGE);
            imageManagement.startSlideshow();
            SimpleSlideshow.switchToImagePanel();
        } else {
            JOptionPane.showMessageDialog(null, "Please select a folder with images or subfolder containing images.", "User Input ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    protected void startWithNoUI(File baseDirectory, int timeDisplayMS) {
        File[] filesInFolder = baseDirectory.listFiles();

        ArrayList<File> foundImages = new ArrayList<File>();

        findImageFiles(filesInFolder, 0, foundImages);

        File[] imageFiles = new File[foundImages.size()];
        for (int i = 0; i < imageFiles.length; i++) {
            imageFiles[i] = foundImages.get(i);
        }

        // Then set variables for passing on start if there is at least on image present
        imageFilesFromChosenDirectory = imageFiles;
        hasChosenFilesAndFolder = true;
        this.timeDisplayMS = timeDisplayMS;

        // Start
        startSlideshowButton1ActionPerformed(null);
    }

}
