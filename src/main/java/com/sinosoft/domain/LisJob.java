package com.sinosoft.domain;

public class LisJob extends LisJobKey {
    private String job;

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job == null ? null : job.trim();
    }
}