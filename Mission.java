import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

public class Mission implements Runnable {

    int startTime;
    ArrayList<Component> componentList;
    int destination;
    int stage;
    boolean missionFailed;
    Network network;

    public Mission(int startTime, int destination) {
        this.startTime = startTime;
        this.destination = destination;
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

    // stage=0 -> waiting for boost
    // stage=1 -> boost
    // stage=2 -> transit
    // stage=3 -> landing
    // stage=4 -> exploration

    public void run() {

        System.out.println(Thread.currentThread().getName() + " Mission thread running");

        //start component threads
        ExecutorService componentPool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            componentPool.execute(new Component());
        }
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
