import java.util.ArrayList;
import java.util.Random;
public class Mission {

    long startTime;
    ArrayList<Component> componentList;
    String destination;

    public Mission(String destination) {
        this.destination = destination;
        long currentTime = System.currentTimeMillis();
        Random random = new Random();
        startTime = random.nextInt() + currentTime;
        // componentList = new ArrayList<Component>();

    }
}
