package pl.com.carfleetmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.carfleetmanagementsystem.models.Card;
import pl.com.carfleetmanagementsystem.models.User;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    Optional<Card> findByUser(User user);

    Optional<Card> findByCardId(Long id);
}
