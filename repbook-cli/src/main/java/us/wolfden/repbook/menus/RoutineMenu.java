package us.wolfden.repbook.menus;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import us.wolfden.repbook.models.Routine;
import us.wolfden.repbook.services.RoutineService;
import us.wolfden.repbook.services.RoutineServiceImpl;

/***
 * Represents routine management view.
 */
public class RoutineMenu implements Menu {

    private final RoutineService routineService;
    private final List<String> options = new ArrayList<>();

    public RoutineMenu() {
        this.routineService = new RoutineServiceImpl();
        options.add("1. New Routine");
        options.add("2. View Routines");
        options.add("3. Delete Routine");
        options.add("4. Back");
    }

    @Override
    public List<String> options() {
        return options;
    }

    @Override
    public void display() {
        MenuManager.clearMenu();
        handleSelection();
    }

    private void deleteRoutine() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Routine ID: ");
        String routineId = scanner.nextLine();
        Routine routine = routineService.getRoutineById(routineId);
        if (routine != null) {
            routineService.deleteRoutineById(routineId);
            System.out.println("Deleted routine '" + routine.getName() + "'.");
        } else {
            System.out.println("Routine not found with ID: " + routineId);
        }
    }

    private void viewRoutines() {
        Scanner scanner = new Scanner(System.in);
        List<Routine> routines = routineService.getRoutines();
        if (!routines.isEmpty()) {
            for (Routine routine : routines) {
                System.out.println(routine.getId() + "\t\t" + routine.getName());
            }
            String choice = scanner.nextLine();
            MenuManager.displayMenu(new RoutineMenu());
        } else {
            System.out.println("You don't have any routines yet.");
        }
    }

    @Override
    public void handleSelection() {
        options.forEach(System.out::println);

        boolean selectionMade = false;

        Scanner scanner = new Scanner(System.in);

        while (!selectionMade) {
            String choice = scanner.nextLine();
            System.out.print("> ");

            if (choice.equals("1")) {
                System.out.print("Routine Name: ");
                choice = scanner.nextLine();
                Routine routine = Routine.builder()
                        .name(choice)
                        .build();
                MenuManager.displayMenu(new AddRoutineMenu(routine));
            }

            if (choice.equals("2")) {
                viewRoutines();
            }

            if (choice.equals("3")) {
                deleteRoutine();
            }

            if (choice.equals("4")) {
                selectionMade = true;
                MenuManager.displayMenu(new MainMenu());
            }
        }
    }
}
