package pl.com.carfleetmanagementsystem.errorhandler.exception;

import pl.com.carfleetmanagementsystem.models.ERole;
import pl.com.carfleetmanagementsystem.models.Role;

import java.util.Set;

public class RoleNotFoundException extends RuntimeException {

    public RoleNotFoundException (Set<Role> role) {
        super("Could not find role: " + role);
    }
}
