package gui;

import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class PrizeFrame extends JFrame{
  String finalPrize = null;
  String chosePlane = null;

  public String getFinalPrize() {
    return finalPrize;
  }

  public String getChosePlane() {
    return chosePlane;
  }

  public PrizeFrame() throws HeadlessException {
    try {
      run();
    } catch (InterruptedException | IOException e) {
      e.printStackTrace();
    }

  }
  public void run() throws InterruptedException, IOException {
    setSize(500,400);
    setIconImage(ImageIO.read(this.getClass().getResource("/resources/props/drink.png")));
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setVisible(true);
    Prize prize = new Prize(this);
    Thread.sleep(500);
    prize.getReturnButton().addMouseListener(
        new MouseAdapter() {
          @Override
          public void mouseReleased(MouseEvent e) {
            dispose();
            repaint();
            super.mouseReleased(e);
          }
        }
    );
    add(prize.getPrizePanel());
    validate();
    while (prize.getPrize() == null || (prize.getPrize().equals("SWORD") && prize.getChosePlane() == null)) {
      Thread.sleep(500);
      finalPrize = prize.getPrize();
      chosePlane = prize.getChosePlane();
    }
    dispose();
  }

}
