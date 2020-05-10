package pl.com.carfleetmanagementsystem.http.request;

import java.util.Set;

public class AssignRoleRequest {

    private Long userId;

    private Set<String> roles;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
