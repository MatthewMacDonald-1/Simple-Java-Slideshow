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
            SimpleSlideshow.imageManagement.startSlideshow();
        }
    }
    
}