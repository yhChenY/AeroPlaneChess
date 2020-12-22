package gui;

import GAMING.Main;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ConnectHostDialog extends JDialog {

  private JLabel hostLabel = new JLabel("Host: ", JLabel.LEFT);
  private JTextField hostInputField = new JTextField();
  private JLabel connectResultLabel;
  private JButton confirmButton = new JButton("Confirm");
  private Font font = new Font("Arial", Font.PLAIN, 16);
  private GridBagLayout layout = new GridBagLayout();
  private GridBagConstraints constraints = new GridBagConstraints();
  private String ipAddress;
  private boolean connectionStatus = false;

  public ConnectHostDialog() {

    connectResultLabel = new JLabel("Enter the ip address of the host please.");

    confirmButton.addMouseListener(new gui.MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        if (!hostInputField.getText().equals("")) {
          ipAddress = hostInputField.getText();
          checkConnection();
            
        }
      }
    });
    hostInputField.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (!hostInputField.getText().equals("")) {
          ipAddress = hostInputField.getText();
          hostInputField.transferFocus();
          checkConnection();
        }
      }
    });

    setLayout(layout);
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = new Insets(10, 30, 10, 30);

    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.anchor = GridBagConstraints.WEST;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.weightx = 1;
    constraints.weighty = 1;
    hostLabel.setFont(font);
    add(hostLabel, constraints);

    constraints.gridx = 0;
    constraints.gridy = 1;
    constraints.anchor = GridBagConstraints.CENTER;
    constraints.gridwidth = 0;
    constraints.gridheight = 1;
    hostInputField.setFont(font);
    add(hostInputField, constraints);

    constraints.gridx = 0;
    constraints.gridy = 2;
    constraints.anchor = GridBagConstraints.WEST;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    connectResultLabel.setFont(font);
    add(connectResultLabel, constraints);

    constraints.gridx = 0;
    constraints.gridy = 3;
    constraints.anchor = GridBagConstraints.EAST;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.weightx = 0;
    constraints.weighty = 0;
    constraints.insets = new Insets(10, 250, 10, 50);
    confirmButton.setFont(font);
    add(confirmButton, constraints);

    setUndecorated(true);
    setModal(true);
    setBounds(520, 305, 400, 150);
    setResizable(false);
    setVisible(true);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

  }

  private void checkConnection() {
    boolean result;
    Socket socket = new Socket();
    try {
      SocketAddress socketAddress = new InetSocketAddress(ipAddress, 4320);
      socket.connect(socketAddress, 3000);
      result = true;
    } catch (IOException e) {
      result = false;
    } finally {
      try {
        socket.close();
      } catch (Exception e) {
        //Do nothing here.
      }
    }
    if (!result) {
      connectResultLabel.setText("Connection failed, please check your connection.");
    } else {
      connectionStatus = true;
      Main.getMainMenu().setIpAddress(ipAddress);
      dispose();
    }
  }

  public boolean getConnectionStatus() {
    return connectionStatus;
  }
}
