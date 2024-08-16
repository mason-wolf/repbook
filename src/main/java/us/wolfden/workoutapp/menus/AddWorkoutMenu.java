package us.wolfden.workoutapp.menus;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddWorkoutMenu implements Menu {

    private final List<String> options = new ArrayList<>();

    public AddWorkoutMenu() {
        options.add("1. Add Exercise");
        options.add("2. Add Routine");
        options.add("3. Save");
        options.add("4. Back");
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
        System.out.print("Workout Name: ");
        Scanner scanner = new Scanner(System.in);
        System.out.print(scanner.nextLine());
    }
}
