package ar.agenda.dao.jpa;

import ar.agenda.dao.UserDao;
import ar.agenda.dao.entities.User;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.Optional;

public class UserDaoImpl extends GenericJpaDao<User> implements UserDao {
	
	public UserDaoImpl() {
		super();
	}

	@Override
	public Optional<User> findByUsername(String username) {
		Query query = em.createQuery("SELECT u FROM User u WHERE u.account.username LIKE :username");
		query.setParameter("username", username);
		try {
			return Optional.ofNullable((User) query.getSingleResult());
		}catch (NoResultException e) {
			return Optional.empty();
		}
	}
}