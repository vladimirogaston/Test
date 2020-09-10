package ar.agenda.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T, ID> {

    Optional<T> save(T entity);

    Optional<T> update(T entity);
    
    void delete(ID id);

    Optional<T> read(ID id);

    List<T> findAll();
}
