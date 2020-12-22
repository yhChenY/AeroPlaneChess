package GAMING;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Plane {
  
  private final Color color;
  private Block position;
  private boolean hasSetOff = false;
  private boolean hasFinished = false;
  private JButton button;
  private Player father;
  ArrayList<Plane> combinedPlanes = new ArrayList<>();
  
  public Plane(Color color, Player player) {
    father = player;
    button = new JButton();
    this.color = color;
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
        if (color == Main.nowPlayer) {
          System.out.println("clicked" + color);
          Main.setHasGotPlane(true);
          run(Main.getChosenStep());
        }
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
      n = 76;
    } else if (color == Color.YELLOW) {
      n = 77;
    } else if (color == Color.BLUE) {
      n = 78;
    } else if (color == Color.GREEN) {
      n = 79;
    }
    setPosition(MapSystem.getNthBlock(n));
    father.setOffOnePlane();
  }
  
  public void inSetOff() {
    hasSetOff = false;
    setPosition(MapSystem.shitBlock);
    //remove self from other combined planes
    for (Plane plane : Main.getPlayerByColor(color).getPlanes()) {
      if (!plane.equals(this)) {
        plane.combinedPlanes.removeIf(p -> p.equals(this));
      }
    }
    //remove all self combined planes
    combinedPlanes.removeAll(combinedPlanes);
    father.killedOnePlane();
  }
  
  public void combinePlane(Plane plane) {
    //combine self and hasCombined planes with @plane
    if (!plane.combinedPlanes.contains(this)) {
      plane.combinedPlanes.add(this);
    }
    plane.combinedPlanes.addAll(combinedPlanes);
    //combine @plane with self and combined Planes
    combinedPlanes.add(plane);
    for (Plane p : combinedPlanes) {
      if (!p.combinedPlanes.contains(plane)) {
        p.combinedPlanes.add(plane);
      }
    }
    //set All combined planes' position as self position
    //But After this time?????????????????????????????????
    for (Plane p : combinedPlanes) {
      p.setPosition(position);
    }
  }
  
  public boolean isHasSetOff() {
    return hasSetOff;
  }
  
  public Block getPosition() {
    return position;
  }
  
  public void setPosition(Block block) {
    this.position = block;
    for (Plane p : combinedPlanes) {
      p.setPosition(block);
    }
  }
  
  public Color getColor() {
    return color;
  }
  
  public JButton getButton() {
    return button;
  }
  
  public void run(int n) {
    System.out.println(color + "Plane Run Steps: " + n);
    Block dest = position.getNextNBlock(n, this);
    landOnBlock(dest);
  }
  
  public void initialize() {
    hasSetOff = false;
    position = MapSystem.shitBlock;
    hasFinished = false;
  }
  
  public String toString(int n) {
    return " " + color + n + "position: " + position.getId() + " \n" +
        " " + color + n + "hasSetOff: " + hasSetOff + " \n";
  }
  
  public boolean isHasFinished() {
    return hasFinished;
  }
  
  private void landOnBlock(Block dest) {
    System.out.println(color + "land on " + dest.getId() + " " + dest.getColor() + " " + dest.getType());
    if (dest.getType() == Block.Type.FINAL) {
      hasFinished = true;
      setPosition(MapSystem.shitBlock);
      father.finishOnePlane();
    }
    ArrayList<Plane> planesUpside = dest.getPlaneUpside();
    // whether crash ? or combine ?
    if (planesUpside.size() > 0) {
      for (Plane plane : planesUpside) {
        if (plane.getColor() == color) {
          plane.combinePlane(this);
        } else {
          PK(planesUpside);
        }
      }
    }
    Block nextDest = dest;
    if (color == dest.getColor()) {
      if (dest.getType() == Block.Type.COMMON) {
        nextDest = dest.getNextNBlock(4, this);
      } else if (dest.getType() == Block.Type.CORNER) {
        nextDest = dest.getNextBlock(this);
      } else if (dest.getType() == Block.Type.FLY) {
        nextDest = dest.getFlyBlock();
      }
      System.out.println("Jump to " + nextDest.getId());
      setPosition(nextDest);
    }
    setPosition(nextDest);
    Main.mainMenu.getGame().flushGameFrame();
  }
  
  private void PK(ArrayList<Plane> planes) {
    Color enemyColor = planes.get(0).getColor();
    while (planes.size() > 0 || this.combinedPlanes.size() > 0 || this.hasSetOff) {
      int pkc1 = utils.util.random(1, 6);
      System.out.println(color + " rolled " + pkc1);
      int pkc2 = utils.util.random(1, 6);
      System.out.println(enemyColor + " rolled " + pkc2);
      if (pkc1 >= pkc2) {
        System.out.println("Attacker wined");
        planes.get(0).inSetOff();
        planes.remove(0);
      } else {
        if (combinedPlanes.size() > 0) {
          combinedPlanes.get(0).inSetOff();
        } else {
          this.inSetOff();
        }
        System.out.println("Defender wined");
      }
    }
    Main.mainMenu.getGame().flushGameFrame();
  }
}
