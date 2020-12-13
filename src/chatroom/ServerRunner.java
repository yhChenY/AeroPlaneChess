package chatroom;

public class ServerRunner {

  public static void main(String[] args) {
    try {
      Server server = new Server();

      //  组队模式测试
//      ArrayList<String> blueTeam = new ArrayList<>();
//      blueTeam.add("Van");
//      blueTeam.add("Chen");
//      server.setBlueTeam(blueTeam);
//      ArrayList<String> redTeam = new ArrayList<>();
//      redTeam.add("Feng");
//      server.setRedTeam(redTeam);

      server.runServer();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
