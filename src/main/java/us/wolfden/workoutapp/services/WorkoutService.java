package us.wolfden.workoutapp.services;

import us.wolfden.workoutapp.models.Workout;
import java.util.List;

public interface WorkoutService {

    void saveWorkouts(List<Workout> workouts);

    void saveWorkout(Workout workout);

    List<Workout> getWorkouts();

    Workout getWorkoutById(String workoutId);

    boolean deleteWorkoutById(String workoutId);
}
