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
import java.util.HashSet;
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
        Set<String> strRoles = assignRoleRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByNameIgnoreCase(ERole.ROLE_NEW)
                    .orElseThrow(() -> new RoleNotFoundException(ERole.ROLE_NEW.toString()));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "ROLE_ADMIN":
                        Role adminRole = roleRepository.findByNameIgnoreCase(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RoleNotFoundException(role));
                        roles.add(adminRole);

                        break;
                    case "ROLE_EMPLOYEE":
                        Role employeeRole = roleRepository.findByNameIgnoreCase(ERole.ROLE_EMPLOYEE)
                                .orElseThrow(() -> new RoleNotFoundException(role));
                        roles.add(employeeRole);

                        break;
                    case "ROLE_BOSS":
                        Role bossRole = roleRepository.findByNameIgnoreCase(ERole.ROLE_BOSS)
                                .orElseThrow(() -> new RoleNotFoundException(role));
                        roles.add(bossRole);

                        break;
                    case "ROLE_DRIVER":
                        Role driverRole = roleRepository.findByNameIgnoreCase(ERole.ROLE_DRIVER)
                                .orElseThrow(() -> new RoleNotFoundException(role));
                        roles.add(driverRole);

                        break;
                    default:
                        Role newRole = roleRepository.findByNameIgnoreCase(ERole.ROLE_NEW)
                                .orElseThrow(() -> new RoleNotFoundException(role));
                        roles.add(newRole);
                }
            });
        }
        Role userRole = roleRepository.findByNameIgnoreCase(ERole.ROLE_NEW)
                .orElseThrow(() -> new RoleNotFoundException(ERole.ROLE_NEW.toString()));
        roles.add(userRole);

        if(strRoles.isEmpty()){
            System.out.println("Google cloud");
        }

        user.setRoles(roles);
        return ResponseEntity.ok().body(userRepository.save(user));
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> findAllRoles(){
        List<Role> roles = roleRepository.findAll();
        return ResponseEntity.ok().body(roles);
    }

}
