public class Component implements Runnable {

    String type;
    int reportRate;
    int missionSize;

    public Component() {
 
    }

    public void run() {
        System.out.println("Component running");
    }
}