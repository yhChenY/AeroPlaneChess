package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Extends JLayeredPane, provide a JLayeredPane with the game background image.
 */
public class JLayeredPaneWithBackground extends JLayeredPane {

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    ImageIcon background = new ImageIcon("resources/background.png");
    background.paintIcon(this, g, 0, 0);
  }
}
