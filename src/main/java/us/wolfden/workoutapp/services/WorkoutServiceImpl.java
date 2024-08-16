package us.wolfden.workoutapp.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import us.wolfden.workoutapp.models.Workout;

public class WorkoutServiceImpl implements WorkoutService {
    
    private static final String FILE_PATH = "workouts.json";
    
    @Override
    public void saveWorkouts(List<Workout> workouts) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Write the updated list back to the JSON file
            mapper.writeValue(new File(FILE_PATH), workouts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void saveWorkout(Workout workout) {
        File file = new File("workouts.json");

        ObjectMapper mapper = new ObjectMapper();
        try {
            List<Workout> existingWorkouts;

            if (file.exists() && file.length() > 0) {
                existingWorkouts = mapper.readValue(file, new TypeReference<List<Workout>>(){});
            } else {
                existingWorkouts = new ArrayList<>();
            }

            existingWorkouts.add(workout);

            mapper.writeValue(file, existingWorkouts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Workout> getWorkouts() {
        File file = new File(FILE_PATH);
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Workout> workouts = new ArrayList<>();
        try {
            if (file.exists() && file.length() > 0) {
                workouts = mapper.readValue(file, new TypeReference<ArrayList<Workout>>() {});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workouts;
    }
    
    @Override
    public Workout getWorkoutById(String workoutId) {
        List<Workout> workouts = getWorkouts();
        Optional<Workout> workout = workouts.stream()
                                            .filter(w -> w.getId().equals(workoutId))
                                            .findFirst();
        return workout.orElse(null);
    }
    
    @Override
    public boolean deleteWorkoutById(String workoutId) {
        ArrayList<Workout> workouts = getWorkouts();
        boolean removed = workouts.removeIf(workout -> workout.getId().equals(workoutId));
        
        if (removed) {
            saveWorkouts(workouts);
        }
        return removed;
    }
}

