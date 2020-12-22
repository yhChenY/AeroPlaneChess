package GAMING;

//import sun.java2d.pipe.AAShapePipe;

//import com.sun.java.swing.ui.SplashScreen;
//
//import java.awt.*;

import java.util.Map;

public class Player {
  Color color;
  private int hasFinished = 0;
  private int toBeSetOff = 4;
  private int toBeFinished = 4;
  private boolean wined = false;
  private int score = 0;
  private int rank = 4;
  private Plane[] planes;
  private boolean isHuman = false;
  public int toBeArrived = 0;
  
  public Player(Color color) {
    this.color = color;
    planes = new Plane[4];
    for (int i = 0; i < 4; i++) {
      planes[i] = new Plane(color, this);
    }
  }
  
  public Color getColor() {
    return color;
  }
  
  public void finishPlane(int num) {
    hasFinished += num;
    toBeFinished -= num;
    if (hasFinished >= 4) {
      win();
    }
  }
  
  public int getToBeSetOff() {
    return toBeSetOff;
  }
  
  public void setHasFinished(int hasFinished) {
    this.hasFinished = hasFinished;
  }
  
  public void setToBeSetOff(int toBeSetOff) {
    this.toBeSetOff = toBeSetOff;
  }
  
  public void setToBeFinished(int toBeFinished) {
    this.toBeFinished = toBeFinished;
  }
  
  public void setOffOnePlane() {
    if (toBeSetOff > 0) {
      toBeSetOff--;
      toBeArrived++;
      Main.setOffInTurn = true;
    } else {
      return;
    }
    if (!planes[0].isHasSetOff()) {
      planes[0].setOff();
    } else if (!planes[1].isHasSetOff()) {
      planes[1].setOff();
    } else if (!planes[2].isHasSetOff()) {
      planes[2].setOff();
    } else if (!planes[3].isHasSetOff()) {
      planes[3].setOff();
    }
    System.out.println(color + " Set Off A Plane.");
    Main.mainMenu.getGame().flushGameFrame();
  }
  
  public void finishOnePlane() {
    toBeArrived--;
    toBeFinished--;
    System.out.println(color + "FINISH 1 PLANE!!!!!!!!!");
  }
  
  public void killedOnePlane() {
    toBeArrived--;
    toBeSetOff++;
  }
  
  public int getHasFinished() {
    return hasFinished;
  }
  
  public int getToBeFinished() {
    return toBeFinished;
  }
  
  public int getToBeArrived() {
    return toBeArrived;
  }
  
  public void win() {
    wined = true;
  }
  
  public boolean isWined() {
    return wined;
  }
  
  public void setRank(int rank) {
    this.rank = rank;
  }
  
  public int getRank() {
    return rank;
  }
  
  public void addScore(int add) {
    score += add;
  }
  
  public void setHuman() {
    isHuman = true;
  }
  
  public boolean isHuman() {
    return isHuman;
  }
  
  public Plane[] getPlanes() {
    return planes;
  }
  
  public void initialize() {
    hasFinished = 0;
    toBeFinished = 4;
    toBeSetOff = 4;
    score = 0;
    rank = 4;
    for (Plane plane : planes) {
      plane.initialize();
    }
  }
  
  @Override
  public String toString() {
    String p0 = planes[0].toString(0);
    String p1 = planes[1].toString(1);
    String p2 = planes[2].toString(2);
    String p3 = planes[3].toString(3);
    return " \n" +
        " " + color + "hasFinished: " + hasFinished + " \n" +
        " " + color + "toBeSetOff: " + toBeSetOff + " \n" +
        " " + color + "toBeFinished: " + toBeFinished + " \n" +
        p0 +
        p1 +
        p2 +
        p3;
  }
  
  public Plane tryGetOnePlane() {
    Plane ans = null;
    for (Plane p : planes) {
      if (p.isHasSetOff() && !p.isHasFinished()) {
        ans = p;
        break;
      }
    }
    return ans;
  }
  
  public void shortCutKill() {
    switch (color) {
      case RED -> MapSystem.getNthBlock(66).killPlaneOnside();
      case YELLOW -> MapSystem.getNthBlock(72).killPlaneOnside();
      case BLUE -> MapSystem.getNthBlock(55).killPlaneOnside();
      case GREEN -> MapSystem.getNthBlock(60).killPlaneOnside();
    }
  }
}
