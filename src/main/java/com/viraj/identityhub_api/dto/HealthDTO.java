package com.viraj.identityhub_api.dto;

public class HealthDTO {

    private String status;
    private String application;
    private String version;
    private String database;

    public HealthDTO() {
    }

    public HealthDTO(String status, String application, String version, String database) {
        this.status = status;
        this.application = application;
        this.version = version;
        this.database = database;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }
}