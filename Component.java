public class Component implements Runnable {

    String type;
    int reportRate;
    int missionSize;

    public Component(String type, int reportRate, int missionSize) {
        this.type = type;
        this.reportRate = reportRate;
        this.missionSize = missionSize;
    }

    public void run() {
        System.out.println("Component running");
    }
}