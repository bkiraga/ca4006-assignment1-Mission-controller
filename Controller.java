import java.util.HashMap;
import java.util.concurrent.*;

public class Controller {

    ExecutorService threadPool;
    int missionCount;

    public Controller(int missionCount) {
        this.missionCount = missionCount;
        threadPool = Executors.newFixedThreadPool(missionCount);
        // HashMap<Mission, String> missionsList;
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
        Controller controller = new Controller(4);
        controller.launchMission(3,4);
        controller.launchMission(6,20);
        controller.launchMission(8,30);
        controller.launchMission(9,55);
    }

}