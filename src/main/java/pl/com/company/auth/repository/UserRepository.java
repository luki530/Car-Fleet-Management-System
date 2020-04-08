package pl.com.company.auth.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.company.auth.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmailIgnoreCase(String email);
}