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
    Main.playerTurnStart(players[0]);
    while (nowRank < 4) {
      try {
        //dividing line
        Thread.sleep(1);
        if (!isOnLineGame) {
          while (nowRank < 4) {
            if (nowPlayer == Color.RED) {
              new waitOpeThread(nowPlayer + "waitOpe").start();
              
              while (!hasGotOpe) {
                Thread.sleep(20);
                if (setOffInTurn) {
                  hasGotOpe = false;
                  break;
                }
              }
              new waitPlaneThread(nowPlayer + "waitPlane").start();
              while (!hasGotPlane) {
                Thread.sleep(20);
                if (setOffInTurn || (Main.getNowPlayer().getToBeSetOff() == 4)) {
                  break;
                }
              }
              if (setOffInTurn || (Main.getNowPlayer().getToBeSetOff() == 4)) {
                setOffInTurn = false;
              } else {
//                plane.run(chosenStep);
              }
            } else {
              if ((roll1 == 6 || roll2 == 6) && Main.getNowPlayer().getToBeSetOff() > 0) {
                Main.getNowPlayer().setOffOnePlane();
              } else {
                chosenStep = Math.max(product, sum);
                Plane p = Main.getNowPlayer().tryGetOnePlane();
                if (p != null) {
                  p.run(chosenStep);
                }
              }
              Main.getMainMenu().getGame().flushGameFrame();
            }
            Thread.sleep(2000);
            nextTurn();
          }
        }
        //dividing line
      } catch (InterruptedException e) {
        System.out.println("Thread " + threadName + " interrupted.");
      }
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
