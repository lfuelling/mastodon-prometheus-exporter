package net.k40s.mastoexport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

class Config {

    private static final Logger log = LoggerFactory.getLogger(Config.class);

    private static Config config = null;

    private int port = 9534;
    private String host = "localhost";
    private String target = "http://localhost:3000";
    private int interval = 5;

    private Config() {
        boolean loadSuccess = false;
        try (FileReader reader = new FileReader("exporter.properties")) {
            Properties p = new Properties();
            p.load(reader);
            loadSuccess = true;
            port = Integer.parseInt(p.getProperty("port"));
            host = p.getProperty("host");
            target = p.getProperty("target");
            interval = Integer.parseInt(p.getProperty("interval"));
        } catch (FileNotFoundException e) {
            log.error("Unable to find config!", e);
        } catch (IOException e) {
            log.error("Error loading config!", e);
        }
        if (!loadSuccess) {
            log.warn("Unable to load config file, using defaults...");
        }
    }

    static Config get() {
        if (config == null) {
            config = new Config();
        }
        return config;
    }

    int getPort() {
        return port;
    }

    String getHost() {
        return host;
    }

    String getTarget() {
        return target;
    }

    int getInterval() {
        return interval;
    }
}
