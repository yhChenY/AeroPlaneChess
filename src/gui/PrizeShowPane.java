package gui;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class PrizeShowPane extends JOptionPane {
  private final String prize;

  public PrizeShowPane(String prize) {
    this.prize = prize;
    run();
  }

  public void run() {
    Prop targetProp = null;
    for (Prop prop : LotteryPanel.getProps()
    ) {
      if (prize.equals(prop.getPropName())) {
        targetProp = prop;
      }
    }
    assert targetProp != null;
    setIcon(new ImageIcon(targetProp.getPhotoAddress()));
    setFont(new Font("Arial", Font.BOLD, 13));
    showMessageDialog(null, targetProp.getInstruction(), null, INFORMATION_MESSAGE, new ImageIcon(targetProp.getPhotoAddress()));
  }

}
