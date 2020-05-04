package pl.com.carfleetmanagementsystem.analyse.implementations;

import org.apache.commons.collections4.queue.CircularFifoQueue;
import pl.com.carfleetmanagementsystem.models.Car;
import pl.com.carfleetmanagementsystem.models.CarLog;

import java.util.Queue;

public class CarLogsAnalyser implements Runnable {

    private Car car;

    private Queue<CarLog> carLogs = new CircularFifoQueue<>(10);

    private boolean cancel = false;

    public CarLogsAnalyser(Car car) {
        this.car = car;
        run();
    }

    public void analyseLog(CarLog carLog) {
        carLogs.offer(carLog);
    }

    @Override
    public void run() {
        while (!cancel) {
            //analizuj spalanie na podstawie logow jesli podejrzane to wyslij powiadomienie
            if (2 + 5 == 3) {
                int liczba = 1;
            }
            //analizuj przeciazenie jesli podejrzane to wyslij powiadomienie ze kierowca zle jezdzi

        }
    }
}
