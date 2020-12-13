package chatroom;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class GUI extends Thread {
  private final Client client;
  private final JPanel status = new JPanel();
  private final JPanel chatRoomPanel = new JPanel();
  public TargetPanel currentTargetPanel = null;
  public JPopupMenu currentPopUpMenu = null;
  private final ArrayList<TargetPanel> targetPanels = new ArrayList<>(); // 记录所有用户的panel
  private final ArrayList<TargetButton> targetButtons = new ArrayList<>(); // 记录所有用户的按钮
  private boolean teamFlag = false; // 标志是否存在队友，判断模式
  private boolean teamSet = false; // 判断是否已经找到队友，若已找到将不再添加新的聊天target
  public final Color borderColor = new Color(193, 198, 222);
  public final Color original = new Color(0xFFBFEBFA, true);
  public final Color blockedColor = new Color(108, 108, 108, 245);
  public final Color selectedColor = new Color(253, 248, 185, 255);

  public JPanel getChatRoomPanel() {
    return chatRoomPanel;
  }

  public ArrayList<TargetPanel> getTargetPanels() {
    return targetPanels;
  }

  public GUI(Client client) {
    this.client = client;
    chatRoomPanel.setBorder(BorderFactory.createLineBorder(borderColor));
    chatRoomPanel.setOpaque(false);
//    chatRoomPanel.setBackground(backgroundColor);
    chatRoomPanel.setPreferredSize(new Dimension(400,600));
    chatRoomPanel.setLayout(new BorderLayout());
    addTarget("ALL USERS");
  }

  public void run() {

    // 隐藏currentPopUpMenu
    chatRoomPanel.addMouseListener(
        new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            if (currentPopUpMenu != null) {
              currentPopUpMenu.setVisible(false);
            }
          }
        }
    );

    //  状态Panel: 用于转换targetPanel和拉黑
    status.setOpaque(false);
//    status.setBackground(backgroundColor);
    status.setBorder(BorderFactory.createLineBorder(borderColor));
    status.setPreferredSize(new Dimension(400, 80));
    currentTargetPanel.getRootPanel().add(status, BorderLayout.CENTER);

    chatRoomPanel.setVisible(true);
  }

  public void addTarget(String username) {

    //如果已经找到队友，直接退出
    if (teamSet) {
      return;
    }

    //添加按钮情况：单人模式或者组队模式还未找到队友
    if (!teamFlag || client.getTeammate().equals(username)) {
      TargetButton userButton = new TargetButton();
      userButton.setBackground(original);
      userButton.setText(username);
      Color original = userButton.getBackground(); // 记录组件原始颜色，作为状态标志
      status.add(userButton);
      status.validate();
      targetButtons.add(userButton);

      //给该用户创建一个专属panel
      TargetPanel targetPanel = new TargetPanel(userButton, username, client);
      userButton.setTargetPanel(targetPanel);
      targetPanels.add(targetPanel);

      if (currentTargetPanel == null) {
        userButton.setBackground(selectedColor);
        currentTargetPanel = userButton.targetPanel;
        chatRoomPanel.add(currentTargetPanel.getRootPanel());
        chatRoomPanel.validate();
      }

      if (teamFlag  && client.getTeammate().equals(userButton.targetPanel.getTargetUser())) {
        teamSet = true;
        userButton.setText("TEAMMATE");
      }

      userButton.addMouseListener(
          new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
              super.mousePressed(e);
              // 切换targetPanel
              if (e.getButton() == MouseEvent.BUTTON1) {
                if (currentTargetPanel.getRootPanel() == userButton.targetPanel.getRootPanel()) {
                  return;
                }
                if (!currentTargetPanel.isBlockStatus()) {
                  currentTargetPanel.getControlButton().setBackground(original);
                }
                if (!userButton.targetPanel.isBlockStatus()) {
                  userButton.setBackground(selectedColor);
                }
                chatRoomPanel.remove(currentTargetPanel.getRootPanel());
                chatRoomPanel.repaint();
                currentTargetPanel = userButton.targetPanel;
                chatRoomPanel.add(currentTargetPanel.getRootPanel());
                currentTargetPanel.getRootPanel().add(status, BorderLayout.CENTER);
                status.validate();
                chatRoomPanel.validate();
              }
              // 弹出拉黑键
              if (e.getButton() == MouseEvent.BUTTON3) {
                //全体频道不能屏蔽
                if (userButton.getText().equals("ALL USERS")) {
                  return;
                }
                // 确定menu弹出位置
                setPopUpMenu(userButton, e.getX()+215,e.getY()+400);
                //currentPopUpMenu 5秒自动消失
                new MenuTimer(currentPopUpMenu);
              }
            }
          }
      );
    }

  }

  public void updateUsers(String newUsername) {
    if (!newUsername.equals(client.getUser().getUsername())) {
      addTarget(newUsername);
    }
  }

  public void setTeamFlag(boolean teamFlag) {
    this.teamFlag = teamFlag;
  }

  /**
   *     // 给button添加菜单
   * @param x-鼠标x位置
   * @param y-鼠标y位置
   */
  private void setPopUpMenu(TargetButton userButton, int x, int y) {
    JPopupMenu buttonMenu = new JPopupMenu();
    JButton blockButton = new JButton();
    if ((original.equals(userButton.getBackground())
        || (selectedColor.equals(userButton.getBackground())))) {
      blockButton.setText("Block");
    } else {
      blockButton.setText("Normalize");
    }
    buttonMenu.add(blockButton);
    blockButton.addMouseListener(
        new MouseAdapter() {
          @Override
          public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
            if (blockButton.getText().equals("Block")) {
              block(userButton.targetPanel.getTargetUser());
              blockButton.setText("Normalize");
              userButton.setBackground(blockedColor);
            } else {
              normalize(userButton.targetPanel.getTargetUser());
              blockButton.setText("Block");
              if (userButton.targetPanel.equals(currentTargetPanel)) {
                userButton.setBackground(selectedColor);
              } else {
                userButton.setBackground(original);
              }
            }
          }
        }
    );
    buttonMenu.setLocation(x,y);
    if (currentPopUpMenu != null) {
      currentPopUpMenu.setVisible(false);
    }
    currentPopUpMenu = buttonMenu;
    status.add(buttonMenu);
    buttonMenu.setVisible(true);
  }


  public void block(String targetUsername) {
    for (TargetPanel p : targetPanels
    ) {
      if (p.getTargetUser().equals(targetUsername)) {
        p.setBlockStatus(true);
      }
    }
    client.getBlocks().add(targetUsername);
    try {
      client.transmit("[block] " + "["+targetUsername+"] " + "["+client.getUser().getUsername()+"]" + "\n",
          client.getSocket());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void normalize(String targetUsername) {
    for (TargetPanel p : targetPanels
    ) {
      if (p.getTargetUser().equals(targetUsername)) {
        p.setBlockStatus(false);
      }
    }
    client.getBlocks().remove(targetUsername);
    try {
      client.transmit(
          "[normalize] " + "["+targetUsername+"] " + "["+client.getUser().getUsername()+"]" + "\n",
          client.getSocket());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void deleteUsers(String username) {
    //传进来的username有[]包裹
    TargetButton deleteButton = null;
    for (TargetButton targetButton : targetButtons
    ) {
      if (("["+targetButton.targetPanel.getTargetUser()+"]").equals(username)) {
        targetButton.setVisible(false);
        deleteButton = targetButton;
      }
    }
    targetButtons.remove(deleteButton);
  }
}
