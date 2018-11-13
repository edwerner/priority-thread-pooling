import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Scheduler {

  final static int THREAD_COUNT = 10;
  private static ExecutorService executorService;

  public static void main(String[] args) throws InterruptedException, ExecutionException {

    executorService = Executors.newFixedThreadPool(THREAD_COUNT);

    // natural ordering example of priority queue
    Queue<Integer> integerPriorityQueue = new PriorityQueue<>(THREAD_COUNT);
    Random rand = new Random();
    for (int i = 0; i < THREAD_COUNT; i++) {
      integerPriorityQueue.add(new Integer(rand.nextInt(100)));
    }
    initializePriorityQueue();
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
    Random rand = new Random();
    for (int i = 0; i < THREAD_COUNT; i++) {
      int id = rand.nextInt(100);
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
      };
    }
  }

  public static void executeTask(int id) throws InterruptedException, ExecutionException {
    Future<String> future = executorService.submit(() -> "Thread executed with priority " + id);
    String result = future.get();
    System.out.println(result);
    Thread.sleep(1000);
  }
}
