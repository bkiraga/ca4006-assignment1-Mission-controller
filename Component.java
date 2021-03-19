import java.util.Random;
public class Component implements Runnable {

    String type;
    int reportRate;
    int id;
    Network network;
    Mission mission;

    public Component(int id, Network network, String type, Mission mission) {
        this.network = network;
        this.mission = mission;
        this.type = type;
        this.id = id;
    }

    public void run() {
        // System.out.println("Component:" + type + " running");
        Random rand = new Random();
        reportRate = rand.nextInt(2000) + 500;  //reports vary from every half a month to every 2 months
        boolean needResponse;
        while ((System.currentTimeMillis() / 1000) < mission.missionEnd){
            try {
                Thread.sleep(reportRate);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            needResponse = rand.nextInt(100) < 30;  //30% of reports need a response
            if (needResponse) {
                //sleep mission
                // mission.pauseMission(5, true);
                mission.sendReport(id, type, true);
                // wake up mission after update
            } else {
                mission.sendReport(id, type, false);
            }
        }
        System.out.println("Component " + type + "finished");
    }
}