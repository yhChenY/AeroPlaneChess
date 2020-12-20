package GAMING;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Plane {
  
  private final Color color;
  private State state;
  private Block position;
  private boolean hasSetOff = false;
  private JButton button;
  private Player father;
  
  public Plane(Color color, Player player) {
    father = player;
    button = new JButton();
    this.color = color;
    state = State.FORWARD;
    System.out.println("Produced Plane" + color);
    setPosition(MapSystem.shitBlock);
    button.setIcon(new ImageIcon(
        new ImageIcon("resources/" + color.getColor() + "Airplane.png").getImage()
            .getScaledInstance(36, 31, Image.SCALE_DEFAULT)));
    button.setSize(new Dimension(36, 36));
    button.setFocusPainted(false);
    button.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
//        setPosition(MapSystem.getNthBlock(0));
        super.mouseClicked(e);
        System.out.println("clicked" + color);
        run(Main.getChosenStep());
      }
    });
    button.setOpaque(false);
    button.setFocusPainted(false);
    button.setBorderPainted(false);
  }
  
  public void setOff() {
    hasSetOff = true;
    int n = -1;
    if (color == Color.RED) {
      n = 0;
    } else if (color == Color.YELLOW) {
      n = 13;
    } else if (color == Color.BLUE) {
      n = 26;
    } else if (color == Color.GREEN) {
      n = 39;
    }
    setPosition(MapSystem.getNthBlock(n));
    father.setOffOnePlane();
  }
  
  public void inSetOff() {
    hasSetOff = false;
    setPosition(MapSystem.shitBlock);
    father.killedOnePlane();
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
    } else if (dest.getColor() == color && dest.getType() == Block.Type.FLY) {
      dest = dest.getFlyBlock();
      setPosition(dest);
    }
    //重绘
    Main.getMainMenu().getGame().flushGameFrame();
  }
  
  enum State {
    FORWARD, BACKWARD
  }
  
  public void initialize() {
    hasSetOff = false;
    state = State.FORWARD;
    position = MapSystem.shitBlock;
  }
  
  public String toString(int n) {
    return " " + color + n + "position: " + position.getId() + " \n" +
        " " + color + n + "hasSetOff: " + hasSetOff + " \n";
  }
}
