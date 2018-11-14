import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Scheduler {

  private static ExecutorService executorService;
  private static final int THREAD_COUNT = 3;
  private static List<TaskThread> taskList = new ArrayList<TaskThread>();

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
	
	//create and start threads
	TaskThread thermostat = new TaskThread("Thermostat", 10, 10);
	taskList.add(thermostat);
	TaskThread windshieldWipers = new TaskThread("Windshield Wipers", 20,9);
    taskList.add(windshieldWipers);
	TaskThread cruiseControl = new TaskThread("Cruise Control", 10, 8);
    taskList.add(cruiseControl);
	TaskThread seatBeltSensor = new TaskThread("Seat Belt Sensor", 10, 7);
    taskList.add(seatBeltSensor);
	TaskThread gasolineMeter = new TaskThread("Gasoline Meter", 10, 6);
    taskList.add(gasolineMeter);
	TaskThread rpmMeter = new TaskThread("RPM Meter", 10, 5);
    taskList.add(rpmMeter);
	TaskThread speedometer = new TaskThread("Speedometer", 10, 4);
    taskList.add(speedometer);
	TaskThread laneAssist = new TaskThread("Lane Assist", 10, 3);
    taskList.add(laneAssist);
	TaskThread autoBrake = new TaskThread("Auto Brake", 10, 2);
    taskList.add(autoBrake);
	TaskThread obstacleDetection = new TaskThread("Obstacle Detection", 10, 1);
    taskList.add(obstacleDetection);
    
    for (TaskThread thread : taskList) {
    	executorService.execute(thread);
    }
  }

 
}
