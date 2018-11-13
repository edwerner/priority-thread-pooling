import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Scheduler {

  final static int THREAD_COUNT = 10;
  private static ExecutorService executorService;
  private static List<String> processList = new ArrayList<String>();

  public static void main(String[] args) throws InterruptedException, ExecutionException {

    executorService = Executors.newFixedThreadPool(THREAD_COUNT);

    // natural ordering example of priority queue
    Queue<Integer> integerPriorityQueue = new PriorityQueue<>(THREAD_COUNT);
    for (int i = 0; i < THREAD_COUNT; i++) {
      integerPriorityQueue.add(randInt(0, 9));
    }
    initializeProcessList();
    initializePriorityQueue();
  }
  
  private static void initializeProcessList() {
    processList.add("THERMOSTAT");
    processList.add("WINDSHIELD_WIPERS");
    processList.add("CRUISE_CONTROL");
    processList.add("SEAT_BELT_SENSOR");
    processList.add("GASOLINE_METER");
    processList.add("RPM_METER");
    processList.add("SPEEDOMETER");
    processList.add("LANE_ASSIST");
    processList.add("AUTO_BRAKE");
    processList.add("OBSTACLE_DETECTION");
  }

  private static void initializePriorityQueue() throws InterruptedException, ExecutionException {
    // PriorityQueue example with Comparator
    Queue<Task> taskPriorityQueue = new PriorityQueue<>(THREAD_COUNT, idComparator);
    addTasksToQueue(taskPriorityQueue);
    pollTasksFromQueue(taskPriorityQueue);
  }

  // Comparator anonymous class implementation
  public static Comparator<Task> idComparator = new Comparator<Task>() {

    @Override
    public int compare(Task t1, Task t2) {
      return (int) (t2.getId() - t1.getId());
    }
  };

  // utility method to add random data to Queue
  private static void addTasksToQueue(Queue<Task> taskPriorityQueue) {
    for (int i = 0; i < THREAD_COUNT; i++) {
      int id = randInt(0, 9);
      taskPriorityQueue.add(new Task(id, "Thread_ " + id));
    }
  }

  // utility method to poll data from queue
  private static void pollTasksFromQueue(Queue<Task> taskPriorityQueue)
      throws InterruptedException, ExecutionException {
    int counter = 0;
    while (counter <= THREAD_COUNT) {
      Task task = taskPriorityQueue.poll();
      if (task == null) {
        break;
      }
      executeTask(task.getId());
      counter++;

      if (counter == THREAD_COUNT) {
        initializePriorityQueue();
      }
      ;
    }
  }

  public static int randInt(int min, int max) {
    Random rand = new Random();
    int randomNum = rand.nextInt((max - min) + 1) + min;
    return randomNum;
  }

  public static void executeTask(int id) throws InterruptedException, ExecutionException {
    Future<String> future = executorService.submit(() ->  processList.get(id) + " thread executed with priority " + id);
    String result = future.get();
    System.out.println(result);
    Thread.sleep(1000);
  }
}
