package gui;

import GAMING.Main;
import GAMING.setHasGotOpe;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * new RollDiceDialog() would be OK.
 */
public class RollDiceDialog extends JDialog {


  public RollDiceDialog(int r1, int r2, boolean ableToProduct, boolean ableToQuotient,
      boolean cheatingMode) {
    int[] randomNumber = new int[2];
    JButton plusButton = new JButton("+");
    JButton minusButton = new JButton("-");
    JButton multiplyButton = new JButton("x");
    JButton divideButton = new JButton("÷");
    GridBagLayout layout = new GridBagLayout();
    GridBagConstraints constraints = new GridBagConstraints();
    Font font = new Font("Arial", Font.PLAIN, 16);
    multiplyButton.setEnabled(ableToProduct);
    divideButton.setEnabled(ableToQuotient);

    setLayout(layout);

    plusButton.setFont(font);
    plusButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        Main.setOpe('+');
        new setHasGotOpe(true);
        dispose();
      }
    });
    minusButton.setFont(font);
    minusButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        Main.setOpe('-');
        new setHasGotOpe(true);
        dispose();
      }
    });
    multiplyButton.setFont(font);
    multiplyButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        Main.setOpe('*');
        new setHasGotOpe(true);
        dispose();
      }
    });
    divideButton.setFont(font);
    divideButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        Main.setOpe('/');
        new setHasGotOpe(true);
        dispose();
      }
    });

    constraints.fill = GridBagConstraints.BOTH;
    constraints.insets = new Insets(50, 20, 10, 20);

    if (!cheatingMode) {
      randomNumber[0] = r1;
      randomNumber[1] = r2;
      JLabel randomNumbers = new JLabel("You rolled: " + randomNumber[0] + " " + randomNumber[1],
          JLabel.CENTER);
      randomNumbers.setFont(font);
      constraints.gridx = 0;
      constraints.gridy = 0;
      constraints.gridwidth = 4;
      constraints.gridheight = 1;
      constraints.weightx = 1;
      constraints.weighty = 1;
      add(randomNumbers, constraints);
    } else {
      JLabel randomNumbers = new JLabel("You rolled: ");
      randomNumbers.setFont(font);

      JTextField inputNumber1 = new JTextField();
      inputNumber1.setHorizontalAlignment(JTextField.CENTER);
      inputNumber1.addKeyListener(new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
          super.keyPressed(e);
          if (!inputNumber1.getText().equals("") && e.getKeyCode() == KeyEvent.VK_ENTER) {
            randomNumber[0] = Integer.parseInt(inputNumber1.getText());
            inputNumber1.transferFocus();
          }
        }
      });

      JTextField inputNumber2 = new JTextField();
      inputNumber2.setHorizontalAlignment(JTextField.CENTER);
      inputNumber2.addKeyListener(new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
          super.keyPressed(e);
          if (!inputNumber2.getText().equals("") && e.getKeyCode() == KeyEvent.VK_ENTER) {
            randomNumber[1] = Integer.parseInt(inputNumber2.getText());
            inputNumber2.transferFocus();
          }
        }
      });

      constraints.gridx = 0;
      constraints.gridy = 0;
      constraints.gridwidth = 1;
      constraints.gridheight = 1;
      constraints.weightx = 1;
      constraints.weighty = 1;
      add(randomNumbers, constraints);

      constraints.gridx = 1;
      constraints.gridy = 0;
      constraints.gridwidth = 1;
      constraints.gridheight = 1;
      constraints.weightx = 1;
      constraints.weighty = 1;
      add(inputNumber1, constraints);

      constraints.gridx = 2;
      constraints.gridy = 0;
      constraints.gridwidth = 1;
      constraints.gridheight = 1;
      constraints.weightx = 1;
      constraints.weighty = 1;
      add(inputNumber2, constraints);


    }

    constraints.gridx = 0;
    constraints.gridy = 1;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.weightx = 1;
    constraints.weighty = 1;
    constraints.insets = new Insets(20, 20, 10, 20);
    add(plusButton, constraints);

    constraints.gridx = 1;
    constraints.gridy = 1;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.weightx = 1;
    constraints.weighty = 1;
    add(minusButton, constraints);

    constraints.gridx = 2;
    constraints.gridy = 1;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.weightx = 1;
    constraints.weighty = 1;
    add(multiplyButton, constraints);

    constraints.gridx = 3;
    constraints.gridy = 1;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.weightx = 1;
    constraints.weighty = 1;
    add(divideButton, constraints);

    setUndecorated(true);
    setModal(true);
    setBounds(520, 305, 400, 150);
    setResizable(false);
    setVisible(true);
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
  }
}
