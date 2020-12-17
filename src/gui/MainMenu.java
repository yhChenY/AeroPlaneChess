package gui;

import GAMING.Main;
import chatroom.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The main menu frame.
 */
public class MainMenu extends JFrame {
  JLayeredPane layeredPane = new JLayeredPaneWithTitle();
  JButton startNew = new JButton("New Game");
  JButton loadSaveButton = new JButton("Load Save Game");
  JButton signIn = new JButton("Sign In/Register");
  JButton leaderboardButton = new JButton("Leaderboard");
  JButton exitButton = new JButton("Exit");
  JCheckBox chooseIfOnline = new JCheckBox("Online Mode", false);
  JDialog chooseTeamMate = new JDialog();

  JDialog settings = new JDialog();
  Font font = new Font("Ravie", Font.PLAIN, 24);

  User user = new User();
  
  Game game = null;

  GridBagLayout layout = new GridBagLayout();
  GridBagConstraints constraints = new GridBagConstraints();

  public MainMenu() {
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

    constraints.gridx = 1; constraints.gridy = 0;
    constraints.gridwidth = 3; constraints.gridheight = 2;
    constraints.insets = new Insets(380,500,15,500);
    constraints.weightx = 1; constraints.weighty = 1;
    startNew.setFont(font);
    startNew.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        dispose();
        game = new Game(chooseIfOnline.isSelected(), user);
      }
    });
    startNew.setFocusPainted(false);
    layeredPane.add(startNew, constraints, JLayeredPane.PALETTE_LAYER);

    constraints.insets = new Insets(5,500,15,500);
    constraints.gridx = 1; constraints.gridy = 2;
    constraints.gridwidth = 3; constraints.gridheight = 2;
    loadSaveButton.setFont(font);
    loadSaveButton.setFocusPainted(false);
    loadSaveButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        System.exit(0);
      }
    });
    layeredPane.add(loadSaveButton, constraints, JLayeredPane.PALETTE_LAYER);

    constraints.gridx = 1; constraints.gridy = 4;
    signIn.setFont(font);
    signIn.setFocusPainted(false);
    signIn.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        JDialog account = new AccountDialog();
      }
    });
    layeredPane.add(signIn, constraints, JLayeredPane.PALETTE_LAYER);

    constraints.gridx = 1; constraints.gridy = 6;
    leaderboardButton.setFont(font);
    leaderboardButton.setFocusPainted(false);
    leaderboardButton.addMouseListener(
        new MouseAdapter() {
          @Override
          public void mouseReleased(MouseEvent e) {
            super.mouseClicked(e);
            RankingListPanel rlp = new RankingListPanel();
            constraints.gridx =1; constraints.gridy = 2;
            constraints.gridwidth = 5; constraints.gridheight = 1;
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
            );
          }
        }
    );
    constraints.gridx = 1; constraints.gridy = 6;
    layeredPane.add(leaderboardButton, constraints, JLayeredPane.PALETTE_LAYER);

    constraints.gridx = 1; constraints.gridy = 8;
    exitButton.setFont(font);
    exitButton.setFocusPainted(false);
    exitButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        System.exit(0);
      }
    });
    layeredPane.add(exitButton, constraints, JLayeredPane.PALETTE_LAYER);

    constraints.gridx = 1; constraints.gridy = 10;
    constraints.insets = new Insets(5,20,15,1175);
    chooseIfOnline.setFont(font);
    chooseIfOnline.setForeground(new Color(0xfbfe93));
    chooseIfOnline.setOpaque(false);
    chooseIfOnline.setFocusPainted(false);
    layeredPane.add(chooseIfOnline, constraints, JLayeredPane.PALETTE_LAYER);

  }
  
  public Game getGame() {
    return game;
  }
}