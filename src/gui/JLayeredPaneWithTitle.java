package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Extends JLayeredPane. Provides a JLayeredPane with the main menu background.
 */
public class JLayeredPaneWithTitle extends JLayeredPane {

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    ImageIcon background = new ImageIcon("resources/mainmenubackground.png");
    background.paintIcon(this, g, 0, 0);
  }
}
