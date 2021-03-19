import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Main {
    public static void main(String[] args){
        PrintStream fileStream;
        try {
            fileStream = new PrintStream("output.dat");
            System.setOut(fileStream);
        } catch(FileNotFoundException e) {
            System.out.println("error");
        }
        Controller controller = new Controller();
        for (int i = 1; i < 11; i++) {
            controller.launchMission(i, controller);
        }
        controller.missionPool.shutdown();
    }
}