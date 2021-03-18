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
        this.type = type;
        missionSize = mission.destination;
    }

    public void run() {
        System.out.println("Component:" + type + " running");
        Random rand = new Random();
        reportRate = rand.nextInt(10000) + 1000;
        boolean needResponse;
        while (mission.missionEnd < (System.currentTimeMillis() / 1000)) {
            try {
                Thread.sleep(reportRate);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            needResponse = rand.nextInt(100) < 30;
            if (needResponse) {
                //sleep mission
                mission.sendReport(type, true);
                // wake up mission after update
            } else {
                mission.sendReport(type, false);
            }
        }
        System.out.println("Component " + type + "finished");
    }
}