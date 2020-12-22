package gui;

import GAMING.Main;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
    JButton launchPlaneButton = new JButton("or launch a plane");
    GridBagLayout layout = new GridBagLayout();
    GridBagConstraints constraints = new GridBagConstraints();
    Font font = new Font("Arial", Font.BOLD, 16);
    
    setLayout(layout);
    
    plusButton.setFont(font);
    plusButton.addMouseListener(new gui.MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        if(plusButton.isEnabled()) {
          Main.setOpe('+');
          Main.setHasGotOpe(true);
          dispose();
        }
      }
    });
    minusButton.setFont(font);
    minusButton.addMouseListener(new gui.MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        if(minusButton.isEnabled()) {
          Main.setOpe('-');
          Main.setHasGotOpe(true);
          dispose();
        }
      }
    });
    multiplyButton.setFont(font);
    multiplyButton.addMouseListener(new gui.MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        if(multiplyButton.isEnabled()) {
          Main.setOpe('*');
          Main.setHasGotOpe(true);
          dispose();
        }
      }
    });
    divideButton.setFont(font);
    divideButton.addMouseListener(new gui.MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        if(divideButton.isEnabled()) {
          Main.setOpe('/');
          Main.setHasGotOpe(true);
          dispose();
        }
      }
    });
    launchPlaneButton.setFont(font);
    launchPlaneButton.setEnabled(false);
    launchPlaneButton.addMouseListener(new gui.MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        if(launchPlaneButton.isEnabled()) {
          Main.getPlayerByColor(Main.nowPlayer).setOffOnePlane();
          Main.getMainMenu().getGame().flushGameFrame();
          dispose();
        }
      }
    });
    
    constraints.fill = GridBagConstraints.BOTH;
//    constraints.insets = new Insets(50, 20, 10, 20);
    
    if (!cheatingMode) {
      multiplyButton.setEnabled(ableToProduct);
      divideButton.setEnabled(ableToQuotient);
      randomNumber[0] = r1;
      randomNumber[1] = r2;
      JLabel randomNumbers = new JLabel("You rolled: " + randomNumber[0] + " " + randomNumber[1],
          JLabel.CENTER);
      randomNumbers.setFont(font);
      constraints.gridx = 0;
      constraints.gridy = 0;
      constraints.gridwidth = 4;
      constraints.gridheight = 2;
      constraints.weightx = 1;
      constraints.weighty = 1;
      add(randomNumbers, constraints);
    } else {
      JLabel randomNumbers = new JLabel("You roll: ", JLabel.RIGHT);
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
      inputNumber1.addFocusListener(new FocusAdapter() {
        @Override
        public void focusLost(FocusEvent e) {
          super.focusLost(e);
          if (!inputNumber1.getText().equals("")) {
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
            boolean[] ifValid = ifValid(randomNumber[0], randomNumber[1]);
            if (ifValid[0]) {
              plusButton.setEnabled(true);
              minusButton.setEnabled(true);
              multiplyButton.setEnabled(ifValid[1]);
              divideButton.setEnabled(ifValid[2]);
            } else {
              plusButton.setEnabled(false);
              minusButton.setEnabled(false);
              multiplyButton.setEnabled(false);
              divideButton.setEnabled(false);
            }
          }
        }
      });
      inputNumber2.addFocusListener(new FocusAdapter() {
        @Override
        public void focusLost(FocusEvent e) {
          super.focusLost(e);
          if (!inputNumber2.getText().equals("")) {
            randomNumber[1] = Integer.parseInt(inputNumber2.getText());
            boolean[] ifValid = ifValid(randomNumber[0], randomNumber[1]);
            if(ifValid[0]) {
              plusButton.setEnabled(true);
              minusButton.setEnabled(true);
              multiplyButton.setEnabled(ifValid[1]);
              divideButton.setEnabled(ifValid[2]);
            } else {
              plusButton.setEnabled(false);
              minusButton.setEnabled(false);
              multiplyButton.setEnabled(false);
              divideButton.setEnabled(false);
            }
          }
        }
      });
      
      constraints.gridx = 0;
      constraints.gridy = 0;
      constraints.gridwidth = 1;
      constraints.gridheight = 2;
      constraints.weightx = 1;
      constraints.weighty = 1;
      constraints.anchor = GridBagConstraints.EAST;
      add(randomNumbers, constraints);
      
      constraints.gridx = 1;
      constraints.gridy = 0;
      constraints.gridwidth = 1;
      constraints.gridheight = 2;
      constraints.weightx = 1;
      constraints.weighty = 1;
      add(inputNumber1, constraints);
      
      constraints.gridx = 2;
      constraints.gridy = 0;
      constraints.gridwidth = 1;
      constraints.gridheight = 2;
      constraints.weightx = 1;
      constraints.weighty = 1;
      add(inputNumber2, constraints);

      
    }
    
    constraints.gridx = 0;
    constraints.gridy = 2;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.weightx = 1;
    constraints.weighty = 0;
    constraints.insets = new Insets(20, 20, 10, 20);
    add(plusButton, constraints);
    
    constraints.gridx = 1;
    constraints.gridy = 2;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.weightx = 1;
    constraints.weighty = 0;
    add(minusButton, constraints);
    
    constraints.gridx = 2;
    constraints.gridy = 2;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.weightx = 1;
    constraints.weighty = 0;
    add(multiplyButton, constraints);
    
    constraints.gridx = 3;
    constraints.gridy = 2;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.weightx = 1;
    constraints.weighty = 0;
    add(divideButton, constraints);

    if(randomNumber[0] == 6 || randomNumber[1] == 6) {
      launchPlaneButton.setEnabled(true);
    }
    constraints.gridx = 0;
    constraints.gridy = 3;
    constraints.gridwidth = 4;
    constraints.gridheight = 0;
    constraints.anchor = GridBagConstraints.CENTER;
    add(launchPlaneButton, constraints);
    
    setUndecorated(true);
    setModal(true);
    setBounds(520, 305, 400, 150);
    setResizable(false);
    setVisible(true);
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
  }
  
  private boolean[] ifValid(int number1, int number2) {
    if (0 < number1 && number1 < 7 && 0 < number2 && number2 < 7) {
      boolean[] result = new boolean[3];
      result[0] = true;
      result[1] = number1 * number2 < 7;
      result[2] = number1 % number2 == 0 || number2 % number1 == 0;
      return result;
    } else {
      return new boolean[]{false, false, false};
    }
  }
}
