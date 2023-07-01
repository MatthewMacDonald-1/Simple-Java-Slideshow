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

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

public abstract class ShortCutEnabledAction extends AbstractAction {

    public ShortCutEnabledAction(String name, String desc, Integer mnemonic, KeyStroke accelerator) {
        super(name);
        if (desc != null) {
            putValue(SHORT_DESCRIPTION, desc);
        }
        if (mnemonic != null) {
            putValue(MNEMONIC_KEY, mnemonic);
        }
        if (accelerator != null) {
            putValue(ACCELERATOR_KEY, accelerator);
        }

    }
    
}
