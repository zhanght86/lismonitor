package com.sinosoft.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class LisJobKey {
    private Long id;

    private String moduleid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModuleid() {
        return moduleid;
    }

    public void setModuleid(String moduleid) {
        this.moduleid = moduleid == null ? null : moduleid.trim();
    }
}