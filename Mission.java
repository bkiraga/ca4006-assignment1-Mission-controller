import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.*;

public class Mission implements Runnable {

    // int id;
    int startTime;
    int destination;
    HashMap<String, Integer> componentList;
    int stage;
    boolean missionFailed;
    Network network;

    public Mission(int startTime, int destination, HashMap<String, Integer> componentList) {
        // this.id = id;
        this.startTime = startTime;
        this.destination = destination;
        this.componentList = componentList;
        network = new Network();
        stage = 0;
        missionFailed = false;
    }

    public void sendReport() {
        //send report through network
        System.out.println(Thread.currentThread().getName() + " stage " + stage + " finished");
    }

    public boolean missionStage(int stage) {
        if (stage != 0 && stage % 2 != 0) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
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

    // stage=0 -> waiting for boost
    // stage=1 -> boost
    // stage=2 -> transit
    // stage=3 -> landing
    // stage=4 -> exploration

    public void run() {

        System.out.println(Thread.currentThread().getName() + " Mission thread running");

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

        //time before launch
        try {
            Thread.sleep(3000);
        } catch(InterruptedException exception) {
            exception.printStackTrace();
        }
        stage += 1;

        //stages 1-4
        boolean stageFailiure;;
        while (stage <= 4) {
            stageFailiure = missionStage(stage);
            if (!stageFailiure){
                sendReport();
                stage += 1;
            } else {
                System.out.println(Thread.currentThread().getName() + " Mission failed at stage: " + stage);
                break;
            }
        }
    }
}
