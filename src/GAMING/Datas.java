package GAMING;

import org.snakeyaml.engine.v2.api.Load;
import org.snakeyaml.engine.v2.api.LoadSettings;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Datas {
  Color nowPlayer;
  int nowRank;
  
  int REDhasFinished;
  int REDtoBeSetOff;
  int REDtoBeFinished;
  boolean RED0hasSetOff;
  int RED0position;
  boolean RED1hasSetOff;
  int RED1position;
  boolean RED2hasSetOff;
  int RED2position;
  boolean RED3hasSetOff;
  int RED3position;
  
  int YELLOWhasFinished;
  int YELLOWtoBeSetOff;
  int YELLOWtoBeFinished;
  boolean YELLOW0hasSetOff;
  int YELLOW0position;
  boolean YELLOW1hasSetOff;
  int YELLOW1position;
  boolean YELLOW2hasSetOff;
  int YELLOW2position;
  boolean YELLOW3hasSetOff;
  int YELLOW3position;
  
  int BLUEhasFinished;
  int BLUEtoBeSetOff;
  int BLUEtoBeFinished;
  boolean BLUE0hasSetOff;
  int BLUE0position;
  boolean BLUE1hasSetOff;
  int BLUE1position;
  boolean BLUE2hasSetOff;
  int BLUE2position;
  boolean BLUE3hasSetOff;
  int BLUE3position;
  
  
  int GREENhasFinished;
  int GREENtoBeSetOff;
  int GREENtoBeFinished;
  boolean GREEN0hasSetOff;
  int GREEN0position;
  boolean GREEN1hasSetOff;
  int GREEN1position;
  boolean GREEN2hasSetOff;
  int GREEN2position;
  boolean GREEN3hasSetOff;
  int GREEN3position;
  
  public Datas() {
    
  }
  
  public static Datas getDatasFrom(String s){
    Datas d = new Datas();
    LoadSettings loadSettings = LoadSettings.builder().build();
    Load load = new Load(loadSettings);
    try {
      FileReader file = new FileReader(s);
      List<LinkedHashMap> hashMaps = (List<LinkedHashMap>) load.loadFromReader(file);
      for (LinkedHashMap l : hashMaps) {
        d.nowPlayer = Color.getColor(l.get("nowPlayer").toString());
        d.nowRank = Integer.parseInt(l.get("nowRank").toString());
      
        d.REDhasFinished = Integer.parseInt(l.get("REDhasFinished").toString());
        d.REDtoBeFinished = Integer.parseInt(l.get("REDtoBeFinished").toString());
        d.REDtoBeSetOff = Integer.parseInt(l.get("REDtoBeSetOff").toString());
        d.RED0hasSetOff = Boolean.parseBoolean(l.get("RED0hasSetOff").toString());
        d.RED0position = Integer.parseInt(l.get("RED0position").toString());
        d.RED1hasSetOff = Boolean.parseBoolean(l.get("RED1hasSetOff").toString());
        d.RED1position = Integer.parseInt(l.get("RED1position").toString());
        d.RED2hasSetOff = Boolean.parseBoolean(l.get("RED2hasSetOff").toString());
        d.RED2position = Integer.parseInt(l.get("RED2position").toString());
        d.RED3hasSetOff = Boolean.parseBoolean(l.get("RED3hasSetOff").toString());
        d.RED3position = Integer.parseInt(l.get("RED3position").toString());
      
        d.YELLOWhasFinished = Integer.parseInt(l.get("YELLOWhasFinished").toString());
        d.YELLOWtoBeFinished = Integer.parseInt(l.get("YELLOWtoBeFinished").toString());
        d.YELLOWtoBeSetOff = Integer.parseInt(l.get("YELLOWtoBeSetOff").toString());
        d.YELLOW0hasSetOff = Boolean.parseBoolean(l.get("YELLOW0hasSetOff").toString());
        d.YELLOW0position = Integer.parseInt(l.get("YELLOW0position").toString());
        d.YELLOW1hasSetOff = Boolean.parseBoolean(l.get("YELLOW1hasSetOff").toString());
        d.YELLOW1position = Integer.parseInt(l.get("YELLOW1position").toString());
        d.YELLOW2hasSetOff = Boolean.parseBoolean(l.get("YELLOW2hasSetOff").toString());
        d.YELLOW2position = Integer.parseInt(l.get("YELLOW2position").toString());
        d.YELLOW3hasSetOff = Boolean.parseBoolean(l.get("YELLOW3hasSetOff").toString());
        d.YELLOW3position = Integer.parseInt(l.get("YELLOW3position").toString());
      
        d.BLUEhasFinished = Integer.parseInt(l.get("BLUEhasFinished").toString());
        d.BLUEtoBeFinished = Integer.parseInt(l.get("BLUEtoBeFinished").toString());
        d.BLUEtoBeSetOff = Integer.parseInt(l.get("BLUEtoBeSetOff").toString());
        d.BLUE0hasSetOff = Boolean.parseBoolean(l.get("BLUE0hasSetOff").toString());
        d.BLUE0position = Integer.parseInt(l.get("BLUE0position").toString());
        d.BLUE1hasSetOff = Boolean.parseBoolean(l.get("BLUE1hasSetOff").toString());
        d.BLUE1position = Integer.parseInt(l.get("BLUE1position").toString());
        d.BLUE2hasSetOff = Boolean.parseBoolean(l.get("BLUE2hasSetOff").toString());
        d.BLUE2position = Integer.parseInt(l.get("BLUE2position").toString());
        d.BLUE3hasSetOff = Boolean.parseBoolean(l.get("BLUE3hasSetOff").toString());
        d.BLUE3position = Integer.parseInt(l.get("BLUE3position").toString());
      
        d.GREENhasFinished = Integer.parseInt(l.get("GREENhasFinished").toString());
        d.GREENtoBeFinished = Integer.parseInt(l.get("GREENtoBeFinished").toString());
        d.GREENtoBeSetOff = Integer.parseInt(l.get("GREENtoBeSetOff").toString());
        d.GREEN0hasSetOff = Boolean.parseBoolean(l.get("GREEN0hasSetOff").toString());
        d.GREEN0position = Integer.parseInt(l.get("GREEN0position").toString());
        d.GREEN1hasSetOff = Boolean.parseBoolean(l.get("GREEN1hasSetOff").toString());
        d.GREEN1position = Integer.parseInt(l.get("GREEN1position").toString());
        d.GREEN2hasSetOff = Boolean.parseBoolean(l.get("GREEN2hasSetOff").toString());
        d.GREEN2position = Integer.parseInt(l.get("GREEN2position").toString());
        d.GREEN3hasSetOff = Boolean.parseBoolean(l.get("GREEN3hasSetOff").toString());
        d.GREEN3position = Integer.parseInt(l.get("GREEN3position").toString());
      }
      file.close();
    } catch (Exception exception) {
      System.out.println(exception);
    }
    return d;
  }
  
  public static ArrayList<Datas> loadDocuments() {
    ArrayList<Datas> ans = new ArrayList<>();
    LoadSettings loadSettings = LoadSettings.builder().build();
    Load load = new Load(loadSettings);
    try {
      FileReader file = new FileReader("resources/savedGame.yaml");
      List<LinkedHashMap> hashMaps = (List<LinkedHashMap>) load.loadFromReader(file);
      for (LinkedHashMap l : hashMaps) {
        Datas d = new Datas();
        d.nowPlayer = Color.getColor(l.get("nowPlayer").toString());
        d.nowRank = Integer.parseInt(l.get("nowRank").toString());
        
        d.REDhasFinished = Integer.parseInt(l.get("REDhasFinished").toString());
        d.REDtoBeFinished = Integer.parseInt(l.get("REDtoBeFinished").toString());
        d.REDtoBeSetOff = Integer.parseInt(l.get("REDtoBeSetOff").toString());
        d.RED0hasSetOff = Boolean.parseBoolean(l.get("RED0hasSetOff").toString());
        d.RED0position = Integer.parseInt(l.get("RED0position").toString());
        d.RED1hasSetOff = Boolean.parseBoolean(l.get("RED1hasSetOff").toString());
        d.RED1position = Integer.parseInt(l.get("RED1position").toString());
        d.RED2hasSetOff = Boolean.parseBoolean(l.get("RED2hasSetOff").toString());
        d.RED2position = Integer.parseInt(l.get("RED2position").toString());
        d.RED3hasSetOff = Boolean.parseBoolean(l.get("RED3hasSetOff").toString());
        d.RED3position = Integer.parseInt(l.get("RED3position").toString());
        
        d.YELLOWhasFinished = Integer.parseInt(l.get("YELLOWhasFinished").toString());
        d.YELLOWtoBeFinished = Integer.parseInt(l.get("YELLOWtoBeFinished").toString());
        d.YELLOWtoBeSetOff = Integer.parseInt(l.get("YELLOWtoBeSetOff").toString());
        d.YELLOW0hasSetOff = Boolean.parseBoolean(l.get("YELLOW0hasSetOff").toString());
        d.YELLOW0position = Integer.parseInt(l.get("YELLOW0position").toString());
        d.YELLOW1hasSetOff = Boolean.parseBoolean(l.get("YELLOW1hasSetOff").toString());
        d.YELLOW1position = Integer.parseInt(l.get("YELLOW1position").toString());
        d.YELLOW2hasSetOff = Boolean.parseBoolean(l.get("YELLOW2hasSetOff").toString());
        d.YELLOW2position = Integer.parseInt(l.get("YELLOW2position").toString());
        d.YELLOW3hasSetOff = Boolean.parseBoolean(l.get("YELLOW3hasSetOff").toString());
        d.YELLOW3position = Integer.parseInt(l.get("YELLOW3position").toString());
        
        d.BLUEhasFinished = Integer.parseInt(l.get("BLUEhasFinished").toString());
        d.BLUEtoBeFinished = Integer.parseInt(l.get("BLUEtoBeFinished").toString());
        d.BLUEtoBeSetOff = Integer.parseInt(l.get("BLUEtoBeSetOff").toString());
        d.BLUE0hasSetOff = Boolean.parseBoolean(l.get("BLUE0hasSetOff").toString());
        d.BLUE0position = Integer.parseInt(l.get("BLUE0position").toString());
        d.BLUE1hasSetOff = Boolean.parseBoolean(l.get("BLUE1hasSetOff").toString());
        d.BLUE1position = Integer.parseInt(l.get("BLUE1position").toString());
        d.BLUE2hasSetOff = Boolean.parseBoolean(l.get("BLUE2hasSetOff").toString());
        d.BLUE2position = Integer.parseInt(l.get("BLUE2position").toString());
        d.BLUE3hasSetOff = Boolean.parseBoolean(l.get("BLUE3hasSetOff").toString());
        d.BLUE3position = Integer.parseInt(l.get("BLUE3position").toString());
        
        d.GREENhasFinished = Integer.parseInt(l.get("GREENhasFinished").toString());
        d.GREENtoBeFinished = Integer.parseInt(l.get("GREENtoBeFinished").toString());
        d.GREENtoBeSetOff = Integer.parseInt(l.get("GREENtoBeSetOff").toString());
        d.GREEN0hasSetOff = Boolean.parseBoolean(l.get("GREEN0hasSetOff").toString());
        d.GREEN0position = Integer.parseInt(l.get("GREEN0position").toString());
        d.GREEN1hasSetOff = Boolean.parseBoolean(l.get("GREEN1hasSetOff").toString());
        d.GREEN1position = Integer.parseInt(l.get("GREEN1position").toString());
        d.GREEN2hasSetOff = Boolean.parseBoolean(l.get("GREEN2hasSetOff").toString());
        d.GREEN2position = Integer.parseInt(l.get("GREEN2position").toString());
        d.GREEN3hasSetOff = Boolean.parseBoolean(l.get("GREEN3hasSetOff").toString());
        d.GREEN3position = Integer.parseInt(l.get("GREEN3position").toString());
        ans.add(d);
      }
      file.close();
      System.out.println("Read " + ans.size() + " saved games.");
    } catch (Exception exception) {
      System.out.println(exception);
    }
    return ans;
  }
}
