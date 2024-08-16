package us.wolfden.workoutapp.services;
import us.wolfden.workoutapp.models.Workout;

import java.util.ArrayList;
import java.util.List;

public interface WorkoutService {
    Workout getWorkoutById(String workoutId);
    boolean deleteWorkoutById(String workoutId);
    void saveWorkout(Workout workout); 
    void saveWorkouts(List<Workout> workouts);
    ArrayList<Workout> getWorkouts();
}