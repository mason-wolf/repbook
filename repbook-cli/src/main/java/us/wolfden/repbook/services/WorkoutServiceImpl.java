package us.wolfden.repbook.services;

import us.wolfden.repbook.models.Workout;
import us.wolfden.repbook.repositories.WorkoutDbRepositoryImpl;

import java.util.List;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

public class WorkoutServiceImpl implements WorkoutService {

    private final WorkoutDbRepositoryImpl workoutDbRepository;

    public WorkoutServiceImpl() {
        this.workoutDbRepository = new WorkoutDbRepositoryImpl();
    }

    @Override
    public void saveWorkouts(List<Workout> workouts) {
        for (Workout workout : workouts) {
            workoutDbRepository.create(workout);
        }
    }

    @Override
    public void saveWorkout(Workout workout) {
        workoutDbRepository.create(workout);
    }

    @Override
    public List<Workout> getWorkouts() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");

        return workoutDbRepository.readAll().stream()
                .sorted(Comparator.comparing(workout -> LocalDate.parse(workout.getDate(), formatter)))
                .collect(Collectors.toList());

    }

    @Override
    public Workout getWorkoutById(String workoutId) {
        return null;
    }

    @Override
    public boolean deleteWorkoutById(String workoutId) {
        return false;
    }

    public Optional<Workout> getWorkoutById(int workoutId) {
        return workoutDbRepository.read(workoutId);
    }

    @Override
    public boolean deleteWorkoutById(int workoutId) {
        return workoutDbRepository.delete(workoutId);
    }
}
