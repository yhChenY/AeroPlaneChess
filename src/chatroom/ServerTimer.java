package chatroom;

import java.io.IOException;
import java.util.Date;

public class ServerTimer extends Thread{
  public ServerThread serverThread;
  private boolean status = true;
  public ServerTimer(ServerThread serverThread) {
    this.serverThread = serverThread;
    start();
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  @Override
  public void run() {
    while (status) {
      super.run();
      long currentTime = new Date().getTime();
      while ((new Date().getTime() - currentTime) < 1000) { }
      try {
        serverThread.checkOnline();
        sleep(500);
      } catch (IOException | InterruptedException e) {
        e.printStackTrace();
      }
      serverThread.setOnlineMark(false);
    }
  }
}
