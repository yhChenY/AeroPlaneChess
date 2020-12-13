package GAMING;

public class Plane {
  private State state;
  private final Color color;
  private Block position;
  private boolean hasSetOff=false;
  
  public Plane(Color color) {
    this.color = color;
    state= State.FORWARD;
  }
  
  public void setOff(){
    hasSetOff=true;
  }
  
  public boolean isHasSetOff() {
    return hasSetOff;
  }
  
  public void setPosition(Block block){
    this.position=block;
  }
  
  public Block getPosition() {
    return position;
  }
  
  public boolean isForward(){
    return state== State.FORWARD;
  }
  
  public boolean isBackward(){
    return !isForward();
  }
  
  public void setForward(){
    this.state= State.FORWARD;
  }
  
  public void setBackward(){
    this.state= State.BACKWARD;
  }
  
  public Color getColor() {
    return color;
  }
  
  public void run(int n){
    Block dest=position.getNextNBlock(n,this);
    setPosition(dest);
    if(dest.getColor()==color&&dest.getType()== Block.Type.COMMON){
      dest=dest.getNextNBlock(4,this);
      setPosition(dest);
      return;
    }
    if(dest.getColor()==color&&dest.getType()== Block.Type.FLY){
      dest=dest.getFlyBlock();
      setPosition(dest);
      return;
    }
    //重绘
  }
  
  enum State {
    FORWARD, BACKWARD
  }
}
