package chatroom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * to catch other's talk
 */
public class Catcher extends Thread{
  private final Socket socket;
  private final GUI gui;
  private final Client client;

  public Catcher(Client client) {
    this.client = client;
    this.socket = client.getSocket();
    this.gui = client.getGui();
    start();
  }

  @Override
  public void run() {
    super.run();
    try {
      BufferedReader netIn = new BufferedReader(
          new InputStreamReader(socket.getInputStream())); //收入信息
      String line;
      while (true) {
        line = netIn.readLine();
//       测试
        System.out.println(line);

        switch (line.split(" ")[0]) {
          case "[start]":
            client.setStart(true);
            break;
          case "[color]":
            client.setColor(line.split(" ")[1]);
            break;
          case "[leave]":
            client.getGui().deleteUsers(line.split(" ")[1]);
            for (TargetPanel p : client.getGui().getTargetPanels()
            ) {
              if (p.getTargetUser().equals("ALL USERS")) {
                String leaveName = line.split(" ")[1].replace("[", "");
                leaveName = leaveName.replace("]", "");
                p.show(leaveName+" left");
              }
            }
            break;
          case "[teammate]":
            client.setTeammate(line.split(" ")[1]);
            client.getGui().setTeamFlag(true); // 存在teammate
            break;
          case "[user]":
            String newUser = line.split(" ")[1];
            if (client.getTeammate() == null || client.getTeammate().equals(newUser)) {
              client.getUsers().add(newUser);
              gui.updateUsers(newUser);
            }
            break;
          case "[solo]":
            //  若为屏蔽对象发来的信息，直接return
            if (!client.getBlocks().isEmpty()) {
              // 判断消息是否是屏蔽人发来的
              boolean blockMark = false;
              for (String blockedUsername : client.getBlocks()
              ) {
                if (("[" + blockedUsername + "]").equals(line.split(" ")[2])) {
                  blockMark = true;
                  break;
                }
              }
              if (blockMark) {
                break;
              }
            }
            //  [solo]为私聊的标志， client发过来的信息将是"[solo] [targetUsername] [senderName] "line"
            for (TargetPanel p : client.getGui().getTargetPanels()
            ) {
              if (("[" + p.getTargetUser() + "]").equals(line.split(" ")[2])) {
                p.show(line.split("\"")[1]);
              }
            }
            break;
          case "[block]":
            // [block]为通知拉黑的标志，client发过来的消息将是"[block] [blockTarget] [senderName]"
            for (TargetPanel p : client.getGui().getTargetPanels()
            ) {
              if (("[" + p.getTargetUser() + "]").equals(line.split(" ")[2])) {
                p.show("You are BLOCKED");
              }
            }
            break;
          case "[normalize]":
            // [normalize]为取消拉黑的标志，client发过来的消息将是"[block] [blockTarget] [senderName]"
            for (TargetPanel p : client.getGui().getTargetPanels()
            ) {
              if (("[" + p.getTargetUser() + "]").equals(line.split(" ")[2])) {
                p.show("You can freely talk now");
              }
            }
            break;
          case "[gameData]":
            client.setNewGameData(line.split(" ")[1], line.split(" ")[2]);
            break;
          //  default为正常群聊
          default:
            for (TargetPanel p : client.getGui().getTargetPanels()
            ) {
              if (p.getTargetUser().equals("ALL USERS")) {
                if (line.contains("\"")) {
                  p.show(line.split("\"")[1]);
                } else {
                  p.show(line);
                }
              }
            }
            break;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
