import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    // create and start threads
    TaskThread thermostat = new TaskThread("Thermostat", 50, 1);
    taskList.add(thermostat);
    TaskThread windshieldWipers = new TaskThread("Windshield Wipers", 30, 9);
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
    
    initializePriorityQueue();
  }
  
  public static void initializePriorityQueue() throws InterruptedException, ExecutionException {
    Queue<TaskThread> taskPriorityQueue = new PriorityQueue<>(THREAD_COUNT, idComparator);
    addTasksToQueue(taskPriorityQueue);
    pollTasksFromQueue(taskPriorityQueue);

    Queue<Integer> integerPriorityQueue = new PriorityQueue<>(THREAD_COUNT);
    for (int i = 0; i < THREAD_COUNT; i++) {
      integerPriorityQueue.add(randInt(0, 9));
    }
  }

  public static Comparator<TaskThread> idComparator = new Comparator<TaskThread>() {

    @Override
    public int compare(TaskThread t1, TaskThread t2) {
      return (int) (t2.getPriority() - t1.getPriority());
    }
  };

  private static void addTasksToQueue(Queue<TaskThread> taskPriorityQueue) {
    for (int i = 0; i < THREAD_COUNT; i++) {
      int randomIndex = randInt(0, 9);
      taskPriorityQueue.add(taskList.get(randomIndex));
    }
  }
  
  private static void pollTasksFromQueue(Queue<TaskThread> taskPriorityQueue)
      throws InterruptedException, ExecutionException {
    int counter = 0;
    while (counter <= THREAD_COUNT) {
      TaskThread task = taskPriorityQueue.poll();
      if (task == null) {
        break;
      }
      executeTask();
      counter++;

//      if (counter == THREAD_COUNT) {
//        initializePriorityQueue();
//      }
    }
  }
  
  public static void executeTask() throws InterruptedException, ExecutionException {  
    for (TaskThread thread : taskList) {
      executorService.submit(() -> thread.start());
    }
  }

  public static int randInt(int min, int max) {
    Random rand = new Random();
    int randomNum = rand.nextInt((max - min) + 1) + min;
    return randomNum;
  }
}
