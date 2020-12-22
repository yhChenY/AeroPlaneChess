package gui;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class YourTurnDialog extends JDialog {
  private JLabel yourTurnLabel = new JLabel("It is your turn now!");

  public YourTurnDialog() {
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        add(yourTurnLabel);
        setUndecorated(true);
        setModal(true);
        setBounds(520, 305, 200, 50);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      }
    };
    Thread thread = new Thread(runnable);
    try {
      thread.start();
      Thread.sleep(1500);
      dispose();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
