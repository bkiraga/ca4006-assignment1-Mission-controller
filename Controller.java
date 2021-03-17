import java.util.HashMap;
import java.util.concurrent.*;

public class Controller {

    public static ExecutorService threadPool;
    int missionCount;

    public Controller() {
        threadPool = Executors.newFixedThreadPool(4);
    }

    public void constructMissionComponents() {

    }
    
    public void launchMission(int startTime, int destination) {
        Mission mission = new Mission(startTime, destination);
        threadPool.execute(mission);
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

    public static void main(String[] args){
        Controller controller = new Controller();
        controller.launchMission(3,4);
        controller.launchMission(6,20);
        controller.launchMission(8,30);
        controller.launchMission(9,55);
        threadPool.shutdown();
    }
}