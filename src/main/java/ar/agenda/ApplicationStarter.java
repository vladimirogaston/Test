package ar.agenda;

import ar.agenda.controller.services.DatabaseSeeder;
import ar.agenda.controller.services.ProfilerService;
import ar.agenda.presentation.WorkbenchPresenter;
import com.google.inject.Guice;
import com.google.inject.Injector;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import sun.misc.Unsafe;

import javax.swing.*;
import java.io.IOException;
import java.lang.reflect.Field;

@Slf4j
public class ApplicationStarter {

    Injector injector;

    ApplicationStarter setLookAndFeel() {
        try {
            UIManager.setLookAndFeel ("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        return this;
    }

    ApplicationStarter enableSecurity() {
        ApplicationSecurity applicationSecurity = injector.getInstance(ApplicationSecurity.class);
        applicationSecurity.init();
        return this;
    }

    ApplicationStarter seedDatabase() {
        try {
            String profile = injector.getInstance(ProfilerService.class).getActiveProfile();
            if(profile.matches("dev")){
                injector.getInstance(DatabaseSeeder.class).seedDatabase();
                LoggerFactory.getLogger(this.getClass()).warn("Seed database operation status: [SUCCESS]");
            } else {
                log.warn("Seed database operation status: [FAILS]");
                log.warn("Seed database allowed only for 'dev' profile");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            System.exit(1);
        }
        return this;
    }

    ApplicationStarter showActiveProfile() {
        try {
            String profile = injector.getInstance(ProfilerService.class).getActiveProfile();
            log.info("Application active profile: " + profile);
        } catch (IOException e) {
            log.info("ApplicationProfiler.class error" + e.getMessage());
        }
        return this;
    }

    public void init() {
        injector.getInstance(WorkbenchPresenter.class).displayWorkbench();
    }

    public ApplicationStarter() {
        injector = Guice.createInjector(new ApplicationInjectionConfig(), new ApplicationAuthorizingModule());
    }

    public static void disableWarnings(){
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            Unsafe u = (Unsafe) theUnsafe.get(null);
            Class cls = Class.forName("jdk.internal.module.IllegalAccessLogger");
            Field logger = cls.getDeclaredField("logger");
            u.putObjectVolatile(cls, u.staticFieldOffset(logger), null);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}