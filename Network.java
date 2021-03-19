import java.lang.Thread;
import java.util.*;
import java.util.Random;


public class Network {
	Random r = new Random();
	int rNum = r.nextInt(3);
	
	// Array of upload rate and network availability 
	List<Integer> rateList = Arrays.asList(20000000,20000,20);
	List<Double> availabilityList = Arrays.asList(0.8,0.9,0.999);
	}
	
    public int getRate(){
        Integer randInt = rand.nextInt(100);
        if (randInt <= 80){
            return rateList[0];
        }
        else if (randInt <= 90){
            return rateList[1];
        }
        else if (randInt < 100) {
            return rateList[2];
        }
        else{
            return null;
        }
    }
    
    public double getAvailable(){
        if (randInt <= 80){
            return availabilityList[0];
        }
        else if (randInt <= 90){
            return availabilityList[1];
        }
        else if (randInt < 100) {
            return availabilityList[2];
        }
        else{
            return null;
        }
        
    }
    

}