import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.*;

public class Mission implements Runnable {

    // int id;
    long startTime;
    long destination;
    long missionDuration;
    long missionEnd;
    HashMap<String, Integer> assembledComponents;
    HashMap<Integer, Component> components;
    int stage;
    boolean missionFailed;
    Network network;

    public Mission(long startTime, long destination, HashMap<String, Integer> assembledComponents) {
        // this.id = id;
        this.startTime = startTime;
        this.destination = destination;
        this.assembledComponents = assembledComponents;
        missionDuration = destination * 1000; //mission duration is a function of the destination
        missionEnd = startTime + destination;
        network = new Network();
        stage = 0;
        missionFailed = false;
    }

    public void sendReport(String type, boolean failiure) {
        //send report through network
        // System.out.println(Thread.currentThread().getName() + " stage " + stage + " finished");
        // System.out.println("reportsent");
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
        Random rand = new Random();
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
        for (int i = 0; i < count; i++){
            componentPool.execute(new Component(network, type, mission));
        }
    }

    public void pauseMission(long time, boolean errorPause) {
        // if (errorPause) {
        //     missionEnd += time;
        // }
        try {
            Thread.sleep(time);
        } catch(InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    // stage=0 -> waiting for boost
    // stage=1 -> boost
    // stage=2 -> transit
    // stage=3 -> landing
    // stage=4 -> exploration

    public void run() {

        // System.out.println(Thread.currentThread().getName() + " Mission thread running");
        System.out.println("starttime: " + startTime);
        System.out.println("missionduration: " + missionDuration);
        System.out.println("missionEnd: " + missionEnd);
        
        //time before launch
        while ((System.currentTimeMillis()/1000) < startTime) {
            pauseMission(10, false);
        }
        stage += 1;
        // System.out.println(stage);

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
            // stageFailiure = missionStage(stage, missionDuration);
            missionStage(stage, missionDuration);
            if (!missionFailed){
                sendReport("mission", false);
                stage += 1; //replace with a controller method
                // System.out.println(stage);
            } else {
                // System.out.println(Thread.currentThread().getName() + " Mission failed at stage: " + stage);
                sendReport("mission", true);
                break;
            }
        }
        pauseMission(2000, false);
        System.out.println("mission finished");
    }
}
