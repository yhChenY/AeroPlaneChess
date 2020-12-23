package gui;

import Accounts.Account;
import GAMING.Datas;
import GAMING.gameMainThread;
import chatroom.Client;
import chatroom.User;
import gui.BackgroundMusicSystem.Status;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayer;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * The main menu frame.
 */
public class MainMenu extends JFrame {

  JDialog settings = new JDialog();
  Font font = new Font("Ravie", Font.PLAIN, 24);
  User user = new User();
  Account account = new Account();
  Game game = null;
  private String ipAddress;
  GridBagLayout layout = new GridBagLayout();
  GridBagConstraints constraints = new GridBagConstraints();
  private JLayeredPane layeredPane = new JLayeredPaneWithTitle();
  private JButton startNew = new JButton("New Game");
  private JButton loadSaveButton = new JButton("Load Save Game");
  private JButton signIn = new JButton("Sign In/Register");
  private JButton leaderboardButton = new JButton("Leaderboard");
  private JButton exitButton = new JButton("Exit");
  private JCheckBox chooseIfOnline = new JCheckBox("Online Mode", false);
  private JDialog chooseTeamMate = new JDialog();
  private BackgroundMusicSystem bgm;
  private Thread backgroundMusicThread;

  public MainMenu() {
    bgm = new BackgroundMusicSystem(Status.MAIN_MENU);
    backgroundMusicThread = new Thread(bgm);
    backgroundMusicThread.start();
    setTitle("Rick and Morty's Gamble");

    createComponent();

    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    layeredPane.setSize(new Dimension(1440, 810));

    this.setLayeredPane(layeredPane);
    this.setLocation(0, 0);
    this.setResizable(false);
    this.setSize(new Dimension(1440, 810));
    this.setVisible(true);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  private void createComponent() {
    layeredPane.setLayout(layout);
    constraints.fill = GridBagConstraints.BOTH;

    constraints.gridx = 1;
    constraints.gridy = 0;
    constraints.gridwidth = 3;
    constraints.gridheight = 2;
    constraints.insets = new Insets(380, 500, 15, 500);
    constraints.weightx = 1;
    constraints.weighty = 1;
    startNew.setFont(font);
    startNew.addMouseListener(new gui.MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        bgm.stop();
        Client client;
        if (chooseIfOnline.isSelected()) {
          ConnectHostDialog connectHostDialog = new ConnectHostDialog();
          while(!connectHostDialog.getConnectionStatus()) {
          }
          game = new Game(user, ipAddress);
          try {// 防止用户还没拿到颜色
            Thread.sleep(1000);
          } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
          }
          client = game.getChatRoom().getClient();
          setVisible(false);
        } else {
          setVisible(false);
          game = new Game(false, user);
          client = null;
        }
        gameMainThread mainThread = new gameMainThread("mainThread", client);
        mainThread.start();
      }
    });
    startNew.setFocusPainted(false);
    layeredPane.add(startNew, constraints, JLayeredPane.PALETTE_LAYER);

    constraints.insets = new Insets(5, 500, 15, 500);
    constraints.gridx = 1;
    constraints.gridy = 2;
    constraints.gridwidth = 3;
    constraints.gridheight = 2;
    loadSaveButton.setFont(font);
    loadSaveButton.setFocusPainted(false);
    loadSaveButton.addMouseListener(new gui.MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        JOptionPane loadSavePane = new LoadSavePane();
        loadSavePane.setVisible(true);
      }
    });
    layeredPane.add(loadSaveButton, constraints, JLayeredPane.PALETTE_LAYER);

    constraints.gridx = 1;
    constraints.gridy = 4;
    signIn.setFont(font);
    signIn.setFocusPainted(false);
    signIn.addMouseListener(new gui.MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        JDialog accountDialog = new AccountDialog();
      }
    });
    layeredPane.add(signIn, constraints, JLayeredPane.PALETTE_LAYER);

    constraints.gridx = 1;
    constraints.gridy = 6;
    leaderboardButton.setFont(font);
    leaderboardButton.setFocusPainted(false);
    /*leaderboardButton.addMouseListener(new gui.MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        System.exit(0);
      }
    });*/
    leaderboardButton.addMouseListener(
        new MouseAdapter() {
          @Override
          public void mouseReleased(MouseEvent e) {
            super.mouseClicked(e);
            if (leaderboardButton.isEnabled()) {
              JFrame leaderboardFrame = new JFrame("Leaderboard");
              leaderboardFrame.setSize(new Dimension(1440, 810));
              JLayeredPane layeredPane = new JLayeredPaneWithTitle();
              leaderboardFrame.setLayeredPane(layeredPane);
              RankingListPanel rlp = new RankingListPanel();
              rlp.setBounds(370, 350, 700, 400);
              rlp.setVisible(true);
              layeredPane.add(rlp, JLayeredPane.MODAL_LAYER);
              leaderboardFrame.setVisible(true);
              layeredPane.setVisible(true);
              leaderboardFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
              JButton gobackButton = rlp.getReturnButton();
              gobackButton.addMouseListener(new gui.MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                  super.mouseClicked(e);
                  leaderboardFrame.dispose();
                }
              });
              /*RankingListPanel rlp = new RankingListPanel();
              constraints.gridx = 1;
              constraints.gridy = 2;
              constraints.gridwidth = 3;
              constraints.gridheight = 5;
              layeredPane.removeAll();
              layeredPane.repaint();
              layeredPane.add(rlp, constraints);
              layeredPane.validate();
              JButton returnButton = rlp.getReturnButton();
              returnButton.addMouseListener(
                  new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                      super.mouseReleased(e);
                      layeredPane.remove(rlp);
                      layeredPane.repaint();
                      createComponent();
                    }
                  }
              );*/
            }
          }
        }
    );
    constraints.gridx = 1;
    constraints.gridy = 6;
    layeredPane.add(leaderboardButton, constraints, JLayeredPane.PALETTE_LAYER);

    constraints.gridx = 1;
    constraints.gridy = 8;
    exitButton.setFont(font);
    exitButton.setFocusPainted(false);
    exitButton.addMouseListener(new gui.MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        bgm.stop();
        System.exit(0);
      }
    });
    layeredPane.add(exitButton, constraints, JLayeredPane.PALETTE_LAYER);

    constraints.gridx = 1;
    constraints.gridy = 10;
    constraints.insets = new Insets(5, 20, 15, 1175);
    chooseIfOnline.setFont(font);
    chooseIfOnline.setForeground(new java.awt.Color(0xfbfe93));
    chooseIfOnline.setOpaque(false);
    chooseIfOnline.setFocusPainted(false);
    layeredPane.add(chooseIfOnline, constraints, JLayeredPane.PALETTE_LAYER);

    try {
      InetAddress ipAddr = InetAddress.getLocalHost();
      String ipAddressString = ipAddr.getHostAddress();
      JLabel ipAddressLabel = new JLabel(ipAddressString);
      ipAddressLabel.setFont(new Font("Ravie", Font.PLAIN, 20));
      ipAddressLabel.setForeground(new Color(0xfbfe93));
      ipAddressLabel.setHorizontalAlignment(JLabel.RIGHT);
      ipAddressLabel.setOpaque(false);
      constraints.anchor = GridBagConstraints.EAST;
      constraints.gridx = 2;
      constraints.gridy = 10;
      constraints.insets = new Insets(5, 1175, 15, 20);
      layeredPane.add(ipAddressLabel, constraints, JLayeredPane.PALETTE_LAYER);
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
  }

  public Game getGame() {
    return game;
  }
  
  public void setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
  }
  
  public void setAccount(Account account) {
    this.account = account;
    user.setUsername(account.getName());
    setTitle(user.getUsername() + ": " + getTitle());
  }
}