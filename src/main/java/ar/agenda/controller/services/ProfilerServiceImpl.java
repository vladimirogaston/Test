package ar.agenda.controller.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ProfilerServiceImpl implements ProfilerService {

    public ProfilerServiceImpl() {
        super();
    }

    @Override
    public Properties getDatabaseConfigurationProperties() throws IOException {
        return readProperties(getActiveProfile() + ".properties");
    }

    @Override
    public String getActiveProfile() throws IOException {
        String profile = readProperties("app.properties").getProperty("active.profile");
        if(profile == null || profile.isBlank()) profile = "dev";
        return profile;
    }

    @Override
    public Properties readProperties(String file) throws IOException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = loader.getResourceAsStream(file);
        Properties prp = new Properties();
        prp.load(inputStream);
        return prp;
    }
}
