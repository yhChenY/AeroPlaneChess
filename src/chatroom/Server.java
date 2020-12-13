package chatroom;

import java.io.IOException;
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



  public void runServer() throws IOException {
    System.out.println("Waiting for connection...");
    while(true) {
      Socket socket = serverSocket.accept();
      new ServerThread(socket, this);
    }
  }

}
