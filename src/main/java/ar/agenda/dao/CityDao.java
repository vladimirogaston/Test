package ar.agenda.dao;

import ar.agenda.dao.entities.City;

import java.util.Optional;

public interface CityDao extends GenericDao<City, Integer> {

    Optional<City> findByName(String name);
}
