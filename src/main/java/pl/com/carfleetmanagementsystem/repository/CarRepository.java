package pl.com.carfleetmanagementsystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.carfleetmanagementsystem.models.Car;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Override
    List<Car> findAll();

    @Override
    Optional<Car> findById(Long id);

    @Override
    void delete(Car car);

}
