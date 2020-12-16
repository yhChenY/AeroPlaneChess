package gui.playerbase;

import javax.swing.*;
import java.awt.*;

public class ApronLabelTest {

  public static void main(String[] args) throws InterruptedException {
    JFrame frame = new JFrame();
    BasePanel basePanel = new BasePanel(Color.RED);
    frame.setLocation(0, 0);
    frame.setResizable(false);
    frame.setSize(new Dimension(265, 265));
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(basePanel);
  }
}

