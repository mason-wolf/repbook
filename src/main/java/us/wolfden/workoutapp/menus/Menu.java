package us.wolfden.workoutapp.menus;

import java.util.List;

/***
 * Represents a CLI menu.
 */
public interface Menu {
    /***
     * Menu options.
     * @return List of options.
     */
    List<String> options();

    /***
     * Displays the menu.
     */
    void display();

    /***
     * Handles menu selection.
     */
    void handleSelection();
}
