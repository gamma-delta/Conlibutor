package at.petrak.conlibutor.api;

import at.petrak.conlibutor.Contributors;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class ConlibutorAPI {
    public static Logger LOGGER = LoggerFactory.getLogger("conlibutor");

    public static final String CONTRIBUTOR_URL = "https://raw.githubusercontent.com/gamma-delta/contributors/main/paucal.toml";

    public static void loadContributors() {
        loadContributors(CONTRIBUTOR_URL);
    }

    public static void loadContributors(String url) {
        Contributors.loadContributors(url);
    }

    @Nullable
    public static Contributor getContributor(UUID uuid) {
        return Contributors.CONTRIBUTORS.get(uuid);
    }
}
