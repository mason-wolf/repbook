package us.wolfden.workoutapp.services;

import us.wolfden.workoutapp.models.Routine;
import us.wolfden.workoutapp.repositories.RoutineRepository;
import java.util.List;

public class RoutineServiceImpl implements RoutineService {

    private final RoutineRepository routineRepository;

    public RoutineServiceImpl() {
        this.routineRepository = new RoutineRepository();
    }

    @Override
    public void saveRoutines(List<Routine> routines) {
        routineRepository.save(routines);
    }

    @Override
    public void saveRoutine(Routine routine) {
        routineRepository.save(routine);
    }

    @Override
    public List<Routine> getRoutines() {
        return routineRepository.findAll();
    }

    @Override
    public Routine getRoutineById(String routineId) {
        return routineRepository.findById(routineId);
    }

    @Override
    public boolean deleteRoutineById(String routineId) {
        return routineRepository.deleteById(routineId);
    }
}
