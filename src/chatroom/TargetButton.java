package chatroom;

import javax.swing.JButton;

class TargetButton extends JButton {
  public TargetPanel targetPanel;
  public void setTargetPanel(TargetPanel targetPanel) {
    this.targetPanel = targetPanel;
  }
}
