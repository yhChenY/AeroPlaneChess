package gui;

import gui.BackgroundMusicSystem.Status;
import java.awt.event.MouseEvent;

public class MouseAdapter extends java.awt.event.MouseAdapter {
  @Override
  public void mouseClicked(MouseEvent e) {
    BackgroundMusicSystem bgm = new BackgroundMusicSystem(Status.CLICKED);
    Thread backgroundMusicThread = new Thread(bgm);
    backgroundMusicThread.start();
  }
}
