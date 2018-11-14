import java.util.concurrent.ExecutionException;

public class Scheduler {

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
	
	//create and start threads
	TaskThread thermostat = new TaskThread("Thermostat", 50, 1);
	thermostat.start();
	TaskThread windshieldWipers = new TaskThread("Windshield Wipers", 30,9);
	windshieldWipers.start();
	TaskThread cruiseControl = new TaskThread("Cruise Control", 20, 6);
	cruiseControl.start();
	TaskThread seatBeltSensor = new TaskThread("Seat Belt Sensor", 27, 7);
	seatBeltSensor.start();
	TaskThread gasolineMeter = new TaskThread("Gasoline Meter", 39, 8);
	gasolineMeter.start();
	TaskThread rpmMeter = new TaskThread("RPM Meter", 42, 8);
	rpmMeter.start();
	TaskThread speedometer = new TaskThread("Speedometer", 20, 10);
	speedometer.start();
	TaskThread laneAssist = new TaskThread("Lane Assist", 37, 4);
	laneAssist.start();
	TaskThread autoBrake = new TaskThread("Auto Brake", 21, 10);
	autoBrake.start();
	TaskThread obstacleDetection = new TaskThread("Obstacle Detection", 36, 9);
	obstacleDetection.start();

  }

 
}
