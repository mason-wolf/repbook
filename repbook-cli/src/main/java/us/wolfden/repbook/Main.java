package us.wolfden.repbook;


import java.util.List;
import java.util.Optional;

import us.wolfden.repbook.models.Workout;
import us.wolfden.repbook.models.Exercise;
import us.wolfden.repbook.repositories.WorkoutDbRepositoryImpl;
import us.wolfden.repbook.repositories.ExerciseDbRepositoryImpl;
import us.wolfden.repbook.services.WorkoutServiceImpl;

public class Main {
    public static void main(String[] args) {
        
        // WorkoutDbRepositoryImpl workoutDb = new WorkoutDbRepositoryImpl();
        // Workout workout = new Workout();
        // workout.setName("cool ass workout 2.0");
        // Exercise exercise = new Exercise();
        // exercise.setName("cool ass exercise");
        // workout.addExercise(exercise);
        // workoutDb.create(workout);
        
        ExerciseDbRepositoryImpl exerciseDb = new ExerciseDbRepositoryImpl();
        List<Exercise> exercises = exerciseDb.getExercisesByWorkoutId(26);
        for (Exercise exercise2 : exercises) {
            System.out.println(exercise2.getName());
        }
    }
}