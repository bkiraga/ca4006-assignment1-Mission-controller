public class Main {
    public static void main(String[] args){
        Controller controller = new Controller();
        for (int i = 1; i < 11; i++) {
            controller.launchMission(i, controller);
        }
        controller.missionPool.shutdown();
    }
}