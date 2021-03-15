import java.util.ArrayList;
public class Mission implements Runnable{

    int startTime;
    ArrayList<Component> componentList;
    String destination;
    String stage;

    public Mission(int startTime, String destination) {
        this.startTime = startTime;
        this.destination = destination;
    }

    public void run() {
        System.out.println("Mission thread running");
    }
}
