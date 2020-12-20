package GAMING;

public class waitOpeThread implements Runnable {
  private Thread t;
  private String threadName;
  
  waitOpeThread(String name) {
    threadName = name;
    System.out.println("Creating " + threadName);
  }
  
  @Override
  public void run() {
    System.out.println("Running " + threadName);
    try {
      while (!Main.hasGotOpe) {
        Thread.sleep(50);
        Main.roll();
        if (Main.hasGotOpe) {
          if(Main.getRoll1()==6||Main.getRoll2()==6){
            Main.getPlayerByColor(Main.nowPlayer).setOffOnePlane();
          }
          switch (Main.ope) {
            case '+':
              Main.chosenStep = Main.sum;
              break;
            case '-':
              Main.chosenStep = Main.sub;
              break;
            case '*':
              Main.chosenStep = Main.product;
              break;
            case '/':
              Main.chosenStep = Main.quotient;
              break;
          }
          System.out.println(Main.nowPlayer + " Got Ope");
        }
      }
    } catch (
        InterruptedException e) {
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
