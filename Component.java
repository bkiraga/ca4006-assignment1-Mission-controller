import java.util.Random;

public class Component {

    String type;
    int reportRate;
    int missionSize;

    public Component(String type) {
        this.type = type;
        Random random = new Random();
        reportRate = random.nextInt(9) + 1;
        missionSize = random.nextInt(9) + 1;
    }
}