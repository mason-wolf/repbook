package us.wolfden.repbook.repositories;

import us.wolfden.repbook.models.Workout;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class WorkoutFileRepositoryImpl extends FileRepositoryImpl<Workout, String> {

    public WorkoutFileRepositoryImpl() {
        super("workouts.json", new TypeReference<List<Workout>>() {
        });
    }

    @Override
    protected String getId(Workout workout) {
        return workout.getId();
    }
}
