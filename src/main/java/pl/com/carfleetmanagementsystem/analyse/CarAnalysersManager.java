package pl.com.carfleetmanagementsystem.analyse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.com.carfleetmanagementsystem.analyse.implementations.CarLogsAnalyser;
import pl.com.carfleetmanagementsystem.models.Car;
import pl.com.carfleetmanagementsystem.models.CarLog;
import pl.com.carfleetmanagementsystem.repository.CarRepository;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class CarAnalysersManager {

    @Autowired
    CarRepository carRepository;

    private List<Car> cars = new LinkedList<>();

    private Map<Car, CarLogsAnalyser> carLogsAnalysers = new HashMap<>();

    private Queue<CarLog> carLogToAnalyse = new ArrayDeque<>();

    private Long previousCheckTime;

    private boolean cancel = false;

    private void runOrUpdateCarLogsAnalysers() {
        if (System.currentTimeMillis() - previousCheckTime > (60 * 1000)) {
            cars = carRepository.findAll();
            for (Car car : cars) {
                if (!carLogsAnalysers.containsKey(car)) {
                    carLogsAnalysers.put(car, new CarLogsAnalyser(car));
                }
            }
            previousCheckTime = System.currentTimeMillis();
        }
    }

    public void analyseCarLog(CarLog carLog){
        carLogToAnalyse.offer(carLog);
    }

    @PostConstruct
    public void init() {
        previousCheckTime= System.currentTimeMillis()-100000;
        while (!cancel) {
            runOrUpdateCarLogsAnalysers();
            CarLog carLog = carLogToAnalyse.poll();
            if (carLog != null) {
                carLogsAnalysers.get(carLog.getCar()).analyseLog(carLog);
            }
        }
    }
}
