package chatroom;

import javax.swing.JPanel;

public class demo {

  public static void main(String[] args) throws InterruptedException {
    ChatRoom chatRoom = new ChatRoom("Van");
    Thread.sleep(500);
    JPanel jPanel = chatRoom.getGui().getChatRoomPanel();
  }

}
