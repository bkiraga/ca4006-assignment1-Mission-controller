import java.util.ArrayList;
public class Mission implements Runnable{

    long startTime;
    ArrayList<Component> componentList;
    String destination;

    public void run() {
        System.out.println("Mission thread running");
    }
}
