package us.wolfden.workoutapp.menus;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import us.wolfden.workoutapp.models.Routine;
import us.wolfden.workoutapp.models.Exercise;
import us.wolfden.workoutapp.services.RoutineService;
import us.wolfden.workoutapp.services.RoutineServiceImpl;

/***
 * Represents routine management view. Allows
 * for adding and managing workout routines.
 */
public class AddRoutineMenu implements Menu {
    
    private final RoutineService routineService;
    private final ExerciseManager exerciseManager;
    private final Routine routine;
    private final List<String> options = new ArrayList<>();
    
    public AddRoutineMenu(Routine routine) {
        this.routineService = new RoutineServiceImpl();
        this.exerciseManager = new ExerciseManager();
        this.routine = routine;
        options.add("1. Add Exercise");
        options.add("2. Save Routine");
        options.add("3. Back");
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
        System.out.println("Routine: " + routine.getName());
        routine.setExercises(new ArrayList<Exercise>());
        options.forEach(System.out::println);
        
        boolean selectionMade = false;
        
        Scanner scanner = new Scanner(System.in);
        
        while(!selectionMade) {
            System.out.print("> ");
            String choice = scanner.nextLine();
            
            if (choice.equals("1")) {
                Exercise exercise = exerciseManager.addExercise();
                routine.getExercises().add(exercise);
            }
            
            if(choice.equals("2")) {
                if (routine.getExercises().size() > 0) {
                    reviewRoutine();   
                }
                else {
                    System.out.println("You have no exercises in this routine yet.");
                }
            }
            
            if (choice.equals("3")) {
                MenuManager.displayMenu(new MainMenu());
            }
            
            if (choice.equals("y")) {
                System.out.println("Saving routine.");
                routineService.saveRoutine(this.routine);
                MenuManager.displayMenu(new AddRoutineMenu(routine));
            }
            
            if (choice.equals("n")) {
                routine.setExercises(new ArrayList<Exercise>());
                MenuManager.displayMenu(new AddRoutineMenu(routine));
            }
        }
    }
    
    private void reviewRoutine() {
        MenuManager.clearMenu();
        for (Exercise exercise: routine.getExercises()) {
                routine.getExercises().forEach(e -> {
                    System.out.println();
                    System.out.println("Exercise: " + e.getName());
                    System.out.println("Sets: " + e.getSets());
                    System.out.println("Reps: " + e.getReps());
                    System.out.println();
                });
                System.out.println();
                System.out.println("Log this routine? (y/n)");              
        }
    }
}