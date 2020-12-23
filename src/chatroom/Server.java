package chatroom;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * the only server to deal with 4 sockets
 */
public class Server {
  public static final int PORT = 4320;
  public ServerSocket serverSocket;
  private ArrayList<User> users = new ArrayList<>();
  public ArrayList<String> redTeam = new ArrayList<>();
  public ArrayList<String> blueTeam = new ArrayList<>();
  private boolean teamMode = false;
  private boolean start = false;


  public Server() throws IOException {
    serverSocket = new ServerSocket(PORT);
  }

  public ArrayList<User> getUsers() {
    return users;
  }

  public void resetServer() {
    users = new ArrayList<>();
  }
  /**
   * @param redTeam-the arraylist with blue team members
   */
  public void setRedTeam(ArrayList<String> redTeam) {
    this.redTeam = redTeam;
  }

  /**
   * @param blueTeam-the arraylist with blue team members
   */
  public void setBlueTeam(ArrayList<String> blueTeam) {
    this.blueTeam = blueTeam;
  }



  public void runServer() throws IOException, InterruptedException {
    System.out.println("Waiting for connection...");
    String[] colors = {"red", "yellow", "blue", "green"};
    int cnt = 0;
    while(true) {
      Socket socket = serverSocket.accept();
      if (cnt%2 == 0) {
        cnt++;
        continue;
      }
      new ServerThread(socket, colors[cnt/2], this);
      cnt++;
      if (cnt/2 == 4) {
        cnt = 0;
      }
      Thread.sleep(1000);
      System.out.println(users.size());
      if (users.size() == 4) {
        transmit2All("[start]" + " ", null);
      }
    }
  }

  /**
   * @param line-
   * @param selfSocket send message to any user except myself
   */
  public void transmit2All(String line, Socket selfSocket) throws IOException {
    for (User user : users
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

}
