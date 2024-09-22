package us.wolfden.repbook.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class FileRepositoryImpl<T, ID> implements FileRepository<T, ID> {

    private final String filePath;
    private final ObjectMapper mapper;
    private final TypeReference<List<T>> typeReference;

    public FileRepositoryImpl(String filePath, TypeReference<List<T>> typeReference) {
        this.filePath = filePath;
        this.mapper = new ObjectMapper();
        this.typeReference = typeReference;
    }

    @Override
    public void save(List<T> items) {
        try {
            mapper.writeValue(new File(filePath), items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(T item) {
        List<T> items = findAll();
        items.add(item);
        save(items);
    }

    @Override
    public List<T> findAll() {
        File file = new File(filePath);
        List<T> items = new ArrayList<>();
        try {
            if (file.exists() && file.length() > 0) {
                items = mapper.readValue(file, typeReference);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public T findById(ID id) {
        List<T> items = findAll();
        return items.stream()
                .filter(item -> id.equals(getId(item)))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean deleteById(ID id) {
        List<T> items = findAll();
        boolean removed = items.removeIf(item -> id.equals(getId(item)));
        if (removed) {
            save(items);
        }
        return removed;
    }

    protected abstract ID getId(T item);
}
