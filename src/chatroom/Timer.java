package chatroom;

import java.util.Date;
import javax.swing.JPopupMenu;

public class Timer extends Thread{
  public JPopupMenu menu;
  public Timer(JPopupMenu menu) {
    this.menu = menu;
    start();
  }

  @Override
  public void run() {
    super.run();
    long currentTime = new Date().getTime();
    while ((new Date().getTime() - currentTime) < 2000) { }
    menu.setVisible(false);
  }
}
