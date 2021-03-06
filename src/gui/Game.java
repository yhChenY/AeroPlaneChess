package gui;

import GAMING.Main;
import GAMING.Plane;
import GAMING.Player;
import chatroom.ChatRoom;
import chatroom.User;
import gui.BackgroundMusicSystem.Status;
import gui.playerbase.BasePanel;
import gui.playerbase.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Game frame.
 */
public class Game extends JFrame {

  JLayeredPane layeredPane = new JLayeredPaneWithBackground();
  private JLabel lane = new JLabel();
  private BasePanel greenBase = new BasePanel(Color.GREEN);
  private BasePanel yellowBase = new BasePanel(Color.YELLOW);
  private BasePanel redBase = new BasePanel(Color.RED);
  private BasePanel blueBase = new BasePanel(Color.BLUE);
  private JButton surrenderButton = new JButton("Surrender");
  private JButton saveGameButton = new JButton("Save game");
  private JButton rollDiceButton = new JButton("Roll dice");
  private JButton toggleCheatingModeButton = new JButton("Cheating Mode");
  //  private JButton launchAPlaneButton = new JButton("Launch a plane");
  private JButton nextTurnButton = new JButton("Next turn");
  private Player nowPlayer;
  private Player thisPlayer;
  private boolean cheatingMode = false;
  private Player[] players = Main.getPlayers();
  private BackgroundMusicSystem bgm;
  private Thread backgroundMusicThread;
  private ChatRoom chatRoom;
  private String hostIpAddress;

  /**
   * Start a new game.
   *
   * @param ifOnline Indicates if the game is in online mode.
   * @param user     Indicates the player.
   */
  public Game(boolean ifOnline, User user) {
    thisPlayer = Main.getPlayerByColor(GAMING.Color.RED);
    nowPlayer = Main.getPlayerByColor(Main.nowPlayer);

    setTitle("Rick and Morty's Gamble");
    Main.setIsOnLineGame(ifOnline);
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (IllegalAccessException | UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException e) {
      e.printStackTrace();
    }
    this.setLayeredPane(layeredPane);
    this.setLocation(0, 0);
    this.setResizable(false);
    this.setSize(new Dimension(1440, 810));
    this.setVisible(true);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    bgm = new BackgroundMusicSystem(Status.GAMING);
    backgroundMusicThread = new Thread(bgm);
    backgroundMusicThread.start();

    createComponent(ifOnline);
  }
  
