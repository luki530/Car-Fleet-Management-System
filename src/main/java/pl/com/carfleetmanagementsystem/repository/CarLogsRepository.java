package pl.com.carfleetmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.carfleetmanagementsystem.models.CarLog;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarLogsRepository extends JpaRepository<CarLog, Long> {

    @Override
    Optional<CarLog> findById(Long id);

    @Override
    void delete(CarLog carLog);

    @Override
    List<CarLog> findAll();
}
