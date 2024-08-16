package us.wolfden.workoutapp.menus;

public class MenuManager {
    public static void displayMenu(Menu menu) {
        menu.display();
    }

    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
