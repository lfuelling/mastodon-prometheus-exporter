package net.k40s.mastoexport;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.common.TextFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.InetSocketAddress;
import java.util.Collections;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        startServer();
        startFetchJob();
    }

    private static void startServer() throws IOException {
        log.info("Starting server...");
        HttpServer server = HttpServer.create(new InetSocketAddress(Config.get().getHost(), Config.get().getPort()), 0);
        server.createContext("/metrics", new MetricsHandler());
        server.setExecutor(java.util.concurrent.Executors.newCachedThreadPool());
        server.start();
        log.info("Server started!");
    }

    private static void startFetchJob() {
        log.info("Starting fetch job...");
        Thread fetchThread = new Thread(new FetchJob());
        fetchThread.start();
        log.info("Fetch job started!");
    }

    private static class MetricsHandler implements HttpHandler {

        public void handle(HttpExchange t) throws IOException {
            log.info("/metrics - " + t.getRemoteAddress() + " - " + Thread.currentThread().getId());
            String response = getCurrentMetrics();
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }

        private String getCurrentMetrics() throws IOException {
            StringWriter writer = new StringWriter();
            TextFormat.write004(writer, CollectorRegistry.defaultRegistry.filteredMetricFamilySamples(Collections.emptySet()));
            return writer.toString();
        }
    }
}
