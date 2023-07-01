package MainApp;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.ActionEvent;

import javax.swing.Action;

public class SlideshowMenuActions extends MenuAction 
{
    public SlideshowMenuActions() {
        actions.add(new GoToSettingsSlideshowMenuAction("Settings", "Go to slideshow settings", null, null));
        actions.add(new ResumeSlideShowMenuAction("Resume", "Resume slideshow from beginning", null, null));
        actions.add(new PauseSlideshowMenuAction("Pause", "Pause slideshow from beginning", null, null));
        actions.add(new RestartSlideshowMenuAction("Restart", "Restart slideshow from beginning", null, null));

    }

    @Override
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu("Slideshow");

        for(Action action: actions) {
            fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;
    }

    class GoToSettingsSlideshowMenuAction extends ShortCutEnabledAction {

        public GoToSettingsSlideshowMenuAction(String name, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, desc, mnemonic, accelerator);
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            SimpleSlideshow.imageManagement.stopSlideshow();
            SimpleSlideshow.switchToSettingsPanel();
        }
    }

    class RestartSlideshowMenuAction extends ShortCutEnabledAction {

        public RestartSlideshowMenuAction(String name, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, desc, mnemonic, accelerator);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            SimpleSlideshow.imageManagement.stopSlideshow();
            SimpleSlideshow.imageManagement.restartSlideshow();
        }
    }

    class PauseSlideshowMenuAction extends ShortCutEnabledAction {

        public PauseSlideshowMenuAction(String name, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, desc, mnemonic, accelerator);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            SimpleSlideshow.imageManagement.stopSlideshow();
        }
    }

    class ResumeSlideShowMenuAction extends ShortCutEnabledAction {

        public ResumeSlideShowMenuAction(String name, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, desc, mnemonic, accelerator);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // PISlideshow.imageManagement
            SimpleSlideshow.imageManagement.startSlideshow();
        }
    }
    
}