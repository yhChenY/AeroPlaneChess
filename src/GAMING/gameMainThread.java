package GAMING;

import static GAMING.Main.*;

import chatroom.Client;
import gui.PrizeFrame;
import java.io.IOException;

public class gameMainThread extends Thread {
  private Thread t;
  private final String threadName;
  private Client client;
  private String selfColor; // in small letter
  
  public gameMainThread(String name) {
    threadName = name;
    System.out.println("Creating " + threadName);
  }

  public void setClient(Client client) {
    this.client = client;
  }

  public void run() {
    System.out.println("Running " + threadName);
    Main.initializeData();
    Main.playerTurnStart(players[0]);
    int roundCnt = 0;
    while (nowRank < 4) {
      try {
        //dividing line
        Thread.sleep(1);
        if (!isOnLineGame) {
          if (nowPlayer == Color.RED) {
            Main.getMainMenu().getGame().flushGameFrame();
            roundCnt++;
            System.out.println(roundCnt);
            if (roundCnt != 0 && roundCnt % 3 == 0) {
              PrizeFrame prizeFrame = new PrizeFrame();
              String finalPrize = prizeFrame.getFinalPrize();
              String chosePlane = prizeFrame.getChosePlane();
            }
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
            System.out.println(nowPlayer + " AI turn start.");
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
          Thread.sleep(50);
          nextTurn();
          
        } else {
          // 在线模式
          String[] newGameData = client.getNewGameData();
          String[] newGameDataArray = newGameData[2].split("'");
          // 此处更新自己的数据
          Main.getMainMenu().getGame().flushGameFrame();
          if (!newGameData[0].equals(getFormerPlayer())) {
            continue;
          }
          roundCnt++;
          if (roundCnt != 0 && roundCnt % 3 == 0) {
            PrizeFrame prizeFrame = new PrizeFrame();
            String finalPrize = prizeFrame.getFinalPrize();
            String chosePlane = prizeFrame.getChosePlane();
          }
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
          String gameData = selfColor;
          try{
            // 传输格式为"[gameData] SelfColor String\n" (String为目标游戏数据)
            client.transmit("[gameData]" + " " +  selfColor + " " + gameData + "\n", client.getSocket());
          } catch (IOException e) {
            e.printStackTrace();
          }

        }
        //dividing line
      } catch (InterruptedException e) {
        System.out.println("Thread " + threadName + " interrupted.");
      }
    }
    System.out.println("Thread " + threadName + " exiting.");
  }

  private String getFormerPlayer() {
    switch (selfColor) {
      case "red" -> {
        return "green";
      }
      case "yellow" -> {
        return "red";
      }
      case "blue" -> {
        return "yellow";
      }
      case "green" -> {
        return "blue";
      }
    }
    return null;
  }

  public void start() {
    System.out.println("Starting " + threadName);
    if (t == null) {
      t = new Thread(this, threadName);
      t.start();
    }
  }
}
