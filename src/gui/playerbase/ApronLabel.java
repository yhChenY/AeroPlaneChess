package gui.playerbase;

import javax.swing.*;
import java.awt.*;

/**
 * DO NOT use this class. Use BasePanel instead.
 */
final class ApronLabel extends JLabel {

  private final String imageUrlString;
  private final String emptyImageUrlString = "resources/emptyApron.jpg";
  private ImageIcon imageIcon;
  private final Apron apron;

  ApronLabel(Color color, Apron apron) {
    this.apron = apron;
    this.setSize(new Dimension(66, 66));
    imageUrlString = "resources/" + color.getColorName() + "Airplane.png";
    imageIcon = new ImageIcon(apron.getStatus() ? emptyImageUrlString : imageUrlString);
    setIcon(imageIcon);
    setVisible(true);
  }

  void setStatus(boolean isEmpty) {
    apron.setStatus(isEmpty);
    imageIcon = new ImageIcon(apron.getStatus() ? emptyImageUrlString : imageUrlString);
    setIcon(imageIcon);
  }
}