package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Extends JLayeredPane, provide a JLayeredPane with the game background image.
 */
public class JLayeredPaneWithBack extends JLayeredPane {
  private final String filename;

  public JLayeredPaneWithBack(String filename) {
    this.filename = filename;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    ImageIcon background = new ImageIcon(filename);
    background.paintIcon(this, g, 0, 0);
  }
}
