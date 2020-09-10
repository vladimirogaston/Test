package ar.agenda.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T, K> {

    Optional<T> save(T entity);

    Optional<T> update(T entity);
    
    void delete(K id);

    Optional<T> read(K id);

    List<T> findAll();
}
