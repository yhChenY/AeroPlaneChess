package GAMING;

public class Block {
  private Color color;
  private final Type type;
  private int x;
  private int y;
  private Block preBlock;
  private Block nextBlock;
  private Block afterCornerBlock;
  private Block nextFlyBlock;
  private int distance=0;
  
  public enum Type {
    COMMON(),
    CORNER(),
    FLY(),
    SPRINT();
    public static Type getType(String s){
      if(s.equals("COMMON"))return COMMON;
      if(s.equals("CONNER"))return CORNER;
      if(s.equals("FLY"))return FLY;
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
  
  public Block(Color c, Type type,int x,int y) {
    this.color = c;
    this.type = type;
    this.x=x;
    this.y=y;
  }
  public Block getPreBlock() {
    return preBlock;
  }
  
  public void setPreBlock(Block preBlock) {
    this.preBlock = preBlock;
  }
  
  public void setNextFlyBlock(Block block){
    nextFlyBlock=block;
  }
  
  public Block getFlyBlock() {
    return nextFlyBlock;
  }
//  public Block getNextBlock() {
//    return nextBlock;
//  }
  
  public Block getNextBlock(Plane plane) {
    if (plane.getColor()==color&&type==Type.CORNER) return afterCornerBlock;
    return nextBlock;
  }
  
  public Block getNextNBlock(int n, Plane plane){
    if(n>distance){
      if(n==2*distance)return this;
      if(n>2*distance)return getPreNBlock(n-2*distance);
      return getNextNBlock(2*distance-n,plane);
    }
    if(n==1)return getNextBlock(plane);
    else return getNextNBlock(n-1,plane);
  }
  
  private Block getPreNBlock(int n){
    if(n==1)return preBlock;
    else return getPreNBlock(n-1);
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
  
  public void killPlane(){
  
  }
  
  @Override
  public String toString(){
    return "\n" +
        "- \n" +
        " blockType: " + type + " \n" +
        " blockColor: " + color + " \n" +
        " x: " + x + " \n" +
        " y: " + y + " ";
  }
}
