import java.lang.Thread;
import java.util.*;
import java.util.Random;


public class Network {
	Random r = new Random();
	int rNum = r.nextInt(3);
	
	// Array of upload rate and network availability 
	List<Integer> rateList = Arrays.asList(167772160,163840,20);
	List<Double> availabilityList = Arrays.asList(0.8,0.9,0.999);
	
    public int getRate(){
        Integer rNum = r.nextInt(100);
        if (rNum <= 80){
            return rateList.get(0);
        }
        else if (rNum <= 90){
            return rateList.get(1);
        }
        else if (rNum < 100) {
            return rateList.get(2);
        }
        else{
            return 0;
        }
    }
    
    public double getAvailable(){
        if (rNum <= 80){
            return availabilityList.get(0);
        }
        else if (rNum <= 90){
            return availabilityList.get(1);
        }
        else if (rNum < 100) {
            return availabilityList.get(2);
        }
        else {
            return 0.0;
        }
    }
    

}