package us.wolfden.workoutapp.repositories;

import us.wolfden.workoutapp.models.Routine;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class RoutineFileRepositoryImpl extends FileRepositoryImpl<Routine, String> {

    public RoutineFileRepositoryImpl() {
        super("routines.json", new TypeReference<List<Routine>>() {
        });
    }

    @Override
    protected String getId(Routine routine) {
        return routine.getId();
    }
}
