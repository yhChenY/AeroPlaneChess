package Accounts;

import utils.util;

public class Account {

  private int uid;
  private String name;
  private String password;
  private int score = 0;

  public Account() {
  }

  public Account(int uid, String name, String password, int score) {
    this.uid = uid;
    this.name = name;
    this.password = password;
    this.score = score;
  }

  public int getUid() {
    return uid;
  }

  protected void setUid(int uid) {
    this.uid = uid;
  }

  public String getName() {
    return name;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean setName(String name) {
    this.name = name;
    return true;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public void addScore(int a) {
    score += a;
  }

  @Override
  public String toString() {
    return "\n" +
        "- \n" +
        " userName: " + name + " \n" +
        " uid: " + uid + " \n" +
        " password: " + password + " \n" +
        " score: " + score + " ";
  }

  /**
   * @return true: registered successfully/ false failed
   */
  public boolean register() {
    boolean temp = false;
    if (!(AccountSystem.isExisted(uid) || AccountSystem.isExisted(name) || !util
        .Allowed(password))) {
      temp = true;
      AccountSystem.addAccount(this);
    }
    return temp;
  }

  public boolean equals(Account account) {
    System.out.println(name);
    System.out.println(account.getName());
    return account.getName().equals(name) && account.password.equals(password);
  }
}
