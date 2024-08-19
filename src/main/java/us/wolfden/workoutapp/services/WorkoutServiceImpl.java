package us.wolfden.workoutapp.services;

import us.wolfden.workoutapp.models.Workout;
import us.wolfden.workoutapp.repositories.WorkoutRepository;
import java.util.List;

public class WorkoutServiceImpl implements WorkoutService {

    private final WorkoutRepository workoutRepository;

    public WorkoutServiceImpl() {
        this.workoutRepository = new WorkoutRepository();
    }

    @Override
    public void saveWorkouts(List<Workout> workouts) {
        workoutRepository.save(workouts);
    }

    @Override
    public void saveWorkout(Workout workout) {
        workoutRepository.save(workout);
    }

    @Override
    public List<Workout> getWorkouts() {
        return workoutRepository.findAll();
    }

    @Override
    public Workout getWorkoutById(String workoutId) {
        return workoutRepository.findById(workoutId);
    }

    @Override
    public boolean deleteWorkoutById(String workoutId) {
        return workoutRepository.deleteById(workoutId);
    }
}
