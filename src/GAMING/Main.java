package GAMING;

import gui.Game;
import gui.MainMenu;
import utils.*;

import java.io.File;
import java.io.FileWriter;

import static GAMING.Color.*;

public class Main {
  static int nowRank = 1;
  static int roll1;
  static int roll2;
  static int big;
  static int small;
  static int sum;
  static int quotient;
  static int sub;
  static int product;
  static boolean ableToProduct;
  static boolean ableToQuotient;
  static boolean isOnLineGame = false;
  static char ope;
  static boolean hasGotOpe = false;
  static boolean hasGotPlane = false;
  static Player[] players = new Player[4];
  static Plane plane = null;
  static int chosenStep = 1;
  public static Color nowPlayer = RED;
  public static MainMenu mainMenu;
  public static boolean setOffInTurn = false;
  
  public static void playerWin(Player player) {
    player.setRank(nowRank);
    nowRank++;
  }
  
  static {
    MapSystem.loadBlocks();
    System.out.println("载入block完毕");
    players[0] = new Player(RED);
    players[1] = new Player(Color.YELLOW);
    players[2] = new Player(Color.BLUE);
    players[3] = new Player(Color.GREEN);
  }
  
  public static void main(String[] args) {

//    mainMenu = new MainMenu();
//    if (!isOnLineGame) {
//      players[0].setHuman();
//      do {
//        for (int i = 0; i < 4; i++) {
//          Player p = players[i];
//          if (p.isWined()) {
//            continue;
//          }
//          roll1 = util.random(1, 6);
//          roll2 = util.random(1, 6);
//          big = Math.max(roll1, roll2);
//          small = Math.min(roll1, roll2);
//          sum = roll1 + roll2;
//          product = roll1 * roll2;
//          ableToProduct = product <= 12;
//          sub = big - small;
//          ableToQuotient = big % small == 0;
//          quotient = ableToQuotient ? big / small : 0;
//          if ((roll1 == 6 || roll2 == 6) && p.getToBeSetOff() > 0) {
//            // 是否选择上飞机？
//            if (p.getToBeSetOff() > 0)
//              //如果选择 是  上飞机
//
//              continue;
//            //如果选择否
//          }
//          //获取操作//此处考虑操作的不可行性
//          if (hasGotOpe) {
//            hasGotOpe = false;
//            //更新chosenStep
//            switch (ope) {
//              case '+':
//                chosenStep = sum;
//                System.out.println("Got ope and chosenStep");
//                break;
//              case '-':
//                chosenStep = sub;
//                break;
//              case '*':
//                chosenStep = product;
//                break;
//              case '/':
//                chosenStep = quotient;
//                break;
//            }
//
//            break;
//          }
//          System.out.println(66666);
//          //获取plane 鼠标点击
//        }
//        //whether the game has finished
//      } while (nowRank != 4);
//      //显示结算框
//      //更新 存储 账户数据
//
//      //关闭当前游戏界面
//      //重启新的游戏界面
//    }
  }
  
  private void flyOneTime(Player player, int time) {
  
  }
  
  public static int getRoll1() {
    return roll1;
  }
  
  public static int getRoll2() {
    return roll2;
  }
  
  public static int getBig() {
    return big;
  }
  
  public static int getSmall() {
    return small;
  }
  
  public static int getSum() {
    return sum;
  }
  
  public static int getQuotient() {
    return quotient;
  }
  
  public static int getSub() {
    return sub;
  }
  
  public static int getProduct() {
    return product;
  }
  
  public static boolean isAbleToProduct() {
    return ableToProduct;
  }
  
  public static boolean isAbleToQuotient() {
    return ableToQuotient;
  }
  
  public static Player[] getPlayers() {
    return players;
  }
  
  public static void setIsOnLineGame(boolean isOnLineGame) {
    Main.isOnLineGame = isOnLineGame;
  }
  
  public static void setOpe(char o) {
    ope = '+';
    if (o == '-' || o == '*' || o == '/') {
      ope = o;
    }
    System.out.println("set ope = " + ope);
  }
  
  /**
   * used with setOpe()... right after it.
   *
   * @param hasGotOpe true/false
   */
  public static void setHasGotOpe(boolean hasGotOpe) {
    Main.hasGotOpe = hasGotOpe;
    System.out.println("setHasGotOpe " + hasGotOpe);
  }
  
  public static void setPlane(Plane plane) {
    Main.plane = plane;
  }
  
  /**
   * used with setPlane()... right after it.
   *
   * @param hasGotPlane true/false
   */
  public static void setHasGotPlane(boolean hasGotPlane) {
    Main.hasGotPlane = hasGotPlane;
  }
  
