import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.*;

public class Mission implements Runnable {

    int id;
    long startTime;
    long destination;
    long missionDuration;
    long missionEnd;
    HashMap<String, Integer> assembledComponents;
    HashMap<Integer, Component> componentFinder = new HashMap<Integer, Component>();
    Controller controller;
    int stage;
    boolean missionFailed;
    Network network;
    Random rand = new Random();

    public Mission(int id, long startTime, long destination, HashMap<String, Integer> assembledComponents, Controller controller) {
        this.id = id;
        this.startTime = startTime;
        this.destination = destination;
        this.assembledComponents = assembledComponents;
        this.controller = controller;
        missionDuration = destination * 1000; //mission duration is a function of the destination
        missionEnd = startTime + destination;
        network = new Network(controller);
        stage = 0;
        missionFailed = false;
        
    }

    public void sendReport(int id, int componentID, String type, boolean missionFailure, boolean componentNeedResponse) {
        //send report through network
        DataTransmission data = new DataTransmission(id, componentID, type, missionFailure, componentNeedResponse);
        network.transmitMessage(data);
    }

    public void deployUpdate() {
        if (missionFailed) {
            //25% chance to recover failiure with update
            if ((rand.nextInt(100) < 25) == true) {
                missionFailed = true;
            } else {
                System.out.println("mission failed");
            }
        }
    }

    public void missionStage(int stage, long missionDuration) {
        if ((stage != 0) && (stage % 2 == 0)) {
            long explorationTime = 1000;
            long transitTime = missionDuration - explorationTime;
            if (stage == 4) {
                // System.out.println("exploration");
                pauseMission(explorationTime, false);
            } else if (stage == 2) {
                // System.out.println("transit: " + transitTime);
                pauseMission(transitTime, false);
            }
        }
        boolean stageFailure = rand.nextInt(100) < 10;
        if (stageFailure) {
            missionFailed = true;
            //ask controller for update
        } else {
            missionFailed = false;
        }

    }

    public void executeComponentThreads(ExecutorService componentPool, String type, Mission mission) {
        int count = assembledComponents.get(type);
        int componentID = 1;
        for (int i = 0; i < count; i++){
            Component component = new Component(componentID, network, type, mission);
            componentFinder.put(componentID, component);
            componentPool.execute(component);
            componentID += 1;
        }
    }

    public void pauseMission(long time, boolean errorPause) {
        // if (errorPause) {
        //     missionEnd += time;
        //     extraTime += time;
        // }
        try {
            Thread.sleep(time);
        } catch(InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    public void responseReceived(int id) {
        componentFinder.get(id).responded = true;
    }

    // stage=0 -> waiting for boost
    // stage=1 -> boost
    // stage=2 -> transit
    // stage=3 -> landing
    // stage=4 -> exploration

    public void run() {
        
        //time before launch
        while ((System.currentTimeMillis()/1000) < startTime) {
            pauseMission(10, false);
        }
        stage += 1;

        //start component threads
        int componentCount = 0;
        for (int i : assembledComponents.values()) {
            componentCount += i;
        }
        ExecutorService componentPool = Executors.newFixedThreadPool(componentCount);
        executeComponentThreads(componentPool, "thruster", this);
        executeComponentThreads(componentPool, "instrument", this);
        executeComponentThreads(componentPool, "controlSystem", this);
        executeComponentThreads(componentPool, "powerplant", this);
        componentPool.shutdown();

        //stages 1-4
        while (stage <= 4) {
            missionStage(stage, missionDuration);
            if (!missionFailed){
                sendReport(id, 0, "mission", false, false);
                stage += 1; //replace with a controller method
            } else {
                sendReport(id, 0, "mission", true, false);
                break;
            }
        }
        pauseMission(2000, false);
        System.out.println("mission finished");
    }
}
