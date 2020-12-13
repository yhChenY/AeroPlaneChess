package GAMING;

import sun.java2d.pipe.AAShapePipe;

public class Player {
  Color color;
  private int hasFinished = 0;
  private int toBeSetOff=4;
  private int toBeFinished = 4;
  private boolean wined = false;
  private int score = 0;
  private int rank = 4;
  private Plane[] planes;
  private boolean isHuman=false;
  
  public Player(Color color) {
    this.color = color;
    planes=new Plane[4];
    for(int i=0;i<4;i++){
      planes[i]=new Plane(color);
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
  
  public void setOff(Plane plane){
    plane.setOff();
    toBeSetOff--;
  }
  
  public int getHasFinished() {
    return hasFinished;
  }
  
  public int getToBeFinished() {
    return toBeFinished;
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
  
  public void setHuman(){
    isHuman=true;
  }
  
  public boolean isHuman() {
    return isHuman;
  }
}
