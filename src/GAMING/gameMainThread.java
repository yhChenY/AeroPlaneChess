package GAMING;

import static GAMING.Main.*;

import gui.GameResultDialog;
import chatroom.Client;
import gui.Prize;
import gui.PrizeFrame;
import java.io.IOException;

public class gameMainThread extends Thread {

  private Thread t;
  private final String threadName;
  private Client client;
  private String selfColor;

  public gameMainThread(String name, Client client) {
    threadName = name;
    this.client = client;
    this.selfColor = client.getColor();
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
          PrizeFrame prizeFrame = new PrizeFrame();
          Plane p = Main.getNowPlayer().tryGetOnePlane();
          if (p != null) {
            if (prizeFrame.getFinalPrize() == "SWORD") {
              p.run(5);
            }
          }
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
          nextTurn();
          //dividing line
        } catch (InterruptedException e) {
          System.out.println("Thread " + threadName + " interrupted.");
        }
      }
    } else {
      for (int i = 0; i < 4; i++) {
        players[i].setHuman();
      }
      //在线模式
      System.out.println("online mode");
      //
      System.out.println(selfColor);
      //
      while (!client.isStart()) {
        try {
          sleep(10000);
          //
          System.out.println(client.isStart());
          //
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      while (nowRank < 4) {
        // 在线模式
        //
        System.out.println("testing1");
        //
        String[] newGameData = client.getNewGameData();// 获取最新的游戏数据
        //
        System.out.println("the former player is " + newGameData[0]);
        System.out.println("the game data is " + newGameData[1]);
        //
        if (!selfColor.equals("red") && newGameData[1] == null) { // 如果自己是红色，且还没新数据，证明是第一个人，直接开始
          try { // 如果不是红色 还没数据 ，直接等待
            sleep(5000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          continue;
        }
        //
        System.out.println("testing2");
        //
        // 如果开始有数据了
        if (newGameData[1] != null) {
          String[] newGameDataArray = newGameData[1].split("'");
        }
        // 此处更新自己的数据
        Main.getMainMenu().getGame().flushGameFrame();
        if (newGameData[0] != null && !newGameData[0].equals(getFormerPlayer())) {// 游戏数据存在，但你不是下一个玩家，你就wait！！
          //
          System.out.println("wait");
          //
          try {
            sleep(2000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          continue;
        }
        //
        System.out.println("match, testing3");
        //
        if (newGameData[1] != null) {
          String[] newGameDataArray = newGameData[1].split("'");
          for (String s : newGameDataArray) {
            System.out.println("the new gameData");
            System.out.println(s);
          }
        }
        //
        System.out.println("正式开始");
        //回合正式开始

        try {
          sleep(7);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        try {
          //dividing line
          if(!Main.getNowPlayer().isWined()){
//            new startATurn(nowPlayer, 1).start();
//            while (!finishOneTurn) {
//              Thread.sleep(50);
//            }
//            if (sum >= 10) {
//              new startATurn(nowPlayer, 2).start();
//              while (!finishOneTurn) {
//                Thread.sleep(50);
//              }
//            }
//            if (sum >= 10) {
//              new startATurn(nowPlayer, 3).start();
//              while (!finishOneTurn) {
//                Thread.sleep(50);
//              }
//            }
            PrizeFrame prizeFrame = new PrizeFrame();
            Plane p = Main.getNowPlayer().tryGetOnePlane();
            if (p != null) {
              if (prizeFrame.getFinalPrize() == "SWORD") {
                p.run(5);
              }
            }
            Thread.sleep(500);
          }
          //dividing line
        } catch (InterruptedException e) {
          System.out.println("Thread " + threadName + " interrupted.");
        }
        String gameData = "o'k'k";
        try {
          // 传输格式为"[gameData] SelfColor String\n" (String为目标游戏数据)
          System.out.print("the info is " + "[gameData]" + " " + selfColor + " " + gameData + "\n");
          client.transmit("[gameData]" + " " + selfColor + " " + gameData + "\n", client.getSocket());
          sleep(10000); // 防止马上读取游戏数据又进一次游戏
        } catch (IOException | InterruptedException e) {
          e.printStackTrace();
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
}
