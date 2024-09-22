package us.wolfden.repbook.menus;

import us.wolfden.repbook.models.Exercise;
import us.wolfden.repbook.models.Cardio;
import us.wolfden.repbook.models.Workout;
import us.wolfden.repbook.services.WorkoutService;
import us.wolfden.repbook.services.WorkoutServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * * Represents views for managing workouts.
 */
public class ViewWorkoutsMenu implements Menu {

    private final List<String> options = new ArrayList<>();

    private final WorkoutService workoutService;

    public ViewWorkoutsMenu() {
        options.add("1. View Workout");
        options.add("2. Delete Workout");
        options.add("3. Back");
        workoutService = new WorkoutServiceImpl();
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

    @Override
    public void handleSelection() {
        List<Workout> workouts = workoutService.getWorkouts();

        for (Workout workout : workouts) {
            System.out.println(workout.getId() + "\t\t" + workout.getDate() + "\t\t" + workout.getName());
        }

        options.forEach(System.out::println);

        boolean selectionMade = false;

        Scanner scanner = new Scanner(System.in);

        while (!selectionMade) {
            String choice = scanner.nextLine();
            System.out.print("> ");

            if (choice.equals("1")) {
                System.out.print("Workout ID: ");
                choice = scanner.nextLine();
                System.out.println("Searching for " + choice);
                viewWorkout(choice);
            }

            if (choice.equals("2")) {
                System.out.print("Workout ID: ");
                choice = scanner.nextLine();
                boolean workoutDeleted = workoutService.deleteWorkoutById(choice);
                if (workoutDeleted) {
                    System.out.println("Workout " + choice + " deleted.");
                    display();
                }
            }
            if (choice.equals("3")) {
                MenuManager.displayMenu(new MainMenu());
                selectionMade = true;
            }
        }
    }

    private void viewWorkout(String workoutId) {
        Workout workout = workoutService.getWorkoutById(workoutId);
        int totalExercises = 0;
        int totalSets = 0;
        int totalReps = 0;

        // TODO: Separate logic into smaller pieces.
        if (workout != null) {
            System.out.println();
            System.out.println("Workout: " + workout.getName());
            System.out.println("Date: " + workout.getDate());

            if (workout.getCardio() != null) {
                System.out.println("\nCardio:");
                for (Cardio cardio : workout.getCardio()) {
                    System.out.println();
                    System.out.println("Distance: " + cardio.getDistance());
                    System.out.println("Time: " + cardio.getTime());
                    System.out.println();
                }

            }
            // TODO: Check if exercises exist before printing.
            for (Exercise exercise : workout.getExercises()) {
                System.out.println();
                System.out.println(exercise.getName());
                System.out.println("Sets: " + exercise.getSets());
                System.out.println("Reps: " + exercise.getReps());
                totalExercises += 1;
                totalSets += exercise.getSets();
                totalReps += exercise.getReps();
            }
            System.out.println("Total Exercises: " + totalExercises);
            System.out.println("Total Sets: " + totalSets);
            System.out.println("Total Reps:" + totalReps);

        } else {
            System.out.println("Workout not found for '" + workoutId + "'.");
        }
    }
}