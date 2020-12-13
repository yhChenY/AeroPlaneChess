package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Sign in or register dialog.
 * new AccountDialog would be OK.
 */
public class AccountDialog extends JDialog {

  JLabel usernameLabel = new JLabel("Username: ", JLabel.CENTER);
  JTextField usernameField = new JTextField();
  JLabel passwordLabel = new JLabel("Password: ", JLabel.CENTER);
  JPasswordField passwordField = new JPasswordField();
  JButton signIn = new JButton("Sign In");
  JButton register = new JButton("Register");
  GridLayout layout = new GridLayout(3, 2);
  Font font = new Font("Arial", Font.PLAIN, 16);

  public AccountDialog() {
    layout.setVgap(20);
    layout.setHgap(20);
    usernameLabel.setFont(font);
    passwordLabel.setFont(font);
    signIn.setFont(font);
    register.setFont(font);

    this.setBounds(520, 305,400, 200);
    this.setResizable(false);
    this.setModal(true);
    this.setLayout(layout);
    add(usernameLabel);
    add(usernameField);
    add(passwordLabel);
    add(passwordField);
    add(register);
    add(signIn);
    this.setVisible(true);
  }
}
