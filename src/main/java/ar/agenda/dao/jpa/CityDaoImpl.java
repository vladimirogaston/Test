package ar.agenda.dao.jpa;

import ar.agenda.dao.CityDao;
import ar.agenda.dao.entities.City;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.Optional;

public class CityDaoImpl extends GenericJpaDao<City> implements CityDao {

    public CityDaoImpl() {
        super();
    }

    @Override
    public Optional<City> findByName(String name) {
        Query query = em.createQuery("SELECT c FROM City c WHERE c.name LIKE :name");
        query.setParameter("name", name);
        Optional ret = Optional.empty();
        try {
            City city = (City) query.getSingleResult();
            ret = Optional.ofNullable(city);
        } catch (NoResultException e) {}
        return ret;
    }
}
