package pl.com.carfleetmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.carfleetmanagementsystem.models.Car;
import pl.com.carfleetmanagementsystem.models.CarLogs;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarLogsRepository extends JpaRepository<CarLogs, Long> {

    @Override
    Optional<CarLogs> findById(Long carLogsId);

    @Override
    void delete(CarLogs carLogs);

    @Override
    List<CarLogs> findAll();
}
