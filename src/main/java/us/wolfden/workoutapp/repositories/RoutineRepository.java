package us.wolfden.workoutapp.repositories;

import us.wolfden.workoutapp.models.Routine;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;
import java.util.UUID;

public class RoutineRepository extends FileRepository<Routine, String> {

    public RoutineRepository() {
        super("routines.json", new TypeReference<List<Routine>>() {
        });
    }

    @Override
    protected String getId(Routine routine) {
        return routine.getId();
    }
}
