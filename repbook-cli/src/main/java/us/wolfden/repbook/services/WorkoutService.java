package us.wolfden.repbook.services;

import us.wolfden.repbook.models.Workout;

import java.util.List;

public interface WorkoutService {

    void saveWorkouts(List<Workout> workouts);

    void saveWorkout(Workout workout);

    List<Workout> getWorkouts();

    Workout getWorkoutById(String workoutId);

    boolean deleteWorkoutById(String workoutId);
}
