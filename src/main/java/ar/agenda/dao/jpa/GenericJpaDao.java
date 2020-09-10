package ar.agenda.dao.jpa;

import ar.agenda.dao.GenericDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

public abstract class GenericJpaDao<T> implements GenericDao<T, Integer> {

	EntityManager em;
	Class<T> type;

	public GenericJpaDao() {
		em = JpaUtils.getInstance().getEntityManager();
		Type t = getClass().getGenericSuperclass();
	    ParameterizedType pt = (ParameterizedType) t;
	    type = (Class<T>) pt.getActualTypeArguments()[0];
	}

	@Override
	public Optional<T> save(T entity) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(entity);
		em.flush();
		em.refresh(entity);
		tx.commit();
		return Optional.ofNullable(entity);
	}

	@Override
	public Optional<T> update(T entity) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.merge(entity);
		tx.commit();
		return Optional.ofNullable(entity);
	}

	@Override
	public void delete(Integer id) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		T target = read(id).get();
		em.remove(target);
		em.flush();
		tx.commit();
	}

	@Override
	public Optional<T> read(Integer id) {
		T entity = em.find(type, id);
		return Optional.ofNullable(entity);
	}

	@Override
	public List<T> findAll() {
		List<T> entities;
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<T> cq = builder.createQuery(type);
		Root<T> root = cq.from(type);
		cq.select(root);
		entities = em.createQuery(cq).getResultList();
		return entities;
	}
}