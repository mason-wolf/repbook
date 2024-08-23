package us.wolfden.workoutapp.menus;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/***
 * Represents the main menu for managing and viewing workouts.
 */
public class MainMenu implements Menu {

    private final List<String> options = new ArrayList<>();

    public MainMenu() {
        options.add("1. Add Workout");
        options.add("2. View Workouts");
        options.add("3. Manage Routines");
        options.add("4. Reports");
        options.add("5. Exit");
    }

    @Override
    public List<String> options() {
        return options;
    }

    @Override
    public void display() {
        System.out.println("Repbook v1.0");
        options().forEach(System.out::println);
        handleSelection();
    }

    @Override
    public void handleSelection() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        String choice = scanner.nextLine();

        if (choice.equals("1")) {
            MenuManager.displayMenu(new AddWorkoutMenu());
        }

        if (choice.equals("2")) {
            MenuManager.displayMenu(new ViewWorkoutsMenu());
        }

        if (choice.equals("3")) {
            MenuManager.displayMenu(new RoutineMenu());
        }

        if (choice.equals("4")) {
            MenuManager.displayMenu(new MainMenu());
        }

        if (choice.equals("5")) {
            System.out.println("Goodbye, work hard.");
            System.exit(0);
        }
    }
}
