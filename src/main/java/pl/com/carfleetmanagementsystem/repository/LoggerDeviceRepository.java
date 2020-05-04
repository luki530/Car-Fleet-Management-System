package pl.com.carfleetmanagementsystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.carfleetmanagementsystem.models.LoggerDevice;
import pl.com.carfleetmanagementsystem.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoggerDeviceRepository extends JpaRepository<LoggerDevice, Long> {

    @Override
    void deleteById(Long id);

    @Override
    Optional<LoggerDevice> findById(Long id);

    @Override
    List<LoggerDevice> findAll();
}
