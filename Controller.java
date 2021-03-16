import java.util.concurrent.*;

public class Controller {

    ExecutorService threadPool;
    int missionCount;

    public Controller(int missionCount) {
        this.missionCount = missionCount;
        threadPool = Executors.newFixedThreadPool(missionCount);
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