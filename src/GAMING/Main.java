package GAMING;

import gui.MainMenu;
import utils.*;

public class Main {
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
  
  public static void main(String[] args) {
    players[0] = new Player(Color.RED);
    players[1] = new Player(Color.GREEN);
    players[2] = new Player(Color.YELLOW);
    players[3] = new Player(Color.BLUE);
    
    System.setProperty("sun.java2d.win.uiScaleX", "96dpi");
    System.setProperty("sun.java2d.win.uiScaleY", "96dpi");
    MainMenu mainMenu = new MainMenu();
    if (!isOnLineGame) {
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
          while (!hasGotOpe) {
            //等待
            hasGotOpe=false;
          }
      
          while (!hasGotPlane) {
            //等待
            hasGotPlane=false;
          }
          //获取plane 鼠标点击
          switch (ope) {
            case '+':
              plane.run(sum);
              break;
            case '-':
              plane.run(sub);
              break;
            case '*':
              plane.run(product);
              break;
            case '/':
              plane.run(quotient);
              break;
          }
        }
        //whether the game has finished
      } while (MapSystem.getNowRank() != 4);
      //显示结算框
      //更新 存储 账户数据
  
      //关闭当前游戏界面
      //重启新的游戏界面
    }
  }
  
  private void flyOneTime(Player player,int time){
  
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
  }
  
  /**
   * used with setOpe()... right after it.
   *
   * @param hasGotOpe true/false
   */
  public static void setHasGotOpe(boolean hasGotOpe) {
    Main.hasGotOpe = hasGotOpe;
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
  
  
}
