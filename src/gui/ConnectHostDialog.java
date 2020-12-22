package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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

public class ConnectHostDialog extends JDialog {
  
  private JLabel hostLabel = new JLabel("Host: ", JLabel.LEFT);
  private JTextField hostInputField = new JTextField();
  private JLabel connectResultLabel;
  private JButton confirmButton = new JButton("Confirm");
  private GridBagLayout layout = new GridBagLayout();
  private GridBagConstraints constraints = new GridBagConstraints();
  private String ipAddress;
  
  public ConnectHostDialog() {
    
    connectResultLabel = new JLabel();
    hostInputField.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        if (!hostInputField.getText().equals("") && e.getKeyCode() == KeyEvent.VK_ENTER) {
          ipAddress = hostInputField.getText();
          hostInputField.transferFocus();
        }
      }
    });
    hostInputField.addFocusListener(new FocusAdapter() {
      @Override
      public void focusLost(FocusEvent e) {
        super.focusLost(e);
        ipAddress = hostInputField.getText();
        if (!hostInputField.getText().equals("")) {
          hostInputField.transferFocus();
        }
      }
    });
    
    confirmButton.addMouseListener(new gui.MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
      }
    });
    
    setLayout(layout);
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(10, 30, 10, 30);
    
    constraints.gridx = 0; constraints.gridy = 0;
    constraints.anchor = GridBagConstraints.WEST;
    constraints.gridwidth = 1; constraints.gridheight = 1;
    constraints.weightx = 1; constraints.weighty = 1;
    add(hostLabel, constraints);

    constraints.gridx = 0; constraints.gridy = 1;
    constraints.anchor = GridBagConstraints.CENTER;
    constraints.gridwidth = 0; constraints.gridheight = 1;
    add(hostInputField, constraints);

    constraints.gridx = 0; constraints.gridy = 2;
    constraints.anchor = GridBagConstraints.WEST;
    constraints.gridwidth = 1; constraints.gridheight = 1;
    add(connectResultLabel, constraints);

    constraints.gridx = 0; constraints.gridy = 3;
    constraints.anchor = GridBagConstraints.EAST;
    constraints.gridwidth = 1; constraints.gridheight = 1;
    constraints.weightx = 0; constraints.weighty = 0;
    constraints.insets = new Insets(10, 250, 10, 50);
    add(confirmButton, constraints);
    
    setUndecorated(true);
    setModal(true);
    setBounds(520, 305, 400, 150);
    setResizable(false);
    setVisible(true);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
  }

}
