import java.util.concurrent.*;

public class Controller {

    ExecutorService threadPool;

    public Controller() {
        threadPool = Executors.newFixedThreadPool(10);
    }

    public void constructMissionComponents() {

    }
    
    public void launchMission(int startTime, int destination) {
        threadPool.execute(new Mission(startTime, destination));
    }

    public void updateMissionStage() {

    }

    public void checkFailures() {

    }

    public void sendInstructions() {

    }

    public void sendReports() {

    }

    public void sendSoftwareUpdate() {

    }

}