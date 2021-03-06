package GAMING;

import Accounts.Account;
import org.snakeyaml.engine.v2.api.Load;
import org.snakeyaml.engine.v2.api.LoadSettings;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class MapSystem {
  static ArrayList<Block> blocks = new ArrayList<>();
  public static Block shitBlock = new Block(Color.RED, Block.Type.COMMON, 8000, 20, -1, -1, -1);
  public static void loadBlocks() {
    LoadSettings loadSettings = LoadSettings.builder().build();
    Load load = new Load(loadSettings);
    try {
      FileReader file = new FileReader("resources/blocks.yaml");
      List<LinkedHashMap> hashMaps = (List<LinkedHashMap>) load.loadFromReader(file);
      for (LinkedHashMap l : hashMaps) {
        Color color = Color.getColor(l.get("blockColor").toString());
        Block.Type type = Block.Type.getType(l.get("blockType").toString());
        int x = Integer.parseInt(l.get("x").toString()) + 330;
        int y = Integer.parseInt(l.get("y").toString()) + 15;
        int id = Integer.parseInt(l.get("id").toString());
        int nid = Integer.parseInt(l.get("nextId").toString());
        int pid = Integer.parseInt(l.get("preId").toString());
        Block b = new Block(color, type, x, y, id, nid, pid);
//        System.out.println(id + " " + type + " " + color);
        blocks.add(b);
      }
      file.close();
    } catch (Exception exception) {
      System.out.println(exception);
    }
    for (Block block : blocks) {
      block.setNextBlock(getNthBlock(block.getNextId()));
      block.setPreBlock(getNthBlock(block.getPreId()));
    }
    //剩余关系未加入
    blocks.get(9).setAfterCornerBlock(blocks.get(58));
    blocks.get(58).setDistance(6);
    blocks.get(59).setDistance(5);
    blocks.get(60).setDistance(4);
    blocks.get(61).setDistance(3);
    blocks.get(62).setDistance(2);
    blocks.get(63).setDistance(1);
    blocks.get(22).setAfterCornerBlock(blocks.get(64));
    blocks.get(64).setDistance(6);
    blocks.get(65).setDistance(5);
    blocks.get(66).setDistance(4);
    blocks.get(67).setDistance(3);
    blocks.get(68).setDistance(2);
    blocks.get(69).setDistance(1);
    blocks.get(35).setAfterCornerBlock(blocks.get(70));
    blocks.get(70).setDistance(6);
    blocks.get(71).setDistance(5);
    blocks.get(72).setDistance(4);
    blocks.get(73).setDistance(3);
    blocks.get(74).setDistance(2);
    blocks.get(75).setDistance(1);
    blocks.get(48).setAfterCornerBlock(blocks.get(52));
    blocks.get(52).setDistance(6);
    blocks.get(53).setDistance(5);
    blocks.get(54).setDistance(4);
    blocks.get(55).setDistance(3);
    blocks.get(56).setDistance(2);
    blocks.get(57).setDistance(1);
    blocks.get(3).setNextFlyBlock(blocks.get(15));
    blocks.get(16).setNextFlyBlock(blocks.get(28));
    blocks.get(29).setNextFlyBlock(blocks.get(41));
    blocks.get(42).setNextFlyBlock(blocks.get(2));
    
  }
  
  public static void saveBlocks() {
    try {
      File file = new File("resources/blocks.yaml");
      if (!file.exists()) {
        file.createNewFile();
      }
      FileWriter fw = new FileWriter(file);
      for (Block b : blocks) {
        fw.write(b.toString());
      }
      fw.close();
    } catch (Exception ignored) {
    
    }
  }
  
  public static Block getNthBlock(int n) {
    if(n==-1){
      return shitBlock;
    }
    Block ans = null;
    for (Block b : blocks) {
      if (b.getId() == n) {
        ans = b;
        break;
      }
    }
    return ans;
  }
  
  public static ArrayList<Block> getBlocks() {
    return blocks;
  }
}
