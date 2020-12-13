package chatroom;

import java.io.IOException;
import java.util.Date;

public class ClientTimer extends Thread{
  public Client client;
  public ClientTimer(Client client) {
    this.client = client;
    start();
  }

  @Override
  public void run() {
    while (true) {
      super.run();
      long currentTime = new Date().getTime();
      while ((new Date().getTime() - currentTime) < 300) { }
      try {
        // 用"分隔
        client.transmit("I'm online\"\n", client.getSocket());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
