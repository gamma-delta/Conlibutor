package at.petrak.conlibutor;

import at.petrak.conlibutor.api.ConlibutorAPI;
import at.petrak.conlibutor.api.Contributor;
import com.electronwill.nightconfig.core.AbstractConfig;
import com.electronwill.nightconfig.core.UnmodifiableCommentedConfig;
import com.electronwill.nightconfig.toml.TomlParser;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Contributors {
    public static Map<UUID, Contributor> CONTRIBUTORS = new HashMap<>();
    public static boolean startedLoading = false;


    public static void loadContributors(String url) {
        if (startedLoading) {
            ConlibutorAPI.LOGGER.warn("Tried to load contributors after already starting");
            return;
        }
        startedLoading = true;

        var thread = new Thread(() -> fetchAndPopulate(url));
        thread.setName("Conlibutor Loading Thread");
        thread.setDaemon(true);
        thread.setUncaughtExceptionHandler(new DefaultUncaughtExceptionHandler(ConlibutorAPI.LOGGER));
        thread.start();
    }

    private static void fetchAndPopulate(String url) {
        CONTRIBUTORS = fetch(url);
    }

    public static Map<UUID, Contributor> fetch(String urlStr) {
        UnmodifiableCommentedConfig config;
        try {
            var url = new URL(urlStr);
            config = new TomlParser().parse(url).unmodifiable();
        } catch (IOException exn) {
            ConlibutorAPI.LOGGER.warn("Couldn't load contributors: {}", exn.getMessage());
            ConlibutorAPI.LOGGER.warn("Oh well :(");
            return Map.of();
        }

        var out = new HashMap<UUID, Contributor>();

        var keys = config.valueMap().keySet();
        for (var key : keys) {
            try {
                AbstractConfig rawEntry = config.get(key);
                UUID uuid = UUID.fromString(key);
                var contributor = new Contributor(uuid, rawEntry);
                out.put(uuid, contributor);
            } catch (Exception exn) {
                ConlibutorAPI.LOGGER.warn("Exception when loading contributor '{}': {}",
                    key,
                    exn.getMessage());
                // and try again with the next one
            }
        }

        return out;
    }
}
