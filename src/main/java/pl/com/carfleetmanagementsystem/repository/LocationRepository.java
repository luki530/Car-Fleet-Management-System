package pl.com.carfleetmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.carfleetmanagementsystem.models.Location;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {

    @Override
    Optional<Location> findById(Long locationId);

    @Override
    void delete(Location location);

    @Override
    List<Location> findAll();

}
