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
        try {
            Query query = em.createQuery("SELECT c FROM City c WHERE c.name LIKE :name");
            query.setParameter("name", name);
            return Optional.ofNullable((City) query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
