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
