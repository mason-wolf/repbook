package us.wolfden.workoutapp.menus;

import us.wolfden.workoutapp.models.Workout;
import us.wolfden.workoutapp.models.Routine;
import us.wolfden.workoutapp.models.Exercise;

import us.wolfden.workoutapp.services.WorkoutService;
import us.wolfden.workoutapp.services.WorkoutServiceImpl;
import us.wolfden.workoutapp.services.RoutineService;
import us.wolfden.workoutapp.services.RoutineServiceImpl;
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
        System.out.print("Type: ");
        String workoutType = scanner.nextLine();
        this.workout.setName(workoutName);
        this.workout.setDate(workoutDate);
        this.workout.setType(workoutType);
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
                showExercises();
                System.out.println();
                System.out.println("Log this workout? (y/n)");  
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
        }
        else {
            for (Exercise exercise : routine.getExercises()) {
                workout.addExercise(exercise);
            }
            showExercises();
            System.out.println("Routine '" + routine.getName() + "' added.");
        }
    }
    
    @Override
    public void handleSelection() {
        newWorkout();
        
        boolean selectionMade = false;
        
        Scanner scanner = new Scanner(System.in);

        while(!selectionMade) {
            String choice = scanner.nextLine();
            System.out.print("> ");
            
            if (choice.equals("1")) {
                addExercise();
            }
            
            if (choice.equals("2")) {
                addRoutine();
            }
            if (choice.equals("3")) {
                confirmSaveWorkout();
            }
            
            if (choice.equals("4")) {
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
