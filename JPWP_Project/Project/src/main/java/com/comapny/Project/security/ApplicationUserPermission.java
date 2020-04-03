package com.comapny.Project.security;

public enum ApplicationUserPermission {
    WORKER_READ("worker:read"),
    WORKER_WRITE("worker:write"),
    COURSE_READ("course:read"),
    COURSE_WRITE("course:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
