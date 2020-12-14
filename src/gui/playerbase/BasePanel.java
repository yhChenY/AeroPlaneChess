package gui.playerbase;

import GAMING.Main;
import GAMING.Plane;
import GAMING.Player;
import javax.swing.*;
import java.awt.*;

/**
 * A JPanel displaying the base of a player.
 */
public final class BasePanel extends JPanel {

  Color color;
  Base base;
  JLabel backgroundLabel = new JLabel();
  ApronLabel[] apronLabels;
  Plane[] planes;
  JButton[] planeButtons;
  GridBagLayout layout = new GridBagLayout();
  GridBagConstraints constraints = new GridBagConstraints();

  public BasePanel(Color color) {
    this.setLayout(layout);
    //constraints.fill = GridBagConstraints.BOTH;
    this.color = color;
    this.base = new Base(color);
    this.setBounds(0, 0, 175, 165);

    Player[] players = Main.getPlayers();
    int index = -1;
    for(int i = 0; i < players.length; i++) {
      if(players[i].getColor().getColor().equals(this.getColor().getColorName())) {
        index = i;
      }
    }
    planes = players[index].getPlanes();
    planeButtons = new JButton[planes.length];
    for(int i = 0; i < planeButtons.length; i++) {
      planeButtons[i] = planes[i].getButton();
    }

    initApronLabels();
    String backgroundImageLocation = "resources/" + color.getColorName() + "Base.png";
    ImageIcon backgroundImage = new ImageIcon(backgroundImageLocation);
    backgroundLabel.setIcon(backgroundImage);
    constraints.weightx = 1;
    constraints.weighty = 1;
    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.gridwidth = 2;
    constraints.gridheight = 2;
    layout.setConstraints(backgroundLabel, constraints);
    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    layout.setConstraints(planeButtons[0], constraints);
    constraints.gridx = 0;
    constraints.gridy = 1;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    layout.setConstraints(planeButtons[1], constraints);
    constraints.gridx = 1;
    constraints.gridy = 0;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    layout.setConstraints(planeButtons[2], constraints);
    constraints.gridx = 1;
    constraints.gridy = 1;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    layout.setConstraints(planeButtons[3], constraints);
    for (int i = 0; i < planeButtons.length; i++) {
      add(planeButtons[i]);
    }
    setVisible(true);
    add(backgroundLabel);
  }

  private void initApronLabels() {
    apronLabels = new ApronLabel[base.getApronQuantity()];
    for (int i = 0; i < base.getApronQuantity(); i++) {
      apronLabels[i] = new ApronLabel(color, base.getApron(i));
    }
  }

  /**
   * @deprecated
   * Set the status of an apron.
   *
   * @param index   Determine the index of the apron. 0 is the arrow block. 1~4 are in garage.
   * @param isEmpty The next status of the apron.
   */
  public void setApronStatus(int index, boolean isEmpty) {
    apronLabels[index].setStatus(isEmpty);
  }

  /**
   * Get the color of the base.
   *
   * @return Color
   */
  public Color getColor() {
    return base.getColor();
  }
}