package chatroom;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.HostnameVerifier;

/**
 * client part of the socket
 */
public class Client extends Thread{
  private String HOST = "127.0.0.1";
  private final User user = new User();
  private Socket socket;
  private final List<String> talks = new ArrayList<>(); //储存用户发言
  private GUI gui;
  private final ArrayList<String> users = new ArrayList<>();
  private String teammate = null; // 记录队友名字
  private final ArrayList<String> blocks = new ArrayList<>();
  private String[] newGameData = new String[2]; // 储存游戏最新数据
  private String color;
  private boolean start = false;

  public String[] getNewGameData() {
    return newGameData;
  }

  public ArrayList<String> getUsers() {
    return users;
  }

  public void setNewGameData(String formerPlayer, String gameData) {
    newGameData[0] = formerPlayer;
    newGameData[1] = gameData;
  }

  public void setStart(boolean start) {
    this.start = start;
  }

  public boolean isStart() {
    return start;
  }

  public void setHOST(String HOST) {
    this.HOST = HOST;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public User getUser() {
    return user;
  }

  public Socket getSocket() {
    return socket;
  }

  public void setGui(GUI gui) {
    this.gui = gui;
  }

  public GUI getGui() {
    return gui;
  }

  public String getTeammate() {
    return teammate;
  }

  public void setTeammate(String teammate) {
    this.teammate = teammate;
  }

  public ArrayList<String> getBlocks() {
    return blocks;
  }

  public Client(String username, String HOST) {
    user.setUsername(username);
    this.HOST = HOST;
  }

  /**
   * collect lines from the inputBox, put lines into TALKS
   * @param line-
   */
  public void talk(String line) {
    talks.add(line);
  }

  public void run() {
//    System.out.println("run once");
    final int PORT = 4320;
    final int TIME_OUT = 10000;  //10s
    final SocketAddress socketAddress = new InetSocketAddress(HOST, PORT);
    socket = new Socket();
    try {
      socket.connect(socketAddress, TIME_OUT);
      user.setSocket(socket);
      gui.currentTargetPanel.show("--Connected--");
      //client一登录就把自己用户名传过去，方便server对所有用户进行记录
      transmit(user.getUsername()+"\n", socket);

      new Catcher(this);
      new ClientTimer(this);
      while (!socket.isClosed()) {
        // inputBox中的消息自带有"\n"
        if (!talks.isEmpty()) {
          for (String line : talks
          ) {
            if (line.equals("\n")) {
              continue;
            }
            line = user.getUsername() + ": " + line;
            if (gui.currentTargetPanel.getTargetUser().equals("ALL USERS")) {
              //把（真正意义上的talk用"分隔开作为标识符
              transmit("\"" + line, socket);
            } else {
              transmit(
                  "[solo]" + " " + ("["+gui.currentTargetPanel.getTargetUser()+"]") + " "
                      + ("["+getUser().getUsername()+"]") + " " + "\""+line,  socket);
            }
            gui.currentTargetPanel.show(line.replace("\n", ""));
          }
          talks.clear();
        }
      }
      socket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * send line through target socket
   * 注意事项： 发出的所有信息应带有"\n"，便于readline()
   * @param line-
   * @param socket- 目标socket
   * @throws IOException-
   */
  public void transmit(String line, Socket socket) throws IOException {
      PrintWriter netOut = new PrintWriter(socket.getOutputStream());
      netOut.print(line);
      netOut.flush();
  }

}
