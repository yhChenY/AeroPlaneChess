package gui;

import GAMING.Datas;
import GAMING.Main;
import GAMING.gameMainThread;
import chatroom.User;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class LoadSavePane extends JOptionPane {

  ArrayList<Datas> datas;
  
  public LoadSavePane() {
    createComponent();
  }

  public void createComponent() {
    try {
      datas = Datas.loadDocuments();
      String[] saveNumbers = new String[datas.size()];
      for (int i = 0; i < saveNumbers.length; i++) {
        saveNumbers[i] = "Saved Game #" + i;
      }
      setSize(new Dimension(1440, 810));
      setVisible(true);
      String dataSelected = (String) showInputDialog(this, "Choose a save", "Game-save loader", 1,
          null,
          saveNumbers, saveNumbers[0]);
      User user = new User();
      user.setUsername(Main.myAccount.getName());
      Main.getMainMenu().game = new Game(false, user);
      int selectedNumber = Integer.parseInt(dataSelected.split("#")[1]);
      Main.loadData(datas.get(selectedNumber));
      Main.loaded = true;
      Main.datas = datas.get(selectedNumber);
      gameMainThread mainThread = new gameMainThread("mainThread", null);
      mainThread.start();
      Main.getMainMenu().setVisible(false);
    } catch (Exception e) {
      showMessageDialog(null, "Failed to load corrupted file!");
    }
  }

}
