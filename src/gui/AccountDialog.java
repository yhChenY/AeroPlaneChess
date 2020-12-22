package gui;

import Accounts.Account;
import Accounts.AccountSystem;
import GAMING.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.awt.*;

/**
 * Sign in or register dialog.
 * new AccountDialog would be OK.
 */
public class AccountDialog extends JDialog {
  
  private Account account = new Account();
  private String username;
  private String password;
  
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
    
    this.setBounds(520, 305, 400, 200);
    this.setResizable(false);
    this.setModal(true);
    this.setLayout(layout);
    
    
    register.addMouseListener(new gui.MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        System.err.println("register pressed");
        if (usernameField.getText() != null) {
          username = usernameField.getText();
        }
        if (passwordField.getPassword() != null) {
          password = String.copyValueOf(passwordField.getPassword());
        }
        if (AccountSystem.isExisted(username)) {
          JDialog dialog = new JDialog();
          dialog.add(new JLabel("User exists!"));
          dialog.setVisible(true);
        } else {
          account.setName(username);
          account.setPassword(password);
          AccountSystem.addAccount(account);
          dispose();
        }
      }
    });
    signIn.addMouseListener(new gui.MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        System.err.println("signIn pressed");
        if (usernameField.getText() != null) {
          username = usernameField.getText();
        }
        if (passwordField.getPassword() != null) {
          password = String.copyValueOf(passwordField.getPassword());
        }
        super.mouseClicked(e);
        Account user = new Account();
        user.setName(username);
        user.setPassword(password);
        Account targetUser = AccountSystem.findUserByName(username);
        Account ac = new Account();
        ac.setName(username);
        ac.setPassword(password);
        Main.hasRegisteredIn = true;
        Main.myAccount = ac;
        if (user.equals(targetUser)) {
          dispose();
        } else {
          JDialog dialog = new JDialog();
          dialog.add(new JLabel("Username or password incorrect!"));
          dialog.setVisible(true);
        }
      }
    });
    
    add(usernameLabel);
    add(usernameField);
    add(passwordLabel);
    add(passwordField);
    add(register);
    add(signIn);
    this.setVisible(true);
  }
}
