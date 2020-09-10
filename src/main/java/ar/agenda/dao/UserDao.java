package ar.agenda.dao;

import ar.agenda.dao.entities.User;

import java.util.Optional;

public interface UserDao extends GenericDao<User, Integer> {

    Optional<User> findByUsername(String username);
}
