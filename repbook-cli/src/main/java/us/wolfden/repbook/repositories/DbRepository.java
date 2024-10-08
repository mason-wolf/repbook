package us.wolfden.repbook.repositories;

import java.util.List;
import java.util.Optional;

public interface DbRepository<T> {
    void create(T entity);
    Optional<T> read(int id);
    List<T> readAll();
    void update(T entity);
    boolean delete(int id);
}
