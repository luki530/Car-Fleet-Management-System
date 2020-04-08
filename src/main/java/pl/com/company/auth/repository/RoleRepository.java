package pl.com.company.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.company.auth.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
}
