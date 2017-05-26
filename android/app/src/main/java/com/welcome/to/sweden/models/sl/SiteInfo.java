package com.welcome.to.sweden.models.sl;

public abstract class SiteInfo {

    public abstract String getName();

    public abstract String getSiteId();

    public String getCleanedName() {
        if (getName() != null) {
            return getName().replace("(Stockholm)", "");
        }
        return getName();
    }
}
