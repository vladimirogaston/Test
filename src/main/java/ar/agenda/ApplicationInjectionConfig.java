package ar.agenda;

import ar.agenda.controller.services.DatabaseSeeder;
import ar.agenda.controller.services.DatabaseSeederServiceImpl;
import ar.agenda.controller.services.ProfilerService;
import ar.agenda.controller.services.ProfilerServiceImpl;
import ar.agenda.dao.CityDao;
import ar.agenda.dao.UserDao;
import ar.agenda.dao.jpa.CityDaoImpl;
import ar.agenda.dao.jpa.UserDaoImpl;
import ar.agenda.presentation.Profile;
import ar.agenda.presentation.Trace;
import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import com.google.inject.name.Names;
import org.apache.shiro.realm.AuthorizingRealm;

import javax.inject.Provider;

public class ApplicationInjectionConfig extends AbstractModule {

    @Override
    protected void configure() {
        bind(UserDao.class).to(UserDaoImpl.class);
        bind(CityDao.class).to(CityDaoImpl.class);
        bind(DatabaseSeeder.class).to(DatabaseSeederServiceImpl.class);
        bind(AuthorizingRealm.class).toProvider(AuthRealmProvider.class);
        bind(ProfilerService.class).toProvider(ProfilerServiceProvider.class);
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(Trace.class), new ApplicationTracer());
        bindApplicationProfiler();
        bind(String.class).annotatedWith(Names.named("DB_SEED_FILE")).toInstance("db.yml");
    }

    void bindApplicationProfiler() {
        ApplicationProfiler applicationProfiler = new ApplicationProfiler();
        requestInjection(applicationProfiler);
        bindInterceptor(Matchers.any(),
                Matchers.annotatedWith(Profile.class),
                applicationProfiler);
    }

    static class ProfilerServiceProvider implements Provider<ProfilerService> {
        @Override
        public ProfilerService get() {
            return new ProfilerServiceImpl();
        }
    }
}
