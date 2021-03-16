import java.util.ArrayList;
public class Mission implements Runnable {

    int startTime;
    ArrayList<Component> componentList;
    int destination;
    String stage;

    public Mission(int startTime, int destination) {
        this.startTime = startTime;
        this.destination = destination;
    }

    public void sendReport() {

    }

    public void run() {
        System.out.println("Mission thread running");
    }
}
