package gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
  GridBagLayout layout = new GridBagLayout();
  GridBagConstraints constraints = new GridBagConstraints();
  Font font = new Font("Arial", Font.PLAIN, 16);

  public RollDiceDialog(int r1,int r2,boolean ableToProduct,boolean ableToQuotient) {
    //如果ableToProduct true那么 * 可以点击
    //  /
    setLayout(layout);

    randomNumbers.setFont(font);
    plusButton.setFont(font);
    minusButton.setFont(font);
    multiplyButton.setFont(font);

    constraints.fill = GridBagConstraints.BOTH;
    constraints.gridx = 0; constraints.gridy = 0;
    constraints.gridwidth = 3; constraints.gridheight = 1;
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

    setBounds(520, 305, 400, 200);
    setResizable(false);
    setVisible(true);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
  }
}
