import java.util.ArrayList;
import java.util.Random;

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
        System.out.println("stage " + stage + " finished");
    }

    // public boolean stage(int stage, int time) {
    //     Random rand = new Random();
    //     missionFailed = rand.nextInt(100) < 10;
    //     if (stage != 0 && stage % 2 != 0) {
    //         try {
    //             Thread.sleep(time);
    //         } catch (InterruptedException exception) {
    //             exception.printStackTrace();
    //         }
    //     }
    //     return missionFailed;
    // }

    // stage=0 -> waiting for boost
    // stage=1 -> boost
    // stage=2 -> transit
    // stage=3 -> landing
    // stage=4 -> exploration

    public void run() {
        System.out.println(Thread.currentThread().getName() + " Mission thread running");
        try {
            Thread.sleep(3000);
        } catch(InterruptedException exception) {
            exception.printStackTrace();
        }
        // try {
        //     Thread.sleep(System.currentTimeMillis() - startTime);
        // } catch(InterruptedException exception) {
        //     exception.printStackTrace();
        // }
        // stage += 1;

        // //boost stage
        // fail = rand.nextInt(100) < 25;
        // sendReport();
        // if (fail == false) {
        //     stage += 1;
        // }

        // //transit stage
        // try {
        //     Thread.sleep(5000);
        // } catch(InterruptedException exception) {
        //     exception.printStackTrace();
        // }
        // sendReport();
        // if (fail == false) {
        //     stage += 1;
        // }

        // //landing stage
        // sendReport();
        // if (fail == false) {
        //     stage += 1;
        // }

        // //exploration stage
        // try {
        //     Thread.sleep(5000);
        // } catch(InterruptedException exception) {
        //     exception.printStackTrace();
        // }
        // sendReport();
    }
}
