package chatroom;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TargetPanel extends Thread {
  private final JButton controlButton;
  private final String targetUser;
  public JTextArea chatBox = new JTextArea();
  public JTextArea inputBox = new JTextArea();
  public final Client client;
  private boolean blockStatus = false; //  记录targetUser是否被屏蔽
  private final JPanel rootPanel = new JPanel(new BorderLayout());
  public final Color borderColor = new Color(193, 198, 222);

  public TargetPanel(JButton controlButton, String targetUser, Client client) {
    this.controlButton = controlButton;
    this.targetUser = targetUser;
    this.client = client;
    start();
  }

  public JPanel getRootPanel() {
    return rootPanel;
  }

  public boolean isBlockStatus() {
    return blockStatus;
  }

  public void setBlockStatus(boolean blockStatus) {
    this.blockStatus = blockStatus;
  }

  public JButton getControlButton() {
    return controlButton;
  }

  public String getTargetUser() {
    return targetUser;
  }

  @Override
  public void run() {
    super.run();
//    rootPanel.setBackground(new Color(193, 168, 220, 121));
    //  聊天Panel：仅有一个聊天记录显示
    JPanel chatPanel = new JPanel(new BorderLayout());
    chatPanel.setOpaque(false);
//    chatPanel.setBackground(backgroundColor);
    chatPanel.setBorder(BorderFactory.createLineBorder(borderColor));
    chatBox.setLineWrap(true);
    chatBox.setEditable(false);
    chatBox.setFont(new Font(Font.DIALOG, Font.BOLD, 13));
    chatBox.setOpaque(false);
//    chatBox.setBackground(backgroundColor);
    chatBox.setBorder(BorderFactory.createLineBorder(borderColor));
    JScrollPane scrollPaneShow = new JScrollPane(chatBox);
    scrollPaneShow.setOpaque(false);
    scrollPaneShow.getViewport().setOpaque(false);
//    scrollPaneShow.setBackground(backgroundColor);
    scrollPaneShow.setBorder(BorderFactory.createLineBorder(borderColor));
    chatPanel.add(scrollPaneShow);
    chatPanel.setPreferredSize(new Dimension(400,400));
    rootPanel.add(chatPanel, BorderLayout.NORTH);

    //  输入Panel：包括一个输入框和一个确定键
    JPanel inputPanel = new JPanel();
    inputPanel.setPreferredSize(new Dimension(400,120));
    inputPanel.setLayout(new BorderLayout());
    inputPanel.setOpaque(false);
//    inputPanel.setBackground(backgroundColor);
    inputPanel.setBorder(BorderFactory.createLineBorder(borderColor));
    inputBox.setLineWrap(true);
    inputBox.setEditable(true);
    inputBox.setFont(new Font(Font.DIALOG, Font.BOLD, 13));
    inputBox.setOpaque(false);
//    inputBox.setBackground(backgroundColor);
    inputBox.setBorder(BorderFactory.createLineBorder(borderColor));
    inputBox.addKeyListener(
        new KeyAdapter() {
          @Override
          public void keyReleased(KeyEvent e) {
            super.keyReleased(e);
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
              msg();
            }
          }
        }
    );
    JScrollPane scrollPaneInput = new JScrollPane(inputBox);
    scrollPaneInput.setOpaque(false);
//    scrollPaneInput.setBackground(backgroundColor);
    scrollPaneInput.getViewport().setOpaque(false);
    scrollPaneInput.setBorder(BorderFactory.createLineBorder(borderColor));
    scrollPaneInput.setPreferredSize(new Dimension(310,120));
    inputPanel.add(scrollPaneInput, BorderLayout.WEST);


    JPanel confirmButtonPanel = new JPanel();
    confirmButtonPanel.setOpaque(false);
    confirmButtonPanel.setBorder(BorderFactory.createLineBorder(borderColor));
    confirmButtonPanel.setPreferredSize(new Dimension(70,120));
    // 创建确定按钮
    JButton confirmButton = new JButton();
    confirmButton.addActionListener(
        (ActionEvent e) -> msg());
    confirmButton.setPreferredSize(new Dimension(40,40));
    confirmButtonPanel.add(confirmButton, BorderLayout.CENTER);
    //  创建清屏按钮
    JButton clearButton = new JButton();
    clearButton.setText("Clear");
    clearButton.addActionListener(
        (ActionEvent e) -> chatBox.setText(""));
    clearButton.setPreferredSize(new Dimension(100,30));
    confirmButtonPanel.add(clearButton, BorderLayout.SOUTH);

    inputPanel.add(confirmButtonPanel, BorderLayout.EAST);

    rootPanel.add(inputPanel, BorderLayout.SOUTH);

    rootPanel.setVisible(true);

  }

  /**
   * collect message from inputBox and send it to the corresponding client
   */
  private void msg() {
    //line中带有"\n"
    String line = inputBox.getText();
    inputBox.setText("");
    client.talk(line);
  }

  /**
   * show lines on chatBox
   * @param s-
   */
  public void show(String s) {
    chatBox.append(s+"\n\n");
  }
}
