package ar.agenda.dao.jpa;

import ar.agenda.controller.services.DatabaseSeederServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class UserDaoImplTest {

    UserDaoImpl dao = new UserDaoImpl();

    @BeforeEach
    void setUp() {
        new DatabaseSeederServiceImpl(dao, new CityDaoImpl()).seedDatabase();
    }

    @Test
    void findAll() {
        assertTrue(!dao.findAll().isEmpty());
    }
}