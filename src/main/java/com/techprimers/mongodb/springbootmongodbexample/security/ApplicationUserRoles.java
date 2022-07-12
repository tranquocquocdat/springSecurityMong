package com.techprimers.mongodb.springbootmongodbexample.security;

import com.techprimers.mongodb.springbootmongodbexample.security.ApplicationUserPermissions;

import java.util.Set;

import static com.techprimers.mongodb.springbootmongodbexample.security.ApplicationUserPermissions.*;

public enum ApplicationUserRoles {
    STUDENT(Set.of()),
    ADMIN(Set.of(STUDENT_READ,STUDENT_WRITE,COURSE_READ,COURSE_WRITE)),
    HAFT_ADMIN(Set.of(STUDENT_READ,COURSE_READ));
    private final Set<ApplicationUserPermissions> permissions;

    ApplicationUserRoles(Set<ApplicationUserPermissions> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermissions> getPermissions() {
        return permissions;
    }
}
