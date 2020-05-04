package pl.com.carfleetmanagementsystem.analyse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.com.carfleetmanagementsystem.analyse.implementations.CarLogsAnalyser;
import pl.com.carfleetmanagementsystem.models.Car;
import pl.com.carfleetmanagementsystem.models.CarLog;
import pl.com.carfleetmanagementsystem.repository.CarRepository;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class CarAnalysersManager implements Runnable{

    @Autowired
    CarRepository carRepository;

    private List<Car> cars = new LinkedList<>();

    private Map<Car, CarLogsAnalyser> carLogsAnalysers = new HashMap<>();

    private Queue<CarLog> carLogToAnalyse = new ArrayDeque<>();

    private Long previousCheckTime;

    private Thread managerThread;

    private boolean cancel = false;

    private void runOrUpdateCarLogsAnalysers() {
        if (System.currentTimeMillis() - previousCheckTime > (60 * 1000)) {
            cars = carRepository.findAll();
            for (Car car : cars) {
                if (!carLogsAnalysers.containsKey(car)) {
                    CarLogsAnalyser analyser = new CarLogsAnalyser(car);
                    Thread thread = new Thread(analyser);
                    thread.start();
                    carLogsAnalysers.put(car, analyser);
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
        managerThread = new Thread(this, "Manager thread");
        managerThread.start();
    }

    @Override
    public void run() {
        while (!cancel) {
            runOrUpdateCarLogsAnalysers();
            CarLog carLog = carLogToAnalyse.poll();
            if (carLog != null) {
                carLogsAnalysers.get(carLog.getCar()).analyseLog(carLog);
            }
        }
    }
}
