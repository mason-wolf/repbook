package us.wolfden.workoutapp.services;

import us.wolfden.workoutapp.models.Routine;
import us.wolfden.workoutapp.repositories.RoutineFileRepositoryImpl;

import java.util.List;

public class RoutineServiceImpl implements RoutineService {

    private final RoutineFileRepositoryImpl routineFileRepository;

    public RoutineServiceImpl() {
        this.routineFileRepository = new RoutineFileRepositoryImpl();
    }

    @Override
    public void saveRoutines(List<Routine> routines) {
        routineFileRepository.save(routines);
    }

    @Override
    public void saveRoutine(Routine routine) {
        routineFileRepository.save(routine);
    }

    @Override
    public List<Routine> getRoutines() {
        return routineFileRepository.findAll();
    }

    @Override
    public Routine getRoutineById(String routineId) {
        return routineFileRepository.findById(routineId);
    }

    @Override
    public boolean deleteRoutineById(String routineId) {
        return routineFileRepository.deleteById(routineId);
    }
}
