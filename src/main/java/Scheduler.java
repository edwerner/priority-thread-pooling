import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Scheduler {

  private static ExecutorService executorService;
  private static final int THREAD_COUNT = 10;
  private static PriorityQueue<TaskThread> priorityQueue = new PriorityQueue<TaskThread>(new TaskThreadComparator());

  /**
   * Initializes ExecutorService, creates new thread pool, populates integer
   * priority queue initializes process list and priority queue
   * 
   * @param args
   * @throws InterruptedException
   * @throws ExecutionException
   */
  public static void main(String[] args) throws InterruptedException, ExecutionException {
	
	System.out.println("\n\nCar Initialization Started\n");
	
	executorService = Executors.newFixedThreadPool(THREAD_COUNT);
	initializePriorityQueue();
    
    while (priorityQueue.size() > 0) {
    	executorService.execute(priorityQueue.poll());
    }
  }  
  public static void initializePriorityQueue() {
		//create the threads
		TaskThread seatBeltSensor = new TaskThread("Seat Belt Sensor", 15, 7);
		TaskThread gasolineMeter = new TaskThread("Gasoline Meter", 20, 6);
		TaskThread rpmMeter = new TaskThread("RPM Meter", 4, 5);
		TaskThread cruiseControl = new TaskThread("Cruise Control", 3, 8);
		TaskThread windshieldWipers = new TaskThread("Windshield Wipers", 20,1);
		TaskThread speedometer = new TaskThread("Speedometer", 16, 4);
		TaskThread laneAssist = new TaskThread("Lane Assist", 27, 3);
		TaskThread thermostat = new TaskThread("Thermostat", 23, 9);
		TaskThread autoBrake = new TaskThread("Auto Brake", 8, 2);
		TaskThread obstacleDetection = new TaskThread("Obstacle Detection", 3, 10);
		TaskThread wheelSensor = new TaskThread("Wheel Sensor", 18, 2);
		TaskThread gps = new TaskThread("GPS", 20, 3);
		TaskThread radio = new TaskThread("Radio", 15, 10);
		TaskThread airConditioning = new TaskThread("Air Conditioning", 18, 1);
		TaskThread gasSensor = new TaskThread("Gas Sensor", 9, 1);
		TaskThread oxygenSensor = new TaskThread("Oxygen Sensor", 7, 3);
		TaskThread fuelTemperature = new TaskThread("Fuel Temperature", 25, 2);
		TaskThread voltageSensor = new TaskThread("Voltage Sensor", 23, 10);
		TaskThread pressureSensor = new TaskThread("Pressure Sensor", 7, 10);
		TaskThread sparkKnockSensor = new TaskThread("Spark Knock Sensor", 8, 2);
		TaskThread emissionControls = new TaskThread("Emission Controls", 18, 10);
		TaskThread massAirFlowSensor = new TaskThread("Mass Air Flow Sensor", 12, 9);
		TaskThread manifoldAbsolutePressureSensor = new TaskThread("Manifold Absolute Pressure Sensor", 3, 8);
		TaskThread powerSteering = new TaskThread("Power Steering", 18, 3);
		TaskThread brakes = new TaskThread("Brakes", 28, 1);
		TaskThread brakesSensor = new TaskThread("Brakes Sensor", 3, 2);
		TaskThread suspensionSensor = new TaskThread("Suspension Sensor", 8, 6);
		TaskThread headlights = new TaskThread("Headlights", 9, 6);
		TaskThread highBeams = new TaskThread("High Beams", 19, 3);
		TaskThread wipers = new TaskThread("Wipers", 18, 5);
		TaskThread mirrors = new TaskThread("Mirrors", 15, 10);
		TaskThread seatWarmers = new TaskThread("Seat Warmers", 18, 10);
		TaskThread seatAdjustmentSystem = new TaskThread("Seat Adjustment System", 18, 10);
	
		//add the elements into the priority Queue
		priorityQueue.add(obstacleDetection);
		priorityQueue.add(seatBeltSensor);
		priorityQueue.add(gasolineMeter);
		priorityQueue.add(rpmMeter);
		priorityQueue.add(cruiseControl);
		priorityQueue.add(thermostat);
		priorityQueue.add(windshieldWipers);
		priorityQueue.add(speedometer);
		priorityQueue.add(laneAssist);
		priorityQueue.add(autoBrake);
		priorityQueue.add(wheelSensor);
		priorityQueue.add(gps);
		priorityQueue.add(radio);
		priorityQueue.add(airConditioning);
		priorityQueue.add(gasSensor);
		priorityQueue.add(oxygenSensor);
		priorityQueue.add(fuelTemperature);
		priorityQueue.add(voltageSensor);
		priorityQueue.add(pressureSensor);
		priorityQueue.add(sparkKnockSensor);
		priorityQueue.add(emissionControls);
		priorityQueue.add(massAirFlowSensor);
		priorityQueue.add(manifoldAbsolutePressureSensor);
		priorityQueue.add(powerSteering);
		priorityQueue.add(brakes);
		priorityQueue.add(brakesSensor);
		priorityQueue.add(suspensionSensor);
		priorityQueue.add(headlights);
		priorityQueue.add(highBeams);
		priorityQueue.add(wipers);
		priorityQueue.add(mirrors);
		priorityQueue.add(seatWarmers);
		priorityQueue.add(seatAdjustmentSystem);
  }
  
}

class TaskThreadComparator implements Comparator<TaskThread> {

	@Override
	public int compare(TaskThread o1, TaskThread o2) {
		if( o1.getPriority() < o2.getPriority()) 
			return 1;
		return -1;
	}
	  
 }

