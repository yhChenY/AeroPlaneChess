package GAMING;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Plane {
  private State state;
  private final Color color;
  private Block position;
  private boolean hasSetOff=false;
  private JButton button;
  
  public Plane(Color color) {
    this.color = color;
    state= State.FORWARD;
    button.setIcon(new ImageIcon("resources/" + color.getColor() + "Airplane.png"));
    button.setFocusPainted(false);
    button.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        TODO;
      }
    });
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

  public JButton getButton() {
    return button;
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
