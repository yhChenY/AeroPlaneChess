package gui;

import chatroom.VerticalFlowLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

public class PlaneChoosePane extends JLayeredPaneWithBack{
  private String planeColor;
  private final PlaneChoosePane planeChoosePane = this;

  public PlaneChoosePane(String filename) {
    super(filename);
    run();
  }

  public String getPlaneColor() {
    return planeColor;
  }

  public void run() {
    
    setPreferredSize(new Dimension(300,400));
    setLayout(new VerticalFlowLayout());

    class colorButton extends JButton {
      public colorButton(String color) {
        setText(color);
        setPreferredSize(new Dimension(300,70));
        addMouseListener(
            new MouseAdapter() {
              @Override
              public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                planeColor = getText();
                planeChoosePane.setVisible(false);
              }
            }
        );
      }
    }

    add(new colorButton("red"), VerticalFlowLayout.BOTTOM);
    add(new colorButton("green"), VerticalFlowLayout.BOTTOM);
    add(new colorButton("yellow"), VerticalFlowLayout.BOTTOM);
    add(new colorButton("blue"), VerticalFlowLayout.BOTTOM);
  }
}
