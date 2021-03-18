import java.util.concurrent.ArrayBlockingQueue;
public class Network {

    ArrayBlockingQueue<DataTransmission> fastNetwork;
    ArrayBlockingQueue<DataTransmission> mediumNetwork;
    ArrayBlockingQueue<DataTransmission> slowNetwork;
    // boolean isAvailable;

    public Network() {
        fastNetwork = new ArrayBlockingQueue<DataTransmission>(10);
        mediumNetwork = new ArrayBlockingQueue<DataTransmission>(4);
        slowNetwork = new ArrayBlockingQueue<DataTransmission>(2);
    }

    public void transmitMessage(String type) {

    }

    // public void setAvailability() {

    // }
}