package gui.playerbase;

import GAMING.Main;
import GAMING.Player;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A JPanel displaying the base of a player.
 */
public final class BasePanel extends JPanel {

  private static ArrayList<BasePanel> basePanels = new ArrayList<>(0);
  private Color color;
  private Base base;
  private int launchable, arrived;
  private JButton launchButton = new JButton();
  private JLabel launchableLabel = new JLabel("Launchable: "), arrivedLabel = new JLabel(
      "Arrived: ", JLabel.LEFT);
  private JLabel[] launchableImageLabel = new JLabel[4], arrivedImageLabel = new JLabel[4];
  private GridBagLayout layout = new GridBagLayout();
  private GridBagConstraints constraints = new GridBagConstraints();
  private Font font = new Font("Ravie", Font.PLAIN, 15);

  public BasePanel(Color color) {
    basePanels.add(this);
    this.setLayout(layout);
    this.color = color;
    this.base = new Base(color);
    this.setBounds(0, 0, 175, 165);

    Player[] players = Main.getPlayers();
    int index = -1;
    for (int i = 0; i < players.length; i++) {
      if (players[i].getColor().getColor().equals(this.getColor().getColorName())) {
        index = i;
      }
    }
    launchable = players[index].getToBeSetOff();
    arrived = players[index].getHasFinished();

    launchableLabel.setFont(font);
    launchableLabel.setHorizontalAlignment(JLabel.LEFT);

    arrivedLabel.setFont(font);
    arrivedLabel.setHorizontalAlignment(JLabel.LEFT);

    constraints.fill = GridBagConstraints.BOTH;
    constraints.insets = new Insets(20, 5, 0, 0);
    constraints.weighty = 0.2;
    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.gridwidth = 4;
    constraints.gridheight = 1;
    add(launchableLabel, constraints);

    for (int i = 0; i < launchable; i++) {
      launchableImageLabel[i] = new JLabel();
      launchableImageLabel[i].setIcon(new ImageIcon(
          new ImageIcon("resources/" + color.getColorName() + "Airplane.png").getImage()
              .getScaledInstance(25, 22, Image.SCALE_DEFAULT)));
      constraints.fill = GridBagConstraints.NONE;
      constraints.insets = new Insets(0, 10, 0, 10);
      constraints.anchor = GridBagConstraints.WEST;
      constraints.weightx = 1;
      constraints.weighty = 0.3;
      constraints.gridx = 4 - i;
      constraints.gridy = 1;
      constraints.gridwidth = 1;
      constraints.gridheight = 1;
      add(launchableImageLabel[i], constraints);
    }

    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.anchor = GridBagConstraints.CENTER;
    constraints.insets = new Insets(0, 5, 0, 0);
    constraints.weightx = 1;
    constraints.weighty = 0.2;
    constraints.gridx = 0;
    constraints.gridy = 2;
    constraints.gridwidth = 4;
    constraints.gridheight = 1;
    add(arrivedLabel, constraints);

    if (arrived == 0) {
      constraints.weightx = 0;
      constraints.weighty = 0.3;
      constraints.gridx = 0;
      constraints.gridy = 3;
      constraints.gridwidth = 4;
      constraints.gridheight = 1;
      JLabel blank = new JLabel("N/A", JLabel.CENTER);
      blank.setFont(font);
      add(blank, constraints);
    }
    for (int i = 0; i < arrived; i++) {
      arrivedImageLabel[i] = new JLabel();
      arrivedImageLabel[i].setIcon(new ImageIcon(
          new ImageIcon("resources/" + color.getColorName() + "Airplane.png").getImage()
              .getScaledInstance(15, 13, Image.SCALE_DEFAULT)));
      constraints.fill = GridBagConstraints.NONE;
      constraints.insets = new Insets(0, 10, 0, 0);
      constraints.anchor = GridBagConstraints.WEST;
      constraints.weightx = 1;
      constraints.weighty = 0.3;
      constraints.gridx = 4 - i;
      constraints.gridy = 3;
      constraints.gridwidth = 1;
      constraints.gridheight = 1;
      add(arrivedImageLabel[i], constraints);
    }

    setVisible(true);
  }

  public static void flushBasePanel() {
    for (int i = basePanels.size() - 1; i > basePanels.size() - 4; i--) {
      Component[] components = basePanels.get(i).getComponents();
      for(Component c: components) {
        c.repaint();
      }
    }
  }

  /**
   * Get the color of the base.
   *
   * @return Color
   */
  public Color getColor() {
    return base.getColor();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    ImageIcon background = new ImageIcon("resources/" + color.getColorName() + "Base.png");
    background.paintIcon(this, g, 0, 0);
  }
}