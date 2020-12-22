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
  public static boolean isOnLineGame = false;
  static char ope;
  static boolean hasGotOpe = false;
  static boolean hasGotPlane = false;
  static Player[] players = new Player[4];
  static Plane plane = null;
  static int chosenStep = 1;
  public static Color nowPlayer = RED;
  public static MainMenu mainMenu;
  public static boolean setOffInTurn = false;
  public static boolean finishOneTurn = false;
  public Color myColor;
  public static boolean loaded = false;
  public static Datas datas;
  
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
//    System.out.println("setHasGotOpe " + hasGotOpe);
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
  
  public static void playerTurnStart(Player player) {
    mainMenu.getGame().flushGameFrame();
    nowPlayer = player.color;
    System.out.println(nowPlayer + " Turn Started.");
  }
  
  public static void nextTurn() {
    mainMenu.getGame().flushGameFrame();
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
    loaded = false;
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
    product = product > 12 ? 0 : product;
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
  
  public static void loadData(Datas datas){
    players[0].setHasFinished(datas.REDhasFinished);
    players[0].setToBeFinished(datas.REDtoBeFinished);
    players[0].setToBeSetOff(datas.REDtoBeSetOff);
    players[0].getPlanes()[0].hasSetOff = datas.RED0hasSetOff;
    players[0].getPlanes()[0].setPosition(MapSystem.getNthBlock(datas.RED0position));
    players[0].getPlanes()[1].hasSetOff = datas.RED1hasSetOff;
    players[0].getPlanes()[1].setPosition(MapSystem.getNthBlock(datas.RED1position));
    players[0].getPlanes()[2].hasSetOff = datas.RED2hasSetOff;
    players[0].getPlanes()[2].setPosition(MapSystem.getNthBlock(datas.RED2position));
    players[0].getPlanes()[3].hasSetOff = datas.RED3hasSetOff;
    players[0].getPlanes()[3].setPosition(MapSystem.getNthBlock(datas.RED3position));
    players[1].setHasFinished(datas.YELLOWhasFinished);
    players[1].setToBeFinished(datas.YELLOWtoBeFinished);
    players[1].setToBeSetOff(datas.YELLOWtoBeSetOff);
    players[1].getPlanes()[0].hasSetOff = datas.YELLOW0hasSetOff;
    players[1].getPlanes()[0].setPosition(MapSystem.getNthBlock(datas.YELLOW0position));
    players[1].getPlanes()[1].hasSetOff = datas.YELLOW1hasSetOff;
    players[1].getPlanes()[1].setPosition(MapSystem.getNthBlock(datas.YELLOW1position));
    players[1].getPlanes()[2].hasSetOff = datas.YELLOW2hasSetOff;
    players[1].getPlanes()[2].setPosition(MapSystem.getNthBlock(datas.YELLOW2position));
    players[1].getPlanes()[3].hasSetOff = datas.YELLOW3hasSetOff;
    players[1].getPlanes()[3].setPosition(MapSystem.getNthBlock(datas.YELLOW3position));
    players[2].setHasFinished(datas.BLUEhasFinished);
    players[2].setToBeFinished(datas.BLUEtoBeFinished);
    players[2].setToBeSetOff(datas.BLUEtoBeSetOff);
    players[2].getPlanes()[0].hasSetOff = datas.BLUE0hasSetOff;
    players[2].getPlanes()[0].setPosition(MapSystem.getNthBlock(datas.BLUE0position));
    players[2].getPlanes()[1].hasSetOff = datas.BLUE1hasSetOff;
    players[2].getPlanes()[1].setPosition(MapSystem.getNthBlock(datas.BLUE1position));
    players[2].getPlanes()[2].hasSetOff = datas.BLUE2hasSetOff;
    players[2].getPlanes()[2].setPosition(MapSystem.getNthBlock(datas.BLUE2position));
    players[2].getPlanes()[3].hasSetOff = datas.BLUE3hasSetOff;
    players[2].getPlanes()[3].setPosition(MapSystem.getNthBlock(datas.BLUE3position));
    players[3].setHasFinished(datas.GREENhasFinished);
    players[3].setToBeFinished(datas.GREENtoBeFinished);
    players[3].setToBeSetOff(datas.GREENtoBeSetOff);
    players[3].getPlanes()[0].hasSetOff = datas.GREEN0hasSetOff;
    players[3].getPlanes()[0].setPosition(MapSystem.getNthBlock(datas.GREEN0position));
    players[3].getPlanes()[1].hasSetOff = datas.GREEN1hasSetOff;
    players[3].getPlanes()[1].setPosition(MapSystem.getNthBlock(datas.GREEN1position));
    players[3].getPlanes()[2].hasSetOff = datas.GREEN2hasSetOff;
    players[3].getPlanes()[2].setPosition(MapSystem.getNthBlock(datas.GREEN2position));
    players[3].getPlanes()[3].hasSetOff = datas.GREEN3hasSetOff;
    players[3].getPlanes()[3].setPosition(MapSystem.getNthBlock(datas.GREEN3position));
  }
  
  public static Player getNowPlayer() {
    return getPlayerByColor(nowPlayer);
  }
}
