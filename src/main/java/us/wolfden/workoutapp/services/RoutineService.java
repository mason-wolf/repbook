package us.wolfden.workoutapp.services;

import us.wolfden.workoutapp.models.Routine;

import java.util.List;

public interface RoutineService {

    void saveRoutines(List<Routine> routines);

    void saveRoutine(Routine routine);

    List<Routine> getRoutines();

    Routine getRoutineById(String routineId);

    boolean deleteRoutineById(String routineId);
}
