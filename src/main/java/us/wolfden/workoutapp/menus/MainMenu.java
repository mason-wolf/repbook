package us.wolfden.workoutapp.menus;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainMenu implements Menu {

    private final List<String> options = new ArrayList<>();

    public MainMenu() {
        options.add("1. Add Workout");
        options.add("2. View Workouts");
        options.add("3. Search Workouts");
        options.add("4. Reports");
    }

    @Override
    public List<String> options() {
        return options;
    }

    @Override
    public void display() {
        options().forEach(System.out::println);
        handleSelection();
    }

    @Override
    public void handleSelection() {
        Scanner scanner = new Scanner(System.in);
        boolean appRunning = true;

        while (appRunning) {
            System.out.print("> ");
            String choice = scanner.nextLine();

            if (choice.equals("3")) {
                appRunning = false;
                MenuManager.displayMenu(new MainMenu());
            }
        }
    }
}
