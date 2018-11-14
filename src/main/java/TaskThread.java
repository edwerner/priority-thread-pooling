public class TaskThread implements Runnable{

  private String name;
  private int timeToComplete;
  private Thread thread;
  private int priority;

  public TaskThread(String name, int timeToComplete, int priority) {
    this.priority = priority;
    this.name = name;
    this.timeToComplete = timeToComplete;
    this.setPriority(priority);
    System.out.println(name + " initialization started");
  }
  
  public void setPriority(int priority) {
    this.priority = priority;
  }
  
  public int getPriority() {
    return priority;
  }
  
  public void run() {
	  System.out.println("Running " +  name );
      try {
         for(int i = timeToComplete; i > 0; i--) {
            System.out.println(name + ": count remaining: " + i);
            Thread.sleep(500);
         }
      } catch (Exception e) {
         System.out.println(name + " failed to initialize");
      }
      System.out.println("***********************************************");
      System.out.println("* " + name + " initialization completed." + " *");
      System.out.println("***********************************************");
  }
  public void start () {
      System.out.println("Starting " +  name + " with count: " + timeToComplete);
      if (thread == null) {
         thread = new Thread (this, name);
         thread.start ();
      }
   }

}