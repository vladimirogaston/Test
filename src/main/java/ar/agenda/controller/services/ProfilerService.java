package ar.agenda.controller.services;

import java.io.IOException;
import java.util.Properties;

public interface ProfilerService {

    Properties getDatabaseConfigurationProperties() throws IOException;

    String getActiveProfile() throws IOException;

    Properties readProperties(String file) throws IOException;
}
