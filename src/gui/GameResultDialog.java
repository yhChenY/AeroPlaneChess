package gui;

import gui.BackgroundMusicSystem.Status;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class GameResultDialog extends JDialog {
  private BackgroundMusicSystem bgm;
  private Thread backgroundMusicThread;
  private JLabel resultLabel = new JLabel();
  private JButton exitButton = new JButton("Exit");
  Font font = new Font("Arial", Font.PLAIN, 16);
  GridBagLayout layout = new GridBagLayout();
  GridBagConstraints constraints = new GridBagConstraints();

  /**
   * A dialog announcing the result of a game. Should be called for every player respectively.
   * @param gameResult Indicating the result. True for victory; false for defeat.
   */
  public GameResultDialog(boolean gameResult) {
    setLayout(layout);

    if(gameResult) {
      bgm = new BackgroundMusicSystem(Status.VICTORY);
      resultLabel.setText("Congrats! You won the game.");
    } else {
      bgm = new BackgroundMusicSystem(Status.DEFEAT);
      resultLabel.setText("Sorry, You lost the game.");
    }

    backgroundMusicThread = new Thread(bgm);
    backgroundMusicThread.start();

    resultLabel.setFont(font);
    resultLabel.setHorizontalAlignment(JLabel.CENTER);

    exitButton.setFont(font);
    exitButton.addMouseListener(new gui.MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        if(exitButton.isEnabled()) {
          bgm.stop();
          dispose();
          new MainMenu();
        }
      }
    });

    constraints.fill = GridBagConstraints.BOTH;

    constraints.gridx = 0; constraints.gridy = 0;
    constraints.gridwidth = 2; constraints.gridheight = 1;
    constraints.weightx = 1; constraints.weighty = 0.8;
    add(resultLabel, constraints);

    constraints.insets = new Insets(10, 150, 15, 150);
    constraints.gridx = 0; constraints.gridy = 1;
    constraints.gridwidth = 1; constraints.gridheight = 1;
    constraints.weightx = 1; constraints.weighty = 0.2;
    add(exitButton, constraints);

    setUndecorated(true);
    setModal(true);
    setBounds(520, 305, 400, 150);
    setResizable(false);
    setVisible(true);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
  }
}
