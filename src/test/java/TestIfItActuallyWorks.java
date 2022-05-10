import at.petrak.conlibutor.Contributors;
import at.petrak.conlibutor.api.ConlibutorAPI;
import org.junit.jupiter.api.Test;

public class TestIfItActuallyWorks {
    @Test
    void fetchContributors() {
        var contributors = Contributors.fetch(ConlibutorAPI.CONTRIBUTOR_URL);
        ConlibutorAPI.LOGGER.info("Loaded {} contributors", contributors.size());
        contributors.forEach((k, v) ->
            ConlibutorAPI.LOGGER.info("{}: {}", k, v));
    }
}
