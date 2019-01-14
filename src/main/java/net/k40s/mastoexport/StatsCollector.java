package net.k40s.mastoexport;

import io.prometheus.client.Gauge;
import net.k40s.mastoexport.api.CustomEmoji;
import net.k40s.mastoexport.api.instance.Instance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class StatsCollector {

    private static Logger log = LoggerFactory.getLogger(StatsCollector.class);

    private static final Gauge domainCount = new Gauge.Builder()
            .name("masto_domain_count")
            .help("Amount of known domains")
            .labelNames("instance_uri")
            .create().register();

    private static final Gauge statusCount = new Gauge.Builder()
            .name("masto_status_count")
            .help("Amount of statuses on this instance")
            .labelNames("instance_uri")
            .create().register();

    private static final Gauge userCount = new Gauge.Builder()
            .name("masto_user_count")
            .help("Amount of users on this instance")
            .labelNames("instance_uri")
            .create().register();

    private static final Gauge emojiCount = new Gauge.Builder()
            .name("masto_emoji_count")
            .help("Amount of custom emoji on this instance")
            .labelNames("instance_uri")
            .create().register();

    public static void setLatest(Instance instance, ArrayList<CustomEmoji> customEmojis) {
        log.info("Updated '" + instance.getUri() + "'");
        domainCount.labels(instance.getUri()).set(instance.getStats().getDomainCount());
        statusCount.labels(instance.getUri()).set(instance.getStats().getStatusCount());
        userCount.labels(instance.getUri()).set(instance.getStats().getStatusCount());
        emojiCount.labels(instance.getUri()).set(customEmojis.size());
    }
}
