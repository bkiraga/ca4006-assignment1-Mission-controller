import java.util.HashMap;
import java.util.concurrent.*;
import java.util.Random;

public class Controller {

    public static ExecutorService missionPool;
    int missionCount;

    public Controller() {
        missionPool = Executors.newFixedThreadPool(10);
    }

    //we assume that space crfaft doesnt use its space communication before launch
    public HashMap<String, Integer> constructMissionComponents(int thrusterCount, int instrumentCount, int controlSystemCount, int powerplantCount) {
        HashMap<String, Integer> components = new HashMap<String, Integer>();
        components.put("thruster", thrusterCount);
        components.put("instrument", instrumentCount);
        components.put("controlSystem", controlSystemCount);
        components.put("powerplant", powerplantCount);
        return components;
    }
    
    public void launchMission() {
        Random r = new Random();

        //random launch time
        long startTime = r.nextInt(10) + (System.currentTimeMillis() / 1000);

        //random amount of fuel
        int fuel = r.nextInt(10) + 10;

        //destination is a funtion of the fuel carried
        int destination = fuel * 2;

        //longer missions require more thrusters
        int thrusterCount;
        if (fuel < 1000){
            thrusterCount = r.nextInt(2) + 1;
        } else {
            thrusterCount = r.nextInt(2) + 3;
        }

        //variable number of instruments, control systems and powerplants in each mission
        int instrumentCount = rand.nextInt(4) + 1;
        int controlSystemCount = rand.nextInt(2) + 1;
        int powerplantCount = rand.nextInt(2) + 1;

        HashMap<String, Integer> components = constructMissionComponents(thrusterCount, instrumentCount, controlSystemCount, powerplantCount);
        Mission mission = new Mission(startTime, destination, components);
        missionPool.execute(mission);
    }

    public void sendInstructions() {
        String instructs[] = new String[] {"Mission ID: " + Thread.currentThread().getId() + " - check bearing","Mission ID: " + Thread.currentThread().getId() + " - report fuel reserve.", "Mission ID: " + Thread.currentThread().getId() + " - engage teriary thrusters."};

    	int randElement = r.nextInt(instructs.length);

    	System.out.println(instructs[randElement]);


    }


	public static void sendReports(Component components){

		int[] componentList = components.components;

		String[] reports = new String[] {"Mission Component Fuel with (Thread ID) " + Thread.currentThread().getId() + " - fuelr reserve level: " + componentList[] + " Time: " + Mission.startTime,
		"Mission Component Control Systems with (Thread ID) " + Thread.currentThread().getId() + " - control systems status at : " + Mission.startTime,
		"Mission Component Thrusters with (Thread ID) " + Thread.currentThread().getId() + " - thruster systems status at : " + Mission.startTime,
		"Mission Component Powerplant Systems with (Thread ID) " + Thread.currentThread().getId() + " - powerplant systems status at : " + Mission.startTime
		};
		
		int randElement = r.nextInt(reports.length);
    	System.out.println(reports[randomElement]);
	}

	public static void instrumentData(Component components){

        //currently no list of instruments, is a basic component
		//String[] instrumentList = components.instruments;

		String[] reports = new String[] {"Mission ID: " + Thread.currentThread().getId() + " - " + instrumentList[0] + ": temperature levels stable", "Mission ID: " + Thread.currentThread().getId() + " - " + instrumentList[1] + ": no nearby floating debris"};
		int randElement = r.nextInt(reports.length);
    	System.out.println(reports[randElement]);
	}



    public static void main(String[] args){
        Controller controller = new Controller();
        controller.launchMission();
        controller.launchMission();
        controller.launchMission();
        controller.launchMission();
        missionPool.shutdown();
    }
}