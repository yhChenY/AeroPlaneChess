package chatroom;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * the driver for each client
 */
public class ChatRoom extends Thread {
  private GUI gui = null;
  private Client client = null;
  String username;

  public ChatRoom(String username) {
    this.username = username;
    start();
  }

  public GUI getGui() {
    return gui;
  }

  public Client getClient() {
    return client;
  }

  public void run() {
    client = new Client(username);
    gui = new GUI(client);
    client.setGui(gui);
    gui.start();

////      该段用于测试chatPanel在Frame上的效果
//    JFrame mainFrame = new JFrame();
//    mainFrame.setSize(new Dimension(400,600));
//    mainFrame.add(gui.getChatRoomPanel());
//    mainFrame.setTitle("Chatroom");
//    mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//
//    mainFrame.setVisible(true);
//    mainFrame.dispose();
    
    try {
      client.start();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
