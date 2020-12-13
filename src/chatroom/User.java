package chatroom;

import java.io.Serializable;
import java.net.Socket;

public class User implements Serializable {
  private String username;
  private Socket socket;
  private Team team;

  public User() {
  }

  public User(String username, Socket socket) {
    this.username = username;
    this.socket = socket;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setSocket(Socket socket) {
    this.socket = socket;
  }

  public String getUsername() {
    return username;
  }

  public Socket getSocket() {
    return socket;
  }

  public Team getTeam() {
    return team;
  }

  public void setTeam(Team team) {
    this.team = team;
  }
}
