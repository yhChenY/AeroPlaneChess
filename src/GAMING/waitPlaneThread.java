package GAMING;

public class waitPlaneThread implements Runnable {
  private Thread t;
  private String threadName;
  
  waitPlaneThread(String name) {
    threadName = name;
    System.out.println("Creating " + threadName);
  }
  
  @Override
  public void run() {
    System.out.println("Running " + threadName);
    try {
      while (!Main.hasGotPlane) {
        Thread.sleep(50);
        if (Main.hasGotPlane) {
          System.out.println(Main.nowPlayer + " Got Plane");
          break;
        }
      }
    } catch (InterruptedException e) {
      System.out.println("Thread " + threadName + " interrupted");
    }
  }
  
  public void start() {
    System.out.println("Starting " + threadName);
    if (t == null) {
      t = new Thread(this, threadName);
      t.start();
    }
  }
}
