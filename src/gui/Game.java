package gui;

import GAMING.Main;
import GAMING.Plane;
import GAMING.Player;
import chatroom.ChatRoom;
import chatroom.User;
import gui.playerbase.BasePanel;
import gui.playerbase.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
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
  private JButton launchAPlaneButton = new JButton("Launch a plane");
  private boolean cheatingMode = false;
  private Player[] players = Main.getPlayers();

  /**
   * Start a new game.
   *
   * @param ifOnline Indicates if the game is in online mode.
   * @param user     Indicates the player.
   */
  public Game(boolean ifOnline, User user) {
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

    if (ifOnline) {
      try {
        addChatPanel(user);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    createComponent(ifOnline);
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

  /**
   * Create components of a game frame.
   *
   * @param ifOnline Indicates if the game is in online mode.
   */
  public void createComponent(boolean ifOnline) {

    String laneImageUrl = "resources/lane.png";
    ImageIcon laneImage = new ImageIcon(laneImageUrl);
    lane.setIcon(laneImage);

    if (ifOnline) {
      lane.setBounds(430, 15, 780, 750);
      greenBase.setBounds(430, 15, 175, 165);
      blueBase.setBounds(430, 593, 175, 165);
      redBase.setBounds(1039, 15, 175, 165);
      yellowBase.setBounds(1039, 593, 175, 165);
    } else {
      lane.setBounds(330, 15, 780, 750);
      greenBase.setBounds(330, 15, 175, 165);
      blueBase.setBounds(330, 593, 175, 165);
      redBase.setBounds(939, 15, 175, 165);
      yellowBase.setBounds(939, 593, 175, 165);
    }

    surrenderButton.setBounds(1250, 680, 150, 50);
    Font font = new Font("Ravie", Font.PLAIN, 16);
    surrenderButton.setFont(font);
    surrenderButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        dispose();
        //resetServer();
        new MainMenu();
      }
    });

    saveGameButton.setBounds(1250, 600, 150, 50);
    font = new Font("Ravie", Font.PLAIN, 16);
    saveGameButton.setFont(font);
    saveGameButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        //saveGame();
        //resetServer();
        new MainMenu();
      }
    });

    rollDiceButton.setBounds(1250, 520, 150, 50);
    rollDiceButton.setFont(font);
    rollDiceButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        new RollDiceDialog(Main.getRoll1(), Main.getRoll2(), Main.isAbleToProduct(),
            Main.isAbleToQuotient(), cheatingMode);
      }
    });

    toggleCheatingModeButton.setBounds(1250, 440, 150, 50);
    toggleCheatingModeButton.setFont(font);
    toggleCheatingModeButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        cheatingMode = !cheatingMode;
        toggleCheatingModeButton.setText(cheatingMode ? "Normal Mode" : "Cheating Mode");
      }
    });

    launchAPlaneButton.setBounds(1230, 360, 190, 50);
    launchAPlaneButton.setFont(font);
    launchAPlaneButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        flushGameFrame();
        //launchAPlane();
      }
    });

    layeredPane.add(lane, JLayeredPane.PALETTE_LAYER);
    layeredPane.add(blueBase, JLayeredPane.MODAL_LAYER);
    layeredPane.add(greenBase, JLayeredPane.MODAL_LAYER);
    layeredPane.add(redBase, JLayeredPane.MODAL_LAYER);
    layeredPane.add(yellowBase, JLayeredPane.MODAL_LAYER);
    layeredPane.add(launchAPlaneButton, JLayeredPane.MODAL_LAYER);
    layeredPane.add(toggleCheatingModeButton, JLayeredPane.MODAL_LAYER);
    layeredPane.add(rollDiceButton, JLayeredPane.MODAL_LAYER);
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
    ChatRoom chatRoom = new ChatRoom(user.getUsername());
    Thread.sleep(500);
    JPanel chatRoomPanel = chatRoom.getGui().getChatRoomPanel();
    chatRoomPanel.setBounds(10, 75, 400, 600);
    chatRoomPanel.setOpaque(true);
    chatRoomPanel.setVisible(true);
    layeredPane.add(chatRoomPanel, JLayeredPane.MODAL_LAYER);
  }

  public void flushGameFrame() {
    BasePanel.flushBasePanel();
    Map<Player, Plane[]> planes = new HashMap<>(0);
    for (Player p : players) {
      ArrayList<Plane> planeArrayList = new ArrayList<>(0);
      for (Plane plane : p.getPlanes()) {
        planeArrayList.add(plane);
      }
      planes.put(p, planeArrayList.toArray(new Plane[0]));
    }
    clearDragLayer();
    for (Player p : players) {
      for (Plane plane : planes.get(p)) {
        JButton planeButton = plane.getButton();
        planeButton.setBounds(plane.getPosition().getX(), plane.getPosition().getY(),
            planeButton.getWidth(), planeButton.getHeight());
        layeredPane.add(planeButton, JLayeredPane.DRAG_LAYER);
      }
    }
  }

  private void clearDragLayer() {
    Component[] components = layeredPane.getComponentsInLayer(JLayeredPane.DRAG_LAYER);
    for (Component c : components) {
      layeredPane.remove(c);
    }
  }
}