  public Game(User user, String ip) {
    Main.setIsOnLineGame(true);
    hostIpAddress = ip;
    thisPlayer = Main.getPlayerByColor(GAMING.Color.RED);
    System.err.println("Using red as default color of player. Change it.");
    nowPlayer = Main.getPlayerByColor(Main.nowPlayer);

    setTitle("Rick and Morty's Gamble");
    Main.setIsOnLineGame(true);
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (IllegalAccessException | UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException e) {
      e.printStackTrace();
    }
    this.setLayeredPane(layeredPane);
    this.setLocation(0, 0);
    this.setResizable(false);
    this.setSize(new Dimension(1440, 810));
    this.setVisible(true);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    bgm = new BackgroundMusicSystem(Status.GAMING);
    backgroundMusicThread = new Thread(bgm);
    backgroundMusicThread.start();

    createComponent(true);
    try {
      addChatPanel(user);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * Make a JLabel display an image.
   *
   * @param label The JLabel that is going to display an image.
   * @param url   Location of the image.
   */
  public static void setImage(JLabel label, String url) {
    ImageIcon image = new ImageIcon(url);
    label.setIcon(image);
  }

  public ChatRoom getChatRoom() {
    return chatRoom;
  }

  /**
   * Create components of a game frame.
   *
   * @param ifOnline Indicates if the game is in online mode.
   */
  public void createComponent(boolean ifOnline) {

    String laneImageUrl = "resources/lane.png";
    ImageIcon laneImage = new ImageIcon(laneImageUrl);
    lane.setIcon(laneImage);

/*    if (ifOnline) {
      lane.setBounds(430, 15, 780, 750);
      greenBase.setBounds(430, 15, 175, 165);
      blueBase.setBounds(430, 593, 175, 165);
      redBase.setBounds(1039, 15, 175, 165);
      yellowBase.setBounds(1039, 593, 175, 165);
    } else {*/
      lane.setBounds(330, 15, 780, 750);
      greenBase.setBounds(330, 15, 175, 165);
      blueBase.setBounds(330, 593, 175, 165);
      redBase.setBounds(939, 15, 175, 165);
      yellowBase.setBounds(939, 593, 175, 165);
//    }

    Font font = new Font("Ravie", Font.PLAIN, 16);

    rollDiceButton.setBounds(1230, 15, 190, 50);
    rollDiceButton.setFont(font);
    rollDiceButton.addMouseListener(new gui.MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        if (rollDiceButton.isEnabled()) {
          new RollDiceDialog(Main.getRoll1(), Main.getRoll2(), Main.isAbleToProduct(),
              Main.isAbleToQuotient(), cheatingMode);
          rollDiceButton.setEnabled(Main.getSum() >= 10 ? true : false);
        }
      }
    });

    nextTurnButton.setBounds(1230, 175, 190, 50);
    nextTurnButton.setFont(font);
    nextTurnButton.addMouseListener(new gui.MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        if (nextTurnButton.isEnabled()) {
          Main.nextTurn();
        }
      }
    });

    if (!nowPlayer.equals(thisPlayer)) {
      rollDiceButton.setEnabled(false);
      nextTurnButton.setEnabled(false);
    }

    toggleCheatingModeButton.setBounds(1250, 520, 150, 50);
    toggleCheatingModeButton.setFont(font);
    toggleCheatingModeButton.addMouseListener(new gui.MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        if (toggleCheatingModeButton.isEnabled()) {
          cheatingMode = !cheatingMode;
          toggleCheatingModeButton.setText(cheatingMode ? "Normal Mode" : "Cheating Mode");
        }
      }
    });

    saveGameButton.setBounds(1250, 600, 150, 50);
    font = new Font("Ravie", Font.PLAIN, 16);
    saveGameButton.setFont(font);
    saveGameButton.addMouseListener(new gui.MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        if (saveGameButton.isEnabled()) {
          Main.saveGameData();
//          new MainMenu();
          Main.getMainMenu().setVisible(true);
          dispose();
        }
      }
    });

    surrenderButton.setBounds(1250, 680, 150, 50);
    surrenderButton.setFont(font);
    surrenderButton.addMouseListener(new gui.MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        if (surrenderButton.isEnabled()) {
          bgm.stop();
          dispose();
          Main.initializeData();
          //resetServer();
          //new MainMenu();
          Main.getMainMenu().setVisible(true);
        }
      }
    });

    layeredPane.add(lane, JLayeredPane.PALETTE_LAYER);
    layeredPane.add(blueBase, JLayeredPane.MODAL_LAYER);
    layeredPane.add(greenBase, JLayeredPane.MODAL_LAYER);
    layeredPane.add(redBase, JLayeredPane.MODAL_LAYER);
    layeredPane.add(yellowBase, JLayeredPane.MODAL_LAYER);
    layeredPane.add(rollDiceButton, JLayeredPane.MODAL_LAYER);
    layeredPane.add(nextTurnButton, JLayeredPane.MODAL_LAYER);
    layeredPane.add(toggleCheatingModeButton, JLayeredPane.MODAL_LAYER);
    layeredPane.add(surrenderButton, JLayeredPane.MODAL_LAYER);
    layeredPane.add(saveGameButton, JLayeredPane.MODAL_LAYER);
    layeredPane.setOpaque(false);
  }

  /**
   * @param apron      The apron whose status is changing.
   * @param color      The color of the apron. (Apron class does not include color attribute)
   * @param isOccupied Whether the apron will be occupied. Indicates the next status.
   * @deprecated Change the status of an apron.
   */
  public void toggleApron(JLabel apron, Color color, boolean isOccupied) {
    if (!isOccupied) {
      setImage(apron, "resources/emptyApron.jpg");
    } else {
      setImage(apron, "resources/" + color.getColorName() + "Airplane.jpg");
    }
  }

  /**
   * Add a chat panel to the game frame. DO NOT call it outside the Game class.
   *
   * @param user The player.
   * @throws InterruptedException Thread related problems. Don't know how to handle.
   */
  private void addChatPanel(User user) throws InterruptedException {
    System.out.println(hostIpAddress);
//    chatRoom = new ChatRoom("Chen", hostIpAddress);
    chatRoom = new ChatRoom(user.getUsername(), hostIpAddress);
    Thread.sleep(500);
    JPanel chatRoomPanel = chatRoom.getGui().getChatRoomPanel();
    chatRoomPanel.setBounds(10, 75, 300, 600);
    chatRoomPanel.setOpaque(true);
    chatRoomPanel.setVisible(true);
    layeredPane.add(chatRoomPanel, JLayeredPane.MODAL_LAYER);
  }

  public void flushGameFrame() {

    nowPlayer = Main.getPlayerByColor(Main.nowPlayer);

    BasePanel.flushBasePanel();
    Map<Player, Plane[]> planesOfPlayer = new HashMap<>(0);
    for (Player p : players) {
      ArrayList<Plane> planeArrayList = new ArrayList<>(0);
      for (Plane plane : p.getPlanes()) {
        planeArrayList.add(plane);
      }
      planesOfPlayer.put(p, planeArrayList.toArray(new Plane[0]));
    }
    clearDragLayer();
    for (Player p : players) {
      Plane[] planes = planesOfPlayer.get(p);
      for (Plane plane : planes) {
        JButton planeButton = plane.getButton();
        planeButton.setBounds(plane.getPosition().getX(), plane.getPosition().getY(),
            planeButton.getWidth(), planeButton.getHeight());
        layeredPane.add(planeButton, JLayeredPane.DRAG_LAYER);
      }
    }
    layeredPane.repaint();
    layeredPane.validate();

    layeredPane.repaint();
    layeredPane.validate();
    /*if (thisPlayer == nowPlayer) {
      rollDiceButton.setEnabled(true);
      new YourTurnDialog();
    }*/
  }
  
  public void promptYourTurn() {
    rollDiceButton.setEnabled(true);
    new YourTurnDialog();
  }

  private void clearDragLayer() {
    Component[] components = layeredPane.getComponentsInLayer(JLayeredPane.DRAG_LAYER);
    for (Component c : components) {
      layeredPane.remove(c);
    }
  }
  
  public void setHostAddress(String ip) {
    hostIpAddress = ip;
  }
}
