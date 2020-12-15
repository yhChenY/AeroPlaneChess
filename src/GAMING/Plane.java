package GAMING;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Plane {
  
  private final Color color;
  private State state;
  private Block position;
  private boolean hasSetOff = false;
  private JButton button;
  
  public Plane(Color color) {
    button = new JButton();
    this.color = color;
    state = State.FORWARD;
    button.setIcon(new ImageIcon(
        new ImageIcon("resources/" + color.getColor() + "Airplane.png").getImage()
            .getScaledInstance(36, 31, Image.SCALE_DEFAULT)));
    button.setSize(new Dimension(36, 36));
    button.setFocusPainted(false);
    button.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        System.out.println("clicked");
        setPosition(MapSystem.getBlocks().get(0));
        run(Main.getChosenStep());
      }
    });
    button.setOpaque(false);
    button.setFocusPainted(false);
    button.setBorderPainted(false);
  }
  
  public void setOff() {
    hasSetOff = true;
    
  }
  
  public boolean isHasSetOff() {
    return hasSetOff;
  }
  
  public Block getPosition() {
    return position;
  }
  
  public void setPosition(Block block) {
    this.position = block;
  }
  
  public boolean isForward() {
    return state == State.FORWARD;
  }
  
  public boolean isBackward() {
    return !isForward();
  }
  
  public void setForward() {
    this.state = State.FORWARD;
  }
  
  public void setBackward() {
    this.state = State.BACKWARD;
  }
  
  public Color getColor() {
    return color;
  }
  
  public JButton getButton() {
    return button;
  }
  
  public void run(int n) {
    Block dest = position.getNextNBlock(n, this);
    setPosition(dest);
    if (dest.getColor() == color && dest.getType() == Block.Type.COMMON) {
      dest = dest.getNextNBlock(4, this);
      setPosition(dest);
      return;
    }
    if (dest.getColor() == color && dest.getType() == Block.Type.FLY) {
      dest = dest.getFlyBlock();
      setPosition(dest);
      return;
    }
    //重绘
  }
  
  enum State {
    FORWARD, BACKWARD
  }
  
}
