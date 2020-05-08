package gw;

import exceptions.EntityNotFound;
import models.EntityClass;

import java.util.List;

public interface Gateway<T extends EntityClass> {
    List<T> all();
    T find(Long id) throws EntityNotFound;
    void insert(T object);
    void update(T object) throws EntityNotFound;
    void delete(T object);
}
