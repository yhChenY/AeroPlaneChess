package chatroom;

import java.util.Date;
import javax.swing.JPopupMenu;

public class MenuTimer extends Thread{
  public JPopupMenu menu;
  public MenuTimer(JPopupMenu menu) {
    this.menu = menu;
    start();
  }

  @Override
  public void run() {
    super.run();
    long currentTime = new Date().getTime();
    while ((new Date().getTime() - currentTime) < 5000) { }
    menu.setVisible(false);
  }
}
