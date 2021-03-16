import java.util.ArrayList;
public class Mission implements Runnable {

    int startTime;
    ArrayList<Component> componentList;
    int destination;
    int stage;
    Network network;

    public Mission(int startTime, int destination) {
        this.startTime = startTime;
        this.destination = destination;
        network = new Network();
        stage = 0;
    }

    public void sendReport() {
        //send report through network
        System.out.println("stage finished");
    }

    // stage=0 -> waiting for boost
    // stage=1 -> boost
    // stage=2 -> transit
    // stage=3 -> landing
    // stage=4 -> exploration

    public void run() {
        System.out.println("Mission thread running");
        long unixTime = System.currentTimeMillis() / 1000L;
        while (unixTime < startTime) {
            unixTime = System.currentTimeMillis() / 1000L;
        }
        stage += 1;
        sendReport();
        stage += 1;
        try {
            Thread.sleep(5);
        } catch(InterruptedException exception) {
            exception.printStackTrace();
        }
        sendReport();
        stage += 1;
        sendReport();
        stage += 1;
        try {
            Thread.sleep(5);
        } catch(InterruptedException exception) {
            exception.printStackTrace();
        }
        sendReport();
    }
}
