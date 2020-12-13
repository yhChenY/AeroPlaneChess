package gui;

import javax.swing.*;
import chatroom.ChatRoom;

public class addPanel extends Thread{
  JLayeredPane layeredPane;
  ChatRoom chatRoom;
  public addPanel(JLayeredPane layeredPane, ChatRoom chatRoom) {
    this.layeredPane = layeredPane;
    this.chatRoom = chatRoom;
    start();
  }
  public void run() {
    try {
      sleep(500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    layeredPane.add(chatRoom.getGui().getChatRoomPanel());
    layeredPane.validate();
  }
}
