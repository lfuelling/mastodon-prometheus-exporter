package net.k40s.mastoexport.api.instance;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Stats implements Serializable  {

    @JsonProperty("domain_count")
    private Integer domainCount;

    @JsonProperty("status_count")
    private Integer statusCount;

    @JsonProperty("user_count")
    private Integer userCount;

    public Integer getDomainCount() {
        return domainCount;
    }

    public void setDomainCount(Integer domainCount) {
        this.domainCount = domainCount;
    }

    public Integer getStatusCount() {
        return statusCount;
    }

    public void setStatusCount(Integer statusCount) {
        this.statusCount = statusCount;
    }

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }
}