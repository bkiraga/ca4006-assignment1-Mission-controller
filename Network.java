import java.util.concurrent.ArrayBlockingQueue;
import java.lang.Thread;
import java.util.*;
import java.util.Random;


public class Network {
	Random r = new Random();
    ArrayBlockingQueue<DataTransmission> fastNetwork;
    ArrayBlockingQueue<DataTransmission> mediumNetwork;
    ArrayBlockingQueue<DataTransmission> slowNetwork;
    Controller controller;
    
    //network bandwith bottleneck simulated by blocking queues of variable size
    public Network(Controller controller) {
        fastNetwork = new ArrayBlockingQueue<DataTransmission>(8);
        mediumNetwork = new ArrayBlockingQueue<DataTransmission>(4);
        slowNetwork = new ArrayBlockingQueue<DataTransmission>(2);
        this.controller = controller;
    }

    public void transmitMessage(DataTransmission data) {
        ArrayBlockingQueue<DataTransmission> network = allocateNetwork();
        try {
            network.put(data);
            DataTransmission sentdata = network.take();
            controller.receiveMessage(sentdata);
        } catch(InterruptedException exception) {
            exception.printStackTrace();
        }
    }
    
    //simulates network availability
    public ArrayBlockingQueue<DataTransmission> allocateNetwork(){
        Integer rNum = r.nextInt(100);
        if (rNum <= 80){
            return fastNetwork;
        }
        else if (rNum <= 90){
            return mediumNetwork;
        }
        else {
            return slowNetwork;
        }
    }
}

