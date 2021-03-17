import java.util.HashMap;
import java.util.concurrent.*;
import java.util.Random;

public class Controller {

    public static ExecutorService threadPool;
    int missionCount;

    public Controller() {
        threadPool = Executors.newFixedThreadPool(10);
    }

    //we assume that space crfaft doesnt use its space communication before launch
    public HashMap<String, Integer> constructMissionComponents(int thrusterCount, int instrumentCount, int controlSystemCount, int powerplantCount) {
        HashMap<String, Integer> components = new HashMap<String, Integer>();
        components.put("thruster", thrusterCount);
        components.put("instrument", instrumentCount);
        components.put("controlSystem", controlSystemCount);
        components.put("powerplant", powerplantCount);
        return components;
    }
    
    public void launchMission(int startTime, int fuel) {
        Random rand = new Random();

        //destination is a funtion of the fuel carried
        int destination = fuel / 2;

        //longer missions require more thrusters
        int thrusterCount;
        if (fuel < 1000){
            thrusterCount = rand.nextInt(2) + 1;
        } else {
            thrusterCount = rand.nextInt(2) + 3;
        }

        int instrumentCount = rand.nextInt(4) + 1;
        int controlSystemCount = rand.nextInt(2) + 1;
        int powerplantCount = rand.nextInt(2) + 1;

        HashMap<String, Integer> components = constructMissionComponents(thrusterCount, instrumentCount, controlSystemCount, powerplantCount);
        Mission mission = new Mission(startTime, destination, components);
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