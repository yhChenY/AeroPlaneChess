package GAMING;

import static GAMING.Main.*;

public class gameMainThread extends Thread {
  private Thread t;
  private final String threadName;
  
  public gameMainThread(String name) {
    threadName = name;
    System.out.println("Creating " + threadName);
  }
  
  public void run() {
    System.out.println("Running " + threadName);
    Main.initializeData();
    try {
      //dividing line
      Thread.sleep(1);
//      players[0] = new Player(Color.RED);
//      players[1] = new Player(Color.YELLOW);
//      players[2] = new Player(Color.BLUE);
//      players[3] = new Player(Color.GREEN);
      Main.playerTurnStart(players[0]);
      if (!isOnLineGame) {
        while (nowRank<4) {
          new waitOpeThread(nowPlayer + "waitOpe").start();
          while (!hasGotOpe) {
            Thread.sleep(20);
          }
          new waitPlaneThread(nowPlayer + "waitPlane").start();
          while (!hasGotPlane) {
            Thread.sleep(20);
          }
          System.out.println("run!!!!");
          plane.run(chosenStep);
          nextTurn();
        }
      }
      //dividing line
    } catch (InterruptedException e) {
      System.out.println("Thread " + threadName + " interrupted.");
    }
    System.out.println("Thread " + threadName + " exiting.");
  }
  
  public void start() {
    System.out.println("Starting " + threadName);
    if (t == null) {
      t = new Thread(this, threadName);
      t.start();
    }
  }
}
