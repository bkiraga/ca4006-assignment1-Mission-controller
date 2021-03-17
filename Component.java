import java.util.Random;
public class Component implements Runnable {

    String type;
    int reportRate;
    int missionSize;
    Network network;

    public Component(Network network, String type) {
        this.network = network;
    }

    public void run() {
        System.out.println("Component running");
    }
}