package gui;

import java.awt.Dimension;
import javax.swing.JPanel;

public class Prize extends Thread {
  private final JPanel prizePanel = new JPanel();
  private String prize = null;
  private String chosePlane = null;
  private final PrizeFrame prizeFrame;

  public JPanel getPrizePanel() {
    return prizePanel;
  }

  public Prize(PrizeFrame prizeFrame) {
    this.prizeFrame = prizeFrame;
    start();
  }

  public String getPrize() {
    return prize;
  }

  public String getChosePlane() {
    return chosePlane;
  }

  public void run() {
    prizePanel.setPreferredSize(new Dimension(500,500));
    LotteryPanel lotteryPanel = new LotteryPanel();
    prizePanel.add(lotteryPanel);
    while (!lotteryPanel.isLotteryDone()) {
      try {
        sleep(500);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    if (lotteryPanel.getFinalPrize() != null && lotteryPanel.getFinalPrize().equals("SWORD")) {
      prize = lotteryPanel.getFinalPrize();
      PlaneChoosePane pcp = new PlaneChoosePane("resources/blackBackground.jpg");
      prizeFrame.setSize(300,400);
      prizeFrame.remove(prizePanel);
      prizeFrame.repaint();
      prizeFrame.add(pcp);
      prizeFrame.validate();
      chosePlane = pcp.getPlaneColor();
      while (chosePlane == null) {
        try {
          sleep(500);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        chosePlane = pcp.getPlaneColor();
      }
    }
    prize = lotteryPanel.getFinalPrize();
  }

}
