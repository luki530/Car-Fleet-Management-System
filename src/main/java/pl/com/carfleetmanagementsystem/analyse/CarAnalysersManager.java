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
public class CarAnalysersManager {

    @Autowired
    CarRepository carRepository;

    private List<Car> cars = new LinkedList<>();

    private Map<Car, CarLogsAnalyser> carLogsAnalysers = new HashMap<>();

    private Queue<CarLog> carLogToAnalyse = new ArrayDeque<>();

    private void runOrUpdateCarLogsAnalysers() {
            cars = carRepository.findAll();
            for (Car car : cars) {
                if (!carLogsAnalysers.containsKey(car)) {
                    CarLogsAnalyser analyser = new CarLogsAnalyser(car);
                    Thread thread = new Thread(analyser);
                    thread.start();
                    carLogsAnalysers.put(car, analyser);
                }
            }
    }

    public void analyseCarLog(CarLog carLog){
        carLogToAnalyse.offer(carLog);
		redirectCarLogs();
    }

	public void redirectCarLogs(){
		for(CarLog cl : carLogToAnalyse){
			CarLog carLog = carLogToAnalyse.poll();
			if (carLog != null) {
					carLogsAnalysers.get(carLog.getCar()).analyseLog(carLog);
			}
		}
	}

    @PostConstruct
    public void init() {
        runOrUpdateCarLogsAnalysers();
    }
}
