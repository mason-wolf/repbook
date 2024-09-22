package us.wolfden.repbook.menus;

import us.wolfden.repbook.models.Workout;
import us.wolfden.repbook.models.Routine;
import us.wolfden.repbook.models.Cardio;
import us.wolfden.repbook.models.Exercise;

import us.wolfden.repbook.services.WorkoutService;
import us.wolfden.repbook.services.WorkoutServiceImpl;
import us.wolfden.repbook.services.RoutineService;
import us.wolfden.repbook.services.RoutineServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/***
 * Represents workout management view. Allows for
 * adding exercises and workout routines.
 */
public class AddWorkoutMenu implements Menu {

    private final List<String> options = new ArrayList<>();
    private Workout workout;
    private final WorkoutService workoutService;
    private final RoutineService routineService;
    private final ExerciseManager exerciseManager;

    public AddWorkoutMenu() {
        routineService = new RoutineServiceImpl();
        workoutService = new WorkoutServiceImpl();
        exerciseManager = new ExerciseManager();
        options.add("1. Add Exercise");
        options.add("2. Add Cardio");
        options.add("3. Add Routine");
        options.add("4. Save");
        options.add("5. Back");
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

    /**
     * Gathers info for adding a new workout and displays
     * options for management it.
     */
    private void newWorkout() {
        workout = Workout.builder().build();
        System.out.println();
        System.out.print("Workout Name: ");
        Scanner scanner = new Scanner(System.in);
        String workoutName = scanner.nextLine();
        System.out.print("Date: ");
        String workoutDate = scanner.nextLine();
        this.workout.setName(workoutName);
        this.workout.setDate(workoutDate);
        options.forEach(System.out::println);
    }

    private void addExercise() {
        Exercise exercise = exerciseManager.addExercise();
        workout.addExercise(exercise);
    }

    private void confirmSaveWorkout() {
        MenuManager.clearMenu();
        System.out.println("Workout: " + workout.getName());
        System.out.println("Date: " + workout.getDate());
        showCardio();
        showExercises();
        System.out.println();
        System.out.println("Log this workout? (y/n)");
    }

    private void showCardio() {
        workout.getCardio().forEach(c -> {
            System.out.println();
            System.out.println("Cardio Name: " + c.getName());
            System.out.println("Distance: " + c.getDistance());
            System.out.println("Time: " + c.getTime());
            System.out.println();
        });
    }

    private void showExercises() {
        workout.getExercises().forEach(e -> {
            System.out.println();
            System.out.println("Exercise: " + e.getName());
            System.out.println("Sets: " + e.getSets());
            System.out.println("Reps: " + e.getReps());
            System.out.println();
        });
    }

    private void addRoutine() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Routine ID: ");
        String routineId = scanner.nextLine();
        Routine routine = routineService.getRoutineById(routineId);
        if (routine == null) {
            System.out.println("Routine with ID " + routineId + " not found.");
        } else {
            for (Exercise exercise : routine.getExercises()) {
                workout.addExercise(exercise);
            }
            showExercises();
            System.out.println("Routine '" + routine.getName() + "' added.");
        }
    }

    private void addCardio() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Distance: ");
        String distance = scanner.nextLine();
        System.out.print("Time: ");
        String time = scanner.nextLine();
        Cardio cardio = Cardio.builder()
                .name(name)
                .distance(Double.parseDouble(distance))
                .time(time)
                .build();
        System.out.println("Added cardio '" + cardio.getName() + "'.");
        workout.addCardio(cardio);
    }

    @Override
    public void handleSelection() {
        newWorkout();

        boolean selectionMade = false;

        Scanner scanner = new Scanner(System.in);

        while (!selectionMade) {
            String choice = scanner.nextLine();
            System.out.print("> ");

            if (choice.equals("1")) {
                addExercise();
            }

            if (choice.equals("2")) {
                addCardio();
            }

            if (choice.equals("3")) {
                addRoutine();
            }

            if (choice.equals("4")) {
                confirmSaveWorkout();
            }

            if (choice.equals("5")) {
                MenuManager.displayMenu(new MainMenu());
                selectionMade = true;
            }

            if (choice.equals("y")) {
                System.out.println("Saving workout '" + workout.getName() + "'.");
                workoutService.saveWorkout(workout);
                MenuManager.displayMenu(new MainMenu());
                selectionMade = true;
            }

            if (choice.equals("n")) {
                MenuManager.displayMenu(new MainMenu());
                selectionMade = true;
            }
        }

    }
}
