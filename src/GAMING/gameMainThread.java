package GAMING;

public class gameMainThread extends Thread {
  private Thread t;
  private final String threadName;
  
  gameMainThread( String name) {
    threadName = name;
    System.out.println("Creating " +  threadName );
  }
  
  public void run() {
    System.out.println("Running " +  threadName );
    try {
      Thread.sleep(1);
      
      
      
      
      
    }catch (InterruptedException e) {
      System.out.println("Thread " +  threadName + " interrupted.");
    }
    System.out.println("Thread " +  threadName + " exiting.");
  }
  
  public void start () {
    System.out.println("Starting " +  threadName );
    if (t == null) {
      t = new Thread (this, threadName);
      t.start ();
    }
  }
}
