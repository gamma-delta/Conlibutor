package at.petrak.conlibutor.api;

import com.electronwill.nightconfig.core.AbstractConfig;

import java.util.UUID;

public class Contributor {
    private final UUID uuid;
    private final ContributorType contributorType;


    private final AbstractConfig otherVals;

    public Contributor(UUID uuid, AbstractConfig otherVals) {
        this.uuid = uuid;
        this.otherVals = otherVals;

        // IF it's well-formed they should never miss this
        var level = this.otherVals.getIntOrElse("paucal:contributorLevel", 0);
        var isDev = this.otherVals.getOrElse("paucal:isDev", false);
        var isCool = this.otherVals.getOrElse("paucal:isCool", false);
        this.contributorType = new ContributorType(level, isDev, isCool);
    }

    public ContributorType getContributorType() {
        return contributorType;
    }

    public UUID getUuid() {
        return uuid;
    }

    public AbstractConfig getOtherVals() {
        return otherVals;
    }

    @Override
    public String toString() {
        return "Contributor{" +
            "uuid=" + uuid +
            ", contributorType=" + contributorType +
            ", otherVals=" + otherVals +
            '}';
    }
}
