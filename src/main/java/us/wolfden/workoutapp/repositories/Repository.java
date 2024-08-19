package us.wolfden.workoutapp.repositories;

import java.util.List;

public interface Repository<T, ID> {
    void save(List<T> items);
    void save(T item);
    List<T> findAll();
    T findById(ID id);
    boolean deleteById(ID id);
}