package us.wolfden.workoutapp.menus;
import us.wolfden.workoutapp.models.Exercise;
import java.util.Scanner;

/***
 * Provides commonly shared logic associated with managing exercises.
 * May be used with other views to include workout and routine management.
 */
public class ExerciseManager extends AbstractExerciseManager {
    public Exercise addExercise() {
           Scanner scanner = new Scanner(System.in);
                System.out.print("Exercise Name: ");
                String exerciseName = scanner.nextLine();
                System.out.print("Sets: ");
                String sets = scanner.nextLine();
                System.out.print("Reps: ");
                String reps = scanner.nextLine();
                System.out.print("Weight: ");
                String weight = scanner.nextLine();
                Exercise exercise = Exercise.builder()
                                    .name(exerciseName)
                                    .sets(Integer.parseInt(sets))
                                    .reps(Integer.parseInt(reps))
                                    .weight(Double.parseDouble(weight))
                                    .build();
                System.out.printf("Added exercise: %s%n", exerciseName);
            return exercise;
    }
}