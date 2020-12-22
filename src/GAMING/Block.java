package GAMING;

import java.util.ArrayList;

public class Block {
  private Color color;
  private final Type type;
  private int x;
  private int y;
  private Block preBlock;
  private Block nextBlock;
  private Block afterCornerBlock;
  private Block nextFlyBlock;
  private int distance = 100;
  private int id;
  private int nextId;
  private int preId;
  
  public enum Type {
    COMMON(),
    CORNER(),
    FLY(),
    FINAL(),
    SPRINT(),
    WAIT();
    
    public static Type getType(String s) {
      if (s.equals("COMMON")) return COMMON;
      if (s.equals("CORNER")) return CORNER;
      if (s.equals("FLY")) return FLY;
      if (s.equals("FINAL")) return FINAL;
      if (s.equals("WAIT")) return WAIT;
      return SPRINT;
    }
  }
  
  public void setDistance(int distance) {
    this.distance = distance;
  }
  
  public Block(Color c, Type type) {
    this.color = c;
    this.type = type;
  }
  
  public Block(Color c, Type type, int x, int y, int id, int nextId, int preId) {
    this.color = c;
    this.type = type;
    this.x = x;
    this.y = y;
    this.id = id;
    this.nextId = nextId;
    this.preId = preId;
  }
  
  public Block getPreBlock() {
    return preBlock;
  }
  
  public void setPreBlock(Block preBlock) {
    this.preBlock = preBlock;
  }
  
  public void setNextFlyBlock(Block block) {
    nextFlyBlock = block;
  }
  
  public Block getFlyBlock() {
    return nextFlyBlock;
  }
  
  public Block getNextBlock(Plane plane) {
    System.out.println("Try get block" + id + " 's nextBlock");
    if (plane.getColor() == color && type == Type.CORNER) return afterCornerBlock;
    return nextBlock;
  }
  
  public Block getNextNBlock(int n, Plane plane) {
    if (n > distance) {
      if (n == 2 * distance) return this;
      if (n > 2 * distance) return getPreNBlock(n - 2 * distance);
      return getNextNBlock(2 * distance - n, plane);
    }
    if (n == 1) return getNextBlock(plane);
    else return getNextBlock(plane).getNextNBlock(n - 1, plane);
  }
  
  private Block getPreNBlock(int n) {
    if (n == 1) return preBlock;
    else return getPreNBlock(n - 1);
  }
  
  public void setNextBlock(Block nextBlock) {
    this.nextBlock = nextBlock;
  }
  
  public void setAfterCornerBlock(Block afterCornerBlock) {
    if (type == Type.CORNER) {
      this.afterCornerBlock = afterCornerBlock;
    }
  }
  
  public Color getColor() {
    return color;
  }
  
  public void setColor(Color color) {
    this.color = color;
  }
  
  public Type getType() {
    return type;
  }
  
  public int getX() {
    return x;
  }
  
  public void setX(int x) {
    this.x = x;
  }
  
  public int getY() {
    return y;
  }
  
  public void setY(int y) {
    this.y = y;
  }
  
  public void killPlane() {
    for (Player p : Main.players) {
      for (Plane plane : p.getPlanes()) {
        if (plane.getPosition().getId() == id) {
          plane.inSetOff();
        }
      }
    }
  }
  
  public int getId() {
    return id;
  }
  
  public int getNextId() {
    return nextId;
  }
  
  public int getPreId() {
    return preId;
  }
  
  @Override
  public String toString() {
    return "\n" +
        "- \n" +
        " blockType: " + type + " \n" +
        " blockColor: " + color + " \n" +
        " x: " + x + " \n" +
        " y: " + y + " \n" +
        " id: " + id + " \n" +
        " nextId: " + nextId + " \n" +
        " preId: " + preId + " ";
  }
  
  public ArrayList<Plane> getPlaneUpside() {
    ArrayList<Plane> ans = new ArrayList<>();
    for (Player player : Main.players) {
      for (Plane plane : player.getPlanes()) {
        if (plane.getPosition().getId() == id) {
          ans.add(plane);
        }
      }
    }
    return ans;
  }
}
