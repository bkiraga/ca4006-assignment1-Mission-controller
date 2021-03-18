import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.*;

public class Mission implements Runnable {

    // int id;
    long startTime;
    int destination;
    int missionDuration;
    long missionEnd;
    HashMap<String, Integer> componentList;
    int stage;
    boolean missionFailed;
    Network network;

    public Mission(long startTime, int destination, HashMap<String, Integer> componentList) {
        // this.id = id;
        this.startTime = startTime;
        this.destination = destination;
        this.componentList = componentList;
        missionDuration = destination * 5; //mission duration is a function of the destination
        missionEnd = startTime + missionDuration;
        network = new Network();
        stage = 0;
        missionFailed = false;
    }

    public void sendReport(String type, boolean failiure) {
        //send report through network
        System.out.println(Thread.currentThread().getName() + " stage " + stage + " finished");
    }

    public boolean missionStage(int stage, int missionDuration) {
        if (stage != 0 && stage % 2 != 0) {
            int explorationTime = 1000;
            int transitTime = missionDuration - explorationTime;
            if (stage == 4) {
                pauseMission(explorationTime, false);
            } else if (stage == 2) {
                pauseMission(transitTime, false);
            }
            // try {
            //     int explorationTime = 5000;
            //     int transitTime = missionDuration - explorationTime;
            //     if (stage == 4) {
            //         Thread.sleep(explorationTime);
            //     } else if (stage == 2) {
            //         Thread.sleep(transitTime);
            //     }
            //     Thread.sleep(missionDuration / 2);
            // } catch (InterruptedException exception) {
            //     exception.printStackTrace();
            // }
        }
        Random rand = new Random();
        boolean stageFailure = rand.nextInt(100) < 10;
        if (stageFailure) {
            //ask controller for update
            //if update fails set to true
            return true;
        }
        return false;
    }

    public void executeComponentThreads(ExecutorService componentPool, String type, Mission mission) {
        int count = componentList.get(type);
        for (int i = 0; i < count; i++){
            componentPool.execute(new Component(network, type, mission));
        }
    }

    public void pauseMission(long time, boolean errorPause) {
        if (errorPause) {
            missionEnd += time;
        }
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

        System.out.println(Thread.currentThread().getName() + " Mission thread running");

        //time before launch
        while ((System.currentTimeMillis()/1000) < startTime) {
            pauseMission(10, false);
        }
        stage += 1;

        //start component threads
        int componentCount = 0;
        for (int i : componentList.values()) {
            componentCount += i;
        }
        ExecutorService componentPool = Executors.newFixedThreadPool(componentCount);
        executeComponentThreads(componentPool, "thruster", this);
        executeComponentThreads(componentPool, "instrument", this);
        executeComponentThreads(componentPool, "controlSystem", this);
        executeComponentThreads(componentPool, "powerplant", this);
        componentPool.shutdown();

        //stages 1-4
        boolean stageFailiure;;
        while (stage <= 4) {
            stageFailiure = missionStage(stage, missionDuration);
            if (!stageFailiure){
                sendReport("mission", false);
                stage += 1; //replace with a controller method
            } else {
                System.out.println(Thread.currentThread().getName() + " Mission failed at stage: " + stage);
                sendReport("mission", true);
                break;
            }
        }
        System.out.println("mission finished");
    }
}
