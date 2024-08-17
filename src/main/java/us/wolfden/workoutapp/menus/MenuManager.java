package us.wolfden.workoutapp.menus;

/***
 * Controls menu views and manages menu states.
 */
public class MenuManager extends AbstractMenuManager {

    public static void displayMenu(Menu menu) {
        clearMenu();
        menu.display();
    }

    /***
     * Clears the terminal depending on platform.
     */
    public static void clearMenu() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            // Windows
            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (Exception e) {
                System.out.println("Error clearing screen.");
            }
        } else {
            // Unix-like systems
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }
}
