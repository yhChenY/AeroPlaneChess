package GAMING;

import static GAMING.Main.*;

import gui.GameResultDialog;
import gui.PrizeFrame;

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
    Main.playerTurnStart(getNowPlayer());
    if (loaded) {
      loadData(datas);
    }
    int roundCnt = 0;
    if (!isOnLineGame) {
      players[0].setHuman();
      while (nowRank < 4) {
        try {
          //dividing line
          if(!Main.getNowPlayer().isWined()){
            new startATurn(nowPlayer, 1).start();
            while (!finishOneTurn) {
              Thread.sleep(50);
            }
            if (sum >= 10) {
              new startATurn(nowPlayer, 2).start();
              while (!finishOneTurn) {
                Thread.sleep(50);
              }
            }
            if (sum >= 10) {
              new startATurn(nowPlayer, 3).start();
              while (!finishOneTurn) {
                Thread.sleep(50);
              }
            }
            Thread.sleep(500);
          }
          nextTurn();
          //dividing line
        } catch (InterruptedException e) {
          System.out.println("Thread " + threadName + " interrupted.");
        }
      }
      new GameResultDialog(Main.getPlayerByColor(myColor).getRank() < 4);
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
