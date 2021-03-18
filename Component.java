import java.util.Random;
public class Component implements Runnable {

    String type;
    int reportRate;
    int missionSize;
    Network network;
    Mission mission;

    public Component(Network network, String type, Mission mission) {
        this.network = network;
        this.mission = mission;
        Random rand = new Random();
        reportRate = rand.nextInt(6) + 2;
    }

    public void run() {
        System.out.println("Component running");

    }
}