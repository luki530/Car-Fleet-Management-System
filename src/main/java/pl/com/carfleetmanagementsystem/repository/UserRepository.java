package pl.com.carfleetmanagementsystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.com.carfleetmanagementsystem.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);

	Optional<User> findByEmailIgnoreCase(String email);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	List<User> findAll();

	Optional<User> findById(Long id);

	@Override
	void delete(User user);
}
