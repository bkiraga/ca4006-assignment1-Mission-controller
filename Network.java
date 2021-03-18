import java.lang.Thread;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;


public class Network implements Runnable {
	Random r = new Random();
	int rNum = r.nextInt(3);
	enum TypeNET{
		FNET (20000000, 0.8),
		MNET (20000, 0.9),
		SNET (20, 0.999);
		
		public int speed;
		public double avail;
		
		private TypeNET(int speed, double avail) {
			this.speed = speed;
			this.avail = avail;
		}
	}
	
	Queue<Pair<Message, Boolean>> messages = new LinkedList<>();
	
	void messageController(Message message){
        Pair<Message, Boolean> p = new Pair<Message, Boolean>(message, true);
        //System.out.println("NETWORK (<-): " + message.info + " waiting to be sent" + " (REQUIRES RESPONSE: " + message.type == Message.TYPE_RESPONSE_NEEDED + ")");
        messages.add(p);
    }

    void messageMission(Message message){
        Pair<Message, Boolean> p = new Pair<Message, Boolean>(message, false);
        //System.out.println("NETWORK (->): " + message.info + " waiting to be sent");
        messages.add(p);
    }

    public Speed getAvailableSpeed(){
        Integer randInt = rand.nextInt(100);
        if (randInt <= 80){
            return Network.TypeNET.FNET;
        }
        else if (randInt <= 90){
            return Network.TypeNET.MNET;
        }
        else if (randInt < 100) {
            return Network.TypeNET.SNET;
        }
        else{
            return null;
        }
    }

}