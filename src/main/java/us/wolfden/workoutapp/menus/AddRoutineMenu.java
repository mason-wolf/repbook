package us.wolfden.workoutapp.menus;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import us.wolfden.workoutapp.models.Routine;
import us.wolfden.workoutapp.models.Exercise;

/***
 * Represents routine management view. Allows
 * for adding and managing workout routines.
 */
public class AddRoutineMenu implements Menu {
    
    private final ExerciseManager exerciseManager;
    private final Routine routine;
    private final List<String> options = new ArrayList<>();
    
    public AddRoutineMenu(Routine routine) {
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
        MenuManager.clear();
        handleSelection();
    }
    
    @Override
    public void handleSelection() {
        System.out.println("Routine: " + routine.getName());
        options.forEach(System.out::println);
        
        boolean selectionMade = false;
        
        Scanner scanner = new Scanner(System.in);

        while(!selectionMade) {
            String choice = scanner.nextLine();
            System.out.print("> ");
            
            if (choice.equals("1")) {
                Exercise exercise = exerciseManager.addExercise();
                routine.getExercises().add(exercise);
                selectionMade = true;
            }
        }
    }
}