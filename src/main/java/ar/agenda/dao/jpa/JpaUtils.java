package ar.agenda.dao.jpa;

import ar.agenda.controller.services.ProfilerServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;

@Slf4j
public class JpaUtils {

    EntityManager em;
    static JpaUtils instance;

    JpaUtils() {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence", new ProfilerServiceImpl().getDatabaseConfigurationProperties());
            em = emf.createEntityManager();
        }catch(RuntimeException e) {
            throw e;
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public static JpaUtils getInstance() {
        if(instance == null) {
            instance = new JpaUtils();
        }
        return instance;
    }

    public EntityManager getEntityManager() {
        return this.em;
    }
}
