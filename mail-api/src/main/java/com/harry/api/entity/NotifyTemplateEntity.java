package com.harry.api.entity;

public class NotifyTemplateEntity {

    private Long id;
    private String systemID;
    private String serverID;
    private String typeID;
    private String context;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSystemID() {
        return systemID;
    }

    public void setSystemID(String systemID) {
        this.systemID = systemID;
    }

    public String getServerID() {
        return serverID;
    }

    public void setServerID(String serverID) {
        this.serverID = serverID;
    }

    public String getTypeID() {
        return typeID;
    }

    public void setTypeID(String typeID) {
        this.typeID = typeID;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public NotifyTemplateEntity() {
    }

    public NotifyTemplateEntity(String systemID, String serverID) {
        this.systemID = systemID;
        this.serverID = serverID;
    }
}
