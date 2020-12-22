package gui;

import GAMING.Datas;
import GAMING.Main;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class LoadSavePane extends JOptionPane {

  ArrayList<Datas> datas = Datas.loadDocuments();
  
  public LoadSavePane() {
    createComponent();
  }

  public void createComponent() {
    Object[] dataObjects = datas.toArray();
    setSize(new Dimension(1440, 810));
    setVisible(true);
    Object dataSelected = showInputDialog(this, "Choose a save", "Game-save loader", 1, null,
        dataObjects, dataObjects[0]);
    Main.loadData((Datas) dataSelected);
  }

}
