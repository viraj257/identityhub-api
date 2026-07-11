package com.viraj.identityhub_api.dto;

public class ApplicationInfoDTO {

    private String applicationName;
    private String version;
    private String environment;
    private String developer;
    private String framework;
    private String javaVersion;
    private String database;
    private String status;

    public ApplicationInfoDTO() {
    }

    public ApplicationInfoDTO(String applicationName,
                              String version,
                              String environment,
                              String developer,
                              String framework,
                              String javaVersion,
                              String database,
                              String status) {

        this.applicationName = applicationName;
        this.version = version;
        this.environment = environment;
        this.developer = developer;
        this.framework = framework;
        this.javaVersion = javaVersion;
        this.database = database;
        this.status = status;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getFramework() {
        return framework;
    }

    public void setFramework(String framework) {
        this.framework = framework;
    }

    public String getJavaVersion() {
        return javaVersion;
    }

    public void setJavaVersion(String javaVersion) {
        this.javaVersion = javaVersion;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}