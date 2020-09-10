package ar.agenda.controller.services;

import ar.agenda.dao.CityDao;
import ar.agenda.dao.UserDao;
import ar.agenda.dao.entities.City;
import ar.agenda.dao.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.InputStream;

@Slf4j
public class DatabaseSeederServiceImpl implements DatabaseSeeder {

    @Inject
    @Named("DB_SEED_FILE")
    static final String ymlFileName = "db.yml";

    UserDao userDao;
    CityDao cityDao;

    @Inject
    public DatabaseSeederServiceImpl(UserDao userDao, CityDao cityDao) {
        this.userDao = userDao;
        this.cityDao = cityDao;
    }

    @Override
    public void seedDatabase() {
        DatabaseGraph graph = loadDatabaseGraph();
        for (User user : graph.getUserList()) {
            userDao.save(user);
            log.info("Seed database >>>>>> " + user.toString());
        }
        for(City city: graph.getCityList()) {
            cityDao.save(city);
            log.info("Seed database >>>>>> " + city.toString());
        }
    }

    DatabaseGraph loadDatabaseGraph() {
        DatabaseGraph graph = null;
        try {
            log.info("Seed database operation status: [INITIALIZED - LOADING .YML]");
            Yaml yaml = new Yaml(new Constructor(DatabaseGraph.class));
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(ymlFileName);
            graph = yaml.load(inputStream);
        }catch (Exception e) {
            log.error("Seed database operation status: [ABORT - ERROR LOADING .YML, " + e.getMessage() +"]");
        }
        return graph;
    }
}