  public static int getChosenStep() {
    return chosenStep;
  }
  
  public static void gameStart() {
    {
      players[0].setHuman();
      do {
        for (int i = 0; i < 4; i++) {
          Player p = players[i];
          if (p.isWined()) {
            continue;
          }
          roll1 = util.random(1, 6);
          roll2 = util.random(1, 6);
          big = Math.max(roll1, roll2);
          small = Math.min(roll1, roll2);
          sum = roll1 + roll2;
          product = roll1 * roll2;
          ableToProduct = product <= 12;
          sub = big - small;
          ableToQuotient = big % small == 0;
          quotient = ableToQuotient ? big / small : 0;
          if ((roll1 == 6 || roll2 == 6) && p.getToBeSetOff() > 0) {
            // 是否选择上飞机？
            if (p.getToBeSetOff() > 0)
              //如果选择 是  上飞机
              
              continue;
            //如果选择否
          }
          //获取操作//此处考虑操作的不可行性
          if (hasGotOpe) {
            hasGotOpe = false;
            //更新chosenStep
            switch (ope) {
              case '+':
                chosenStep = sum;
                System.out.println("Got ope and chosenStep");
                break;
              case '-':
                chosenStep = sub;
                break;
              case '*':
                chosenStep = product;
                break;
              case '/':
                chosenStep = quotient;
                break;
            }
            
            break;
          }
          System.out.println(6);
          //获取plane 鼠标点击
        }
        //whether the game has finished
      } while (nowRank != 4);
      //显示结算框
      //更新 存储 账户数据
      //关闭当前游戏界面
      //重启新的游戏界面
    }
  }
  
  public static void playerTurnStart(Player player) {
    nowPlayer = player.color;
    System.out.println(nowPlayer + " Turn Started.");
    roll();
    hasGotOpe = false;
    hasGotPlane = false;
  }
  
  public static void nextTurn() {
    System.out.println(nowPlayer + "Turn Finished");
    if (nowPlayer == RED) {
      playerTurnStart(players[1]);
    } else if (nowPlayer == Color.YELLOW) {
      playerTurnStart(players[2]);
    } else if (nowPlayer == Color.BLUE) {
      playerTurnStart(players[3]);
    } else {
      playerTurnStart(players[0]);
    }
  }
  
  public static void initializeData() {
    nowRank = 1;
    roll1 = 1;
    roll2 = 1;
    big = Math.max(roll1, roll2);
    small = Math.min(roll1, roll2);
    sum = roll1 + roll2;
    sub = big - small;
    product = roll1 * roll2 > 12 ? roll1 * roll2 : 0;
    ableToProduct = !(product == 0);
    quotient = big % small == 0 ? big / small : 0;
    ableToQuotient = !(quotient == 0);
    isOnLineGame = false;
    ope = '+';
    hasGotOpe = false;
    hasGotPlane = false;
    setOffInTurn = false;
    for (Player p : players) {
      p.initialize();
    }
    plane = null;
    chosenStep = 1;
    nowPlayer = RED;
  }
  
  public static MainMenu getMainMenu() {
    return mainMenu;
  }
  
  public static void saveGameData() {
    try {
      File file = new File("resources/savedGame.yaml");
      if (!file.exists()) {
        file.createNewFile();
      }
      FileWriter fw = new FileWriter(file, true);
      fw.write(dataToString());
      fw.close();
    } catch (Exception ignored) {
      System.out.println("Save data failed!!!");
    }
    System.out.println("Save data successfully.");
  }
  
  public static String dataToString() {
    return "\n" +
        "- \n" +
        " nowPlayer: " + nowPlayer + " \n" +
        " nowRank: " + nowRank + " \n" + " "
        + players[0].toString()
        + players[1].toString()
        + players[2].toString()
        + players[3].toString();
  }
  
  public static void roll() {
    roll1 = util.random(1, 6);
    roll2 = util.random(1, 6);
    big = Math.max(roll1, roll2);
    small = Math.min(roll1, roll2);
    sum = roll1 + roll2;
    product = roll1 * roll2;
    ableToProduct = product <= 12;
    sub = big - small;
    ableToQuotient = big % small == 0;
    quotient = ableToQuotient ? big / small : 0;
  }
  
  public static Player getPlayerByColor(Color color) {
    if (color == RED) {
      return players[0];
    } else if (color == YELLOW) {
      return players[1];
    } else if (color == BLUE) {
      return players[2];
    } else return players[3];
  }
  
  public static Player getNowPlayer() {
    return getPlayerByColor(nowPlayer);
  }
}
