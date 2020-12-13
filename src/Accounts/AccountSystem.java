package Accounts;

import org.snakeyaml.engine.v2.api.Load;
import org.snakeyaml.engine.v2.api.LoadSettings;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class AccountSystem {
  public static ArrayList<Account> accountList = new ArrayList<>();
  
  static {
    //
    reloadAccountList();
  }
  
  public AccountSystem() {
  
  }
  
  /**
   * @param uid the uid to be examined if it's in the uid-s that has been registered
   * @return true : if @param uid is in / false : NO
   */
  public static boolean isExisted(int uid) {
    boolean temp = false;
    for (Account p : accountList) {
      if (p.getUid() == uid) {
        temp = true;
        break;
      }
    }
    return temp;
  }
  
  /**
   * @param name name to be examined
   * @return true  :has existed / false : has not existed
   */
  public static boolean isExisted(String name) {
    boolean temp = false;
    for (Account p : accountList) {
      if (p.getName().equals(name)) {
        temp = true;
        break;
      }
    }
    return temp;
  }
  
  /**
   * to update the uidList when necessary
   */
  public static void reloadAccountList() {
    accountList.removeAll(accountList);
    LoadSettings loadSettings = LoadSettings.builder().build();
    Load load = new Load(loadSettings);
    try {
      FileReader file = new FileReader("resources/Accounts.yaml");
      List<LinkedHashMap> hashMaps = (List<LinkedHashMap>) load.loadFromReader(file);
      for (LinkedHashMap l : hashMaps) {
        addAccount(new Account(Integer.parseInt(l.get("uid").toString()),
            l.get("userName").toString(), l.get("password").toString(), Integer.parseInt(l.get("score").toString())));
      }
      file.close();
    } catch (Exception exception) {
      System.out.println(exception);
    }
  }
  
  /**
   * @param p account to be added
   * @return true : add successfully / fasle : add failed
   */
  public static boolean addAccount(Account p) {
    boolean temp = false;
    if (isExisted(p.getUid()) || isExisted(p.getName())) {
      return false;
    } else {
      accountList.add(p);
      return true;
    }
  }
  
  public static void saveAccounts() {
    try {
      File file = new File("resources/Accounts.yaml");
      if (!file.exists()) {
        file.createNewFile();
      }
      FileWriter fw = new FileWriter(file);
      for (Account ac : accountList) {
        
        fw.write(ac.toString());
      }
      fw.close();
    } catch (Exception ignored) {
    
    }
    
  }
  
  
}
