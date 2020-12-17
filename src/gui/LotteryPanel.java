package gui;

import chatroom.VerticalFlowLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class LotteryPanel extends JPanel {
  private final JButton returnButton = new JButton();
  public Color backGroundColor = new Color(73, 50, 50);
  private static final ArrayList<Prop> props = new ArrayList<Prop>(){
    {
      add(new Prop("BOOK", "resources/props/book.png",
          "STOP a round to read this magical book"));
      add(new Prop("SWORD", "resources/props/sword.png",
          "Appoint one plane to RETURN 10 blocks"));
      add(new Prop("DRINK", "resources/props/drink.png",
          "THREE dice will be given to you in the next throw"));
      add(new Prop("RING", "resources/props/ring.png",
          "CANCEL out one attack from other players"));
      add(new Prop("MAGNET", "resources/props/magnet.png",
          "DRAW players within 7 blocks ahead of you to your back"));
    }
  };

  public LotteryPanel() {
    run();
  }

  public JButton getReturnButton() {
    return returnButton;
  }

  public static ArrayList<Prop> getProps() {
    return props;
  }

  public void run() {

    class JLayeredPaneWithBack extends JLayeredPane {
      @Override
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon background = new ImageIcon("resources/blackBackground.jpg");
        background.paintIcon(this, g, 0, 0);
      }
    }

    setBackground(new Color(61, 57, 57, 255));
    setPreferredSize(new Dimension(500, 400));
    setLayout(new BorderLayout());

    JLayeredPaneWithBack prizePanel = new JLayeredPaneWithBack();
    prizePanel.setPreferredSize(new Dimension(300, 400));
    prizePanel.setLayout(new VerticalFlowLayout());

    class SubPanel extends JPanel {
      private String propName;
      private final String filename;
      private final JLabel nameLabel = new JLabel();

      public SubPanel(String filename) {
        super();
        this.filename = filename;
        this.run();
      }

      public String getPropName() {
        return propName;
      }

      public JLabel getNameLabel() {
        return nameLabel;
      }

      public void run() {
        for (Prop prop : props
        ) {
          if (prop.getPhotoAddress().equals(this.filename)) {
            this.propName = prop.getPropName();
          }
        }
        setBackground(new Color(252, 245, 203, 255));
        setPreferredSize(new Dimension(660, 60));
        setLayout(null);
        nameLabel.setBackground(new Color(255, 200, 0));
        nameLabel.setOpaque(true);
        nameLabel.setIcon(new ImageIcon(filename));
        nameLabel.setBounds(30, 3, 42, 55);
        add(nameLabel);
      }
    }
    ArrayList<SubPanel> subPanels = new ArrayList<>();
    for (Prop prop : props
    ) {
      SubPanel prizeSubPanel = new SubPanel(prop.getPhotoAddress());
      subPanels.add(prizeSubPanel);
      prizePanel.add(prizeSubPanel, VerticalFlowLayout.TOP);
    }
    add(prizePanel, BorderLayout.WEST);

    JLayeredPaneWithBack confirmPanel = new JLayeredPaneWithBack();
    confirmPanel.setPreferredSize(new Dimension(200, 400));
    confirmPanel.setLayout(null);
    add(confirmPanel, BorderLayout.EAST);
    JButton startButton = new JButton();
    startButton.addMouseListener(
        new MouseAdapter() {
          @Override
          public void mouseReleased(MouseEvent e) {
            final int[] cnt = {4};
            class paintName extends Thread {
              public void run() {
                //随机数生成1.5~4.5
                double time = 1.5 + (4.51 - 1.5) * Math.random();

                boolean goDown = true;
                while (time > 0) {
                  if (cnt[0] == -1) {
                    cnt[0] = 0;
                    goDown = false;
                  }
                  if (cnt[0] == 5) {
                    cnt[0] = 4;
                    goDown = true;
                  }
                  subPanels.get(cnt[0]).getNameLabel().setBorder(BorderFactory.createMatteBorder(
                      3, 3, 3, 3, new Color(238, 96, 96)));
                  try {
                    Thread.sleep(300);
                  } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                  }
                  subPanels.get(cnt[0]).getNameLabel().setBorder(null);
                  if (goDown) {
                    cnt[0]--;
                  } else {
                    cnt[0]++;
                  }
                  time -= 0.3;
                }
                if (cnt[0] == -1) {
                  cnt[0] = 0;
                }
                if (cnt[0] == 5) {
                  cnt[0] = 4;
                }
                SubPanel targetPrizeSubPanel = subPanels.get(cnt[0]);
                targetPrizeSubPanel.getNameLabel().setBorder(BorderFactory.createMatteBorder(
                    3, 3, 3, 3, new Color(238, 96, 96)));
                new PrizeShowPane(targetPrizeSubPanel.getPropName());
              }
            }
            new paintName().start();
            super.mouseReleased(e);
          }
        }
    );
    startButton.setOpaque(true);
    startButton.setIcon(new ImageIcon("resources/start.png"));
    startButton.setBounds(50, 100, 140, 50);
    confirmPanel.add(startButton);
    returnButton.setOpaque(true);
    returnButton.setIcon(new ImageIcon("resources/exit2.jpg"));
    returnButton.setBounds(130, 230, 50, 50);
    confirmPanel.add(returnButton);

  }
}