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

import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class WindowMenuActions extends MenuAction {

    public static enum ScreenState {
        fullScreen, borderLess, windowed, none
    };
    public static ScreenState current;
    private static GraphicsDevice ev = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

    public WindowMenuActions() {
        actions.add(new FullScreenWindowAction("Full Screen Mode", "Puts window into full screen mode", Integer.valueOf(KeyEvent.VK_F), KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_DOWN_MASK)));
        actions.add(new WindowedWindowAction("Windowed Mode", "Puts window into windowed mode", Integer.valueOf(KeyEvent.VK_W), KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.CTRL_DOWN_MASK)));
        actions.add(new BorderLessWindowAction("Border less window Mode", "Puts window into border less window mode", Integer.valueOf(KeyEvent.VK_B), KeyStroke.getKeyStroke(KeyEvent.VK_B, KeyEvent.CTRL_DOWN_MASK)));
    }

    @Override
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu("Window");

        for(Action action: actions) {
            fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;
    }

    public static void changeWindow(ScreenState applied, JFrame frame) throws IOException {
        if (applied == ScreenState.fullScreen && current != ScreenState.fullScreen) {
            if (ev.isFullScreenSupported()) {
                ev.setFullScreenWindow(frame);
            }
            current =  ScreenState.fullScreen;
        }
        if (applied == ScreenState.borderLess && current != ScreenState.borderLess) {
            frame.dispose();
            try {
                SimpleSlideshow.createAndShowGUI(true);
            } catch (IOException e) {
                // DO nothing
            }
            frame.setExtendedState(Frame.MAXIMIZED_BOTH);
            current =  ScreenState.borderLess;
        }
        if (applied == ScreenState.windowed && current != ScreenState.windowed) {
            frame.dispose();
            try {
                SimpleSlideshow.createAndShowGUI(false);
            } catch (IOException e) {
                // DO nothing
            }
            
            frame.setExtendedState(Frame.MAXIMIZED_BOTH);
            current =  ScreenState.windowed;
        }
    }

    class FullScreenWindowAction extends ShortCutEnabledAction {
        
        public FullScreenWindowAction(String name, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, desc, mnemonic, accelerator);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                changeWindow(WindowMenuActions.ScreenState.fullScreen, SimpleSlideshow.mainFrame);
            } catch (IOException ex) {
                // Do nothing
            }
        }

    }

    class WindowedWindowAction extends ShortCutEnabledAction {
        
        public WindowedWindowAction(String name, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, desc, mnemonic, accelerator);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                changeWindow(WindowMenuActions.ScreenState.windowed, SimpleSlideshow.mainFrame);
            } catch (IOException ex) {
                // Do nothing
            }
        }
    }

    class BorderLessWindowAction extends ShortCutEnabledAction {
        
        public BorderLessWindowAction(String name, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, desc, mnemonic, accelerator);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                changeWindow(WindowMenuActions.ScreenState.borderLess, SimpleSlideshow.mainFrame);
            } catch (IOException ex) {
                // Do nothing
            }
        }
    }
}
