package MainApp;

import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.JMenu;

public abstract class MenuAction {
    
    /**
     * Private ArrayList of actions.
     */
    ArrayList<Action> actions = new ArrayList<Action>();

    /**
     * Generates and returns a JMenu object containing the Actions associated with this class.
     * @return JMenu for JMenuBar containing Actions
     */
    public abstract JMenu createMenu();
}
