package chatroom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * create  threads for each socket
 **/
public class ServerThread extends Thread {
  private final Socket socket;
  public String username;
  public Server server;
  private boolean onlineMark = true;
  private ServerTimer serverTimer; // 定时检查client是否在线
  private String color;

  public ServerThread(Socket socket, String color, Server server) {
    this.server = server;
    this.color = color;
    this.socket = socket;
    start();
  }

  public Socket getSocket() {
    return socket;
  }

  public void setOnlineMark(boolean onlineMark) {
    this.onlineMark = onlineMark;
  }

  @Override
  public void run() {
    try {
      //新用户初始化，并被server记录下来
      BufferedReader netIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      // 读取该用户username
      username = netIn.readLine();
      //
      System.out.println("the name is: " + username);
      //
      server.getUsers().add(new User(username, socket));
      //  通知其他人有新用户登录
      transmit2All("--" + username + " connected--", socket);
      //  每有新用户登录，给所有用户（包括新用户）更新users
      //  [User]为更新users名单的标志，user接收到后会进行处理
      transmit2All("[user]" + " " + username, socket);
      //  server给该新用户完整的用户名单(String)
      for (User user : server.getUsers()
      ) {
        soloTransmit("[user]" + " " + user.getUsername(), socket);
      }
      
      //告知该用户他的颜色
      soloTransmit("[color]" + " " + color, socket);

      //把队友名字传给他
      if (!server.redTeam.isEmpty() && server.redTeam.contains(username)) {
        for (String user : server.redTeam
        ) {
          if (!user.equals(username)) {
            soloTransmit("[teammate]" + " " + user, socket);
          }
        }
      }
      if (!server.blueTeam.isEmpty() && server.blueTeam.contains(username)) {
        for (String user : server.blueTeam
        ) {
          if (!user.equals(username)) {
            soloTransmit("[teammate]" + " " + user, socket);
          }
        }
      }
      // 对client是否在线进行检查
      serverTimer = new ServerTimer(this);
      //读取该用户发言，发给其他用户
      //server不对信息进行任何处理，保留所有标识符，交由client处理
      while (!socket.isClosed()) {
        //  line中不带有\n
        String line = netIn.readLine();
        if (line != null) {
          if (line.split("\"")[0].equals("I'm online")) {
            setOnlineMark(true);
          }
          //  [solo]为私聊的标志， client发过来的信息将是"[solo] [targetUsername] [senderName] "line""
          //  [block]为通知拉黑的标志，client发过来的消息将是"[block] [blockTarget] [senderName]"
          else if (line.split(" ")[0].equals("[solo]")
              || line.split(" ")[0].equals("[block]")
              || line.split(" ")[0].equals("[normalize]")) {
            //从server的users中找到对应的user， 只给目标user发送
            for (User user : server.getUsers()
            ) {
              if (("[" + user.getUsername() + "]").equals(line.split(" ")[1])) {
                soloTransmit(line, user.getSocket());
              }
            }
          } else if (line.split(" ")[0].equals("[gameData]")) {
            //
            System.out.println(line);
            //
            transmit2All(line, null);
          }else {
            //
            System.out.println(line);
            System.out.println(line.split(" ")[0]);
            //
            transmit2All(line,socket);
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * @param line-
   * @param selfSocket send message to any user except myself
   */
  public void transmit2All(String line, Socket selfSocket) throws IOException {
    for (User user : server.getUsers()
    ) {
      if (!user.getSocket().equals(selfSocket)) {
        soloTransmit(line, user.getSocket());
      }
    }
  }

  /**
   * 把line单独发给该socket
   * @param line-
   * @param socket-目标对象的socket
   * @throws IOException-
   */
  public void soloTransmit(String line, Socket socket) throws IOException {
    PrintWriter netOut = new PrintWriter(socket.getOutputStream());
    netOut.print(line + "\n"); //发出的所有信息最后都会帮忙加上"\n"，便于readline()
    netOut.flush();
  }

  public void checkOnline() throws IOException {
    if (!onlineMark) {
      // 通知其他用户自己下线
      transmit2All("[leave] " + "["+username+"]", socket);
      serverTimer.setStatus(false);
      server.getUsers().removeIf(user -> user.getUsername().equals(this.username));
    }
  }
}
