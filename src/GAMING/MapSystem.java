package GAMING;

import java.util.ArrayList;

public class MapSystem {
  static ArrayList<Block> blocks = new ArrayList<>();
  private static int nowRank = 1;
  
  public static void playerWin(Player player) {
    player.setRank(nowRank);
    nowRank++;
  }
  public static int getNowRank(){
    return nowRank;
  }
}
