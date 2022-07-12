package com.techprimers.mongodb.springbootmongodbexample.security;

public enum ApplicationUserPermissions {
    STUDENT_READ("student:read"),
    STUDENT_WRITE("student:write"),
    COURSE_READ("course:read"),
    COURSE_WRITE("course:write");

    public final String permission;

    ApplicationUserPermissions(String permission) {
        this.permission = permission;
    }
}
