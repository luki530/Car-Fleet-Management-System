package pl.com.carfleetmanagementsystem.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.com.carfleetmanagementsystem.errorhandler.exception.RoleNotFoundException;
import pl.com.carfleetmanagementsystem.errorhandler.exception.UserNotFoundException;
import pl.com.carfleetmanagementsystem.http.request.AssignRoleRequest;
import pl.com.carfleetmanagementsystem.models.ERole;
import pl.com.carfleetmanagementsystem.models.Role;
import pl.com.carfleetmanagementsystem.models.User;
import pl.com.carfleetmanagementsystem.repository.RoleRepository;
import pl.com.carfleetmanagementsystem.repository.UserRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class RolesController {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @PutMapping("/roles")
    public ResponseEntity<User> assignRoles(@Valid @RequestBody AssignRoleRequest assignRoleRequest) {
        User user = userRepository.findById(assignRoleRequest.getUserId()).orElseThrow(() -> new UserNotFoundException(assignRoleRequest.getUserId()));
        Set<Role> roles = assignRoleRequest.getRoles();

        roles.forEach(role -> {
            if ("admin".equals(role)) {
                Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(() -> new RoleNotFoundException(role.toString()));
                roles.add(adminRole);
                user.setRoles(roles);
            } else if ("employee".equals(role)) {
                Role employeeRole = roleRepository.findByName(ERole.ROLE_EMPLOYEE).orElseThrow(() -> new RoleNotFoundException(role.toString()));
                roles.add(employeeRole);
                user.setRoles(roles);
            } else if ("boss".equals(role)) {
                Role bossRole = roleRepository.findByName(ERole.ROLE_BOSS).orElseThrow(() -> new RoleNotFoundException(role.toString()));
                roles.add(bossRole);
                user.setRoles(roles);
            } else if ("driver".equals(role)) {
                Role driverRole = roleRepository.findByName(ERole.ROLE_DRIVER).orElseThrow(() -> new RoleNotFoundException(role.toString()));
                roles.add(driverRole);
                user.setRoles(roles);
            } else {
                Role newRole = roleRepository.findByName(ERole.ROLE_NEW).orElseThrow(() -> new RoleNotFoundException(role.toString()));
                roles.add(newRole);
                user.setRoles(roles);
            }
        });
        return ResponseEntity.ok().body(userRepository.save(user));
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> findAllRoles(){
        List<Role> roles = roleRepository.findAll();
        return ResponseEntity.ok().body(roles);
    }

}
