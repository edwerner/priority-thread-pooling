import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

  /**
   * Main entry point for thread pooling and scheduling application
   * 
   * @param args
   * @throws ExecutionException
   * @throws InterruptedException
   */
  public static void main(String[] args) throws InterruptedException, ExecutionException {
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    Future<String> future = executorService.submit(() -> "Thread exec");
    String result = future.get();
    System.out.println(result);
  }
}