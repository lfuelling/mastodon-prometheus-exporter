package net.k40s.mastoexport;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import net.k40s.mastoexport.api.CustomEmoji;
import net.k40s.mastoexport.api.instance.Instance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class FetchJob implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(FetchJob.class);

    private boolean shouldStop = false;

    @Override
    public void run() {
        while(!shouldStop) {
            try {
                Instance instance = fetchInstanceStats();
                ArrayList<CustomEmoji> customEmojis = fetchInstanceEmojis();
                if(instance != null) {
                    StatsCollector.setLatest(instance, customEmojis);
                } else {
                    log.warn("No instance object returned! Skipping...");
                }
                Thread.sleep(Config.get().getInterval() * 1000);
            } catch (InterruptedException e) {
                log.info("Thread interrupted!", e);
            }
        }
    }

    public void setShouldStop(boolean shouldStop) {
        this.shouldStop = shouldStop;
    }

    private Instance fetchInstanceStats() {
        try {
            URL url = new URL(Config.get().getTarget() + "/api/v1/instance");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            return new ObjectMapper().readValue(con.getInputStream(), Instance.class);
        } catch (MalformedURLException e) {
            log.error("Unable to parse URL!", e);
        } catch (IOException e) {
            log.error("Unable to execute request!", e);
        }
        return null;
    }

    private ArrayList<CustomEmoji> fetchInstanceEmojis() {
        try {
            URL url = new URL(Config.get().getTarget() + "/api/v1/custom_emojis");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            ObjectMapper objectMapper = new ObjectMapper();
            TypeFactory typeFactory = objectMapper.getTypeFactory();

            return objectMapper.readValue(con.getInputStream(),
                    typeFactory.constructCollectionType(ArrayList.class, CustomEmoji.class));
        } catch (MalformedURLException e) {
            log.error("Unable to parse URL!", e);
        } catch (IOException e) {
            log.error("Unable to execute request!", e);
        }
        return new ArrayList<>();
    }
}
