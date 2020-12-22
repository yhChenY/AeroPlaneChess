package gui;

import Accounts.Account;
import Accounts.AccountSystem;
import chatroom.VerticalFlowLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class RankingListPanel extends JPanel{
  private final JButton returnButton = new JButton();
  public Color backGroundColor = new Color(73, 50, 50);

  public RankingListPanel() {
    run();
  }

  public JButton getReturnButton() {
    return returnButton;
  }

  public void run() {
    
    setBackground(new Color(61, 57, 57, 255));
    setPreferredSize(new Dimension(700,400));
    setLayout(new BorderLayout());

    JLayeredPaneWithBack rankingPanel = new JLayeredPaneWithBack("resources/blackBackground.jpg");
    rankingPanel.setPreferredSize(new Dimension(665,400));
    rankingPanel.setLayout(new VerticalFlowLayout());

    // 退出
    JLayeredPaneWithBack exitPanel = new JLayeredPaneWithBack("resources/blackBackground.jpg");
    exitPanel.setPreferredSize(new Dimension(20, 400));
    exitPanel.setLayout(new BorderLayout());
    add(exitPanel, BorderLayout.EAST);
    returnButton.setPreferredSize(new Dimension(20,380));
    returnButton.setIcon(new ImageIcon("resources/exit.jpg"));
    exitPanel.add(returnButton, BorderLayout.EAST);

    class SubPanel extends JPanel{
      public final String username;
      public final int score;

      public SubPanel(String username, int score) {
        super();
        this.username = username;
        this.score = score;
        this.run();
      }
      public void run() {
        setBackground(new Color(252, 245, 203, 255));
        setPreferredSize(new Dimension(660,60));
        setLayout(null);
        JLabel nameLabel = new JLabel(username, JLabel.CENTER);
        nameLabel.setOpaque(true);
        nameLabel.setBackground(new Color(255, 214, 130));
        nameLabel.setBounds(30,3,65,60);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 15));
        JLabel scoreLabel = new JLabel(score+"", JLabel.CENTER);
        scoreLabel.setOpaque(true);
        scoreLabel.setBackground(new Color(255, 214, 130));
        scoreLabel.setBounds(280,3,200,60);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 15));
        add(nameLabel, BorderLayout.WEST);
        add(scoreLabel, BorderLayout.EAST);
      }

    }

    ArrayList<SubPanel> subPanels = new ArrayList<>();
    for (Account account : AccountSystem.accountList
    ) {
      SubPanel subPanel = new SubPanel(account.getName(), account.getScore());
      subPanels.add(subPanel);
    }
    subPanels.sort(Comparator.comparingInt(o -> o.score));

    for (SubPanel subPanel : subPanels
    ) {
      rankingPanel.add(subPanel, VerticalFlowLayout.TOP);
    }

    add(rankingPanel, BorderLayout.WEST);
    JScrollPane rankingScrollPane = new JScrollPane(rankingPanel);
    rankingScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    rankingScrollPane.setPreferredSize(new Dimension(665,400));
    add(rankingScrollPane, BorderLayout.WEST);
  }

}
