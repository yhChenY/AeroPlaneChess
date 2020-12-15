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
  
  public static void loadBlocks() {
    LoadSettings loadSettings = LoadSettings.builder().build();
    Load load = new Load(loadSettings);
    try {
      FileReader file = new FileReader("resources/blocks.yaml");
      List<LinkedHashMap> hashMaps = (List<LinkedHashMap>) load.loadFromReader(file);
      for (LinkedHashMap l : hashMaps) {
        Color color = Color.getColor(l.get("blockColor").toString());
        Block.Type type = Block.Type.getType(l.get("blockType").toString());
        int x = Integer.parseInt(l.get("x").toString()) + 300;
        int y = Integer.parseInt(l.get("y").toString()) + 15;
        int id = Integer.parseInt(l.get("id").toString());
        int nid = Integer.parseInt(l.get("nextId").toString());
        int pid = Integer.parseInt(l.get("preId").toString());
        Block b = new Block(color, type, x, y, id, nid, pid);
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
