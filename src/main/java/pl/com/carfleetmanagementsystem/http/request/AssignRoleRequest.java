package pl.com.carfleetmanagementsystem.http.request;

import pl.com.carfleetmanagementsystem.models.Role;

import java.util.Set;

public class AssignRoleRequest {

    private Long userId;

    private Set<Role> roles;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
