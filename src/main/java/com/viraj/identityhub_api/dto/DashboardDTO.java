package com.viraj.identityhub_api.dto;

public class DashboardDTO {

    private long totalEmployees;

    private long activeEmployees;

    private long disabledEmployees;

    private long lockedEmployees;

    private long totalRoles;

    private long totalGroups;

    private long totalEntitlements;

    private long auditLogs;

    public DashboardDTO() {
    }

    public long getTotalEmployees() {
        return totalEmployees;
    }

    public void setTotalEmployees(long totalEmployees) {
        this.totalEmployees = totalEmployees;
    }

    public long getActiveEmployees() {
        return activeEmployees;
    }

    public void setActiveEmployees(long activeEmployees) {
        this.activeEmployees = activeEmployees;
    }

    public long getDisabledEmployees() {
        return disabledEmployees;
    }

    public void setDisabledEmployees(long disabledEmployees) {
        this.disabledEmployees = disabledEmployees;
    }

    public long getLockedEmployees() {
        return lockedEmployees;
    }

    public void setLockedEmployees(long lockedEmployees) {
        this.lockedEmployees = lockedEmployees;
    }

    public long getTotalRoles() {
        return totalRoles;
    }

    public void setTotalRoles(long totalRoles) {
        this.totalRoles = totalRoles;
    }

    public long getTotalGroups() {
        return totalGroups;
    }

    public void setTotalGroups(long totalGroups) {
        this.totalGroups = totalGroups;
    }

    public long getTotalEntitlements() {
        return totalEntitlements;
    }

    public void setTotalEntitlements(long totalEntitlements) {
        this.totalEntitlements = totalEntitlements;
    }

    public long getAuditLogs() {
        return auditLogs;
    }

    public void setAuditLogs(long auditLogs) {
        this.auditLogs = auditLogs;
    }
}