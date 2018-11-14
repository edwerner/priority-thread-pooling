import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Scheduler {

  private static ExecutorService executorService;
  private static final int THREAD_COUNT = 10;
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
	TaskThread thermostat = new TaskThread("Thermostat", 50, 1);
	taskList.add(thermostat);
	TaskThread windshieldWipers = new TaskThread("Windshield Wipers", 30,9);
    taskList.add(windshieldWipers);
	TaskThread cruiseControl = new TaskThread("Cruise Control", 20, 6);
    taskList.add(cruiseControl);
	TaskThread seatBeltSensor = new TaskThread("Seat Belt Sensor", 27, 7);
    taskList.add(seatBeltSensor);
	TaskThread gasolineMeter = new TaskThread("Gasoline Meter", 39, 8);
    taskList.add(gasolineMeter);
	TaskThread rpmMeter = new TaskThread("RPM Meter", 42, 8);
    taskList.add(rpmMeter);
	TaskThread speedometer = new TaskThread("Speedometer", 20, 10);
    taskList.add(speedometer);
	TaskThread laneAssist = new TaskThread("Lane Assist", 37, 4);
    taskList.add(laneAssist);
	TaskThread autoBrake = new TaskThread("Auto Brake", 21, 10);
    taskList.add(autoBrake);
	TaskThread obstacleDetection = new TaskThread("Obstacle Detection", 36, 9);
    taskList.add(obstacleDetection);
    
    for (TaskThread thread : taskList) {
      executorService.submit(() -> thread.start());
    }
  }

 
}
