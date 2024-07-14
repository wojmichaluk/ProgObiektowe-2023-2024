package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimulationEngine {
    private List<Simulation> simulationList;
    private List<Thread> threads = new ArrayList<>();
    private ExecutorService executorService = Executors.newFixedThreadPool(4);

    public SimulationEngine(List<Simulation> simulationList){
        this.simulationList = simulationList;
        for (Simulation simulation : simulationList){
            threads.add(new Thread(simulation));
        }
    }

    public void runSync(){
        for (Simulation simulation : simulationList){
            simulation.run();
        }
    }

    public void runAsync(){
        for (Thread thread : threads){
            thread.start();
        }
    }

    public void awaitSimulationsEnd() throws InterruptedException {
        //na potrzeby punktu 4. części 2. - nie usuwam, bo po modyfikacji z program i tak działa
        for (Thread thread : threads){
            thread.join();
        }
        //-------------

        executorService.shutdown();
        if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
            executorService.shutdownNow();
        }
    }

    public void runAsyncInThreadPool(){
        for (Thread thread : threads){
            executorService.submit(thread);
        }
    }
}
