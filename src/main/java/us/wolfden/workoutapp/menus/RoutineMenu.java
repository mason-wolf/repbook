package us.wolfden.workoutapp.menus;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import us.wolfden.workoutapp.models.Routine;

/***
 * Represents routine management view.
 */
public class RoutineMenu implements Menu {
    
    private final List<String> options = new ArrayList<>();
    
    public RoutineMenu() {
        options.add("1. New Routine");
        options.add("2. View Routines");
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
        options.forEach(System.out::println);
        
        boolean selectionMade = false;
        
        Scanner scanner = new Scanner(System.in);

        while(!selectionMade) {
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
            
            if (choice.equals("3")) {
                selectionMade = true;
                MenuManager.displayMenu(new MainMenu());
            }
        }
    }
}
