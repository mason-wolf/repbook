package us.wolfden.repbook.repositories;

import java.util.List;

public interface FileRepository<T, ID> {
    void save(List<T> items);

    void save(T item);

    List<T> findAll();

    T findById(ID id);

    boolean deleteById(ID id);
}