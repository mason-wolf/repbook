package us.wolfden.workoutapp.repositories;

import us.wolfden.workoutapp.models.Workout;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;
import java.util.UUID;

public class WorkoutRepository extends FileRepository<Workout, String> {

    public WorkoutRepository() {
        super("workouts.json", new TypeReference<List<Workout>>() {
        });
    }

    @Override
    protected String getId(Workout workout) {
        return workout.getId();
    }
}
