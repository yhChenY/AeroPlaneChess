package gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 * new RollDiceDialog() would be OK.
 */
public class RollDiceDialog extends JDialog {
  int randomNumber1 = (int)(Math.random() * 60 + 10) / 10;
  int randomNumber2 = (int)(Math.random() * 60 + 10) / 10;
  JLabel randomNumbers = new JLabel("You rolled: \n" +randomNumber1 + " " + randomNumber2, JLabel.CENTER);
  JButton plusButton = new JButton("+");
  JButton minusButton = new JButton("-");
  JButton multiplyButton = new JButton("x");
  JButton divideButton = new JButton("÷");
  GridBagLayout layout = new GridBagLayout();
  GridBagConstraints constraints = new GridBagConstraints();
  Font font = new Font("Arial", Font.PLAIN, 16);

  public RollDiceDialog(int r1,int r2,boolean ableToProduct,boolean ableToQuotient) {
    multiplyButton.setEnabled(ableToProduct);
    divideButton.setEnabled(ableToQuotient);

    setLayout(layout);

    randomNumbers.setFont(font);
    randomNumbers.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        dispose();
      }
    });
    plusButton.setFont(font);
    plusButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        dispose();
      }
    });
    minusButton.setFont(font);
    minusButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        dispose();
      }
    });
    multiplyButton.setFont(font);
    multiplyButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        dispose();
      }
    });

    constraints.fill = GridBagConstraints.BOTH;
    constraints.gridx = 0; constraints.gridy = 0;
    constraints.gridwidth = 4; constraints.gridheight = 1;
    constraints.weightx = 1; constraints.weighty = 1;
    constraints.insets = new Insets(50, 20, 10, 20);
    add(randomNumbers, constraints);

    constraints.gridx = 0; constraints.gridy = 1;
    constraints.gridwidth = 1; constraints.gridheight = 1;
    constraints.weightx = 1; constraints.weighty = 1;
    constraints.insets = new Insets(20, 20, 10, 20);
    add(plusButton, constraints);

    constraints.gridx = 1; constraints.gridy = 1;
    constraints.gridwidth = 1; constraints.gridheight = 1;
    constraints.weightx = 1; constraints.weighty = 1;
    add(minusButton, constraints);

    constraints.gridx = 2; constraints.gridy = 1;
    constraints.gridwidth = 1; constraints.gridheight = 1;
    constraints.weightx = 1; constraints.weighty = 1;
    add(multiplyButton, constraints);

    constraints.gridx = 3; constraints.gridy = 1;
    constraints.gridwidth = 1; constraints.gridheight = 1;
    constraints.weightx = 1; constraints.weighty = 1;
    add(divideButton, constraints);

    //setUndecorated(true);
    setModal(true);
    setBounds(520, 305, 400, 200);
    setResizable(false);
    setVisible(true);
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
  }
}
