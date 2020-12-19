package GAMING;

//import sun.java2d.pipe.AAShapePipe;

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
  
  public Player(Color color) {
    this.color = color;
    planes = new Plane[4];
    for (int i = 0; i < 4; i++) {
      planes[i] = new Plane(color);
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
  
  public void setOff(Plane plane) {
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
    isHuman = false;
  }
  
  @Override
  public String toString(){
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
}
