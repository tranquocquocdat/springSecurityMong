package com.techprimers.mongodb.springbootmongodbexample.security;

import com.techprimers.mongodb.springbootmongodbexample.security.ApplicationUserPermissions;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

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

    public Set<GrantedAuthority> getGrantedAuthorities(){
        Set<GrantedAuthority> permissions=this.getPermissions().stream().map(permission->new SimpleGrantedAuthority(permission.getPermissions())).collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return permissions;
    }
}
