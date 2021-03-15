import java.util.Random;

public class Component implements Runnable {

    String type;
    int reportRate;
    int missionSize;

    public Component(String type) {
        this.type = type;
        Random random = new Random();
        reportRate = random.nextInt(9) + 1;
        missionSize = random.nextInt(9) + 1;
    }

    public void run() {
        System.out.println("Component running");
    }
}