public class TaskThread extends Thread{

  private String name;
  private int timeToComplete;
  private int priority;

  public TaskThread(String name, int timeToComplete, int priority) {
    this.name = name;
    this.timeToComplete = timeToComplete;
    this.priority = priority;
    Thread.currentThread().setPriority(priority);
  }
  
  public void run() {
	  System.out.println("***************************" + name + " initialization started.************************");
	  for(int i = timeToComplete; i > -1; i--) {
            System.out.println(name + " - priority: " + priority + " - count remaining: " + i);
	  }
      System.out.println("***************************" + name + " initialization completed.************************");
  }

}