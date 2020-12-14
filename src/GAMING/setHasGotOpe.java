package GAMING;

import GAMING.Main;

public class setHasGotOpe extends Thread{
  boolean hasGotOpe;

  public setHasGotOpe(boolean hasGotOpe) {
    this.hasGotOpe = hasGotOpe;
    start();
  }

  @Override
  public void run() {
    super.run();
    Main.hasGotOpe = hasGotOpe;
  }
}
