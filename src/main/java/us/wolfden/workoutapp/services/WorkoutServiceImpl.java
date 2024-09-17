package us.wolfden.workoutapp.services;

import us.wolfden.workoutapp.models.Workout;
import us.wolfden.workoutapp.repositories.WorkoutFileRepositoryImpl;

import java.util.List;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.stream.Collectors;

public class WorkoutServiceImpl implements WorkoutService {

    private final WorkoutFileRepositoryImpl workoutFileRepository;

    public WorkoutServiceImpl() {
        this.workoutFileRepository = new WorkoutFileRepositoryImpl();
    }

    @Override
    public void saveWorkouts(List<Workout> workouts) {
        workoutFileRepository.save(workouts);
    }

    @Override
    public void saveWorkout(Workout workout) {
        workoutFileRepository.save(workout);
    }

    @Override
    public List<Workout> getWorkouts() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");

        return workoutFileRepository.findAll().stream()
                .sorted(Comparator.comparing(workout -> LocalDate.parse(workout.getDate(), formatter)))
                .collect(Collectors.toList());

    }

    @Override
    public Workout getWorkoutById(String workoutId) {
        return workoutFileRepository.findById(workoutId);
    }

    @Override
    public boolean deleteWorkoutById(String workoutId) {
        return workoutFileRepository.deleteById(workoutId);
    }
}
