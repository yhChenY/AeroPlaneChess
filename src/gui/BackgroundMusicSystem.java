package gui;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class BackgroundMusicSystem implements Runnable {

  private File audioFile;
  private AudioInputStream audioInputStream;
  private AudioFormat audioFormat;
  private SourceDataLine audioDataLine;
  private DataLine.Info info;
  private String audioLocation;
  private volatile boolean run = true;
  private FloatControl floatControl;
  private Status status;

  public BackgroundMusicSystem(Status status) {
    this.status = status;
    switch (status) {
      case DEFEAT -> audioLocation = "resources/defeat.wav";
      case GAMING -> audioLocation = "resources/bgm.wav";
      case VICTORY -> audioLocation = "resources/victory.wav";
      case MAIN_MENU -> audioLocation = "resources/theme.wav";
      case CLICKED -> audioLocation = "resources/mouseclicked.wav";
    }
  }

  private void prefetch() {
    audioFile = new File(audioLocation);
    if (!audioFile.exists()) {
      System.err.println("File not found.");
      return;
    }

    audioInputStream = null;
    try {
      audioInputStream = AudioSystem.getAudioInputStream(audioFile);

    } catch (UnsupportedAudioFileException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    audioFormat = audioInputStream.getFormat();
    audioDataLine = null;
    info = new DataLine.Info(SourceDataLine.class, audioFormat, AudioSystem.NOT_SPECIFIED);
    try {
      audioDataLine = (SourceDataLine) AudioSystem.getLine(info);
      audioDataLine.open(audioFormat);
    } catch (LineUnavailableException e) {
      e.printStackTrace();
    }
    System.out.println("Music playing. Can you hear?");
    audioDataLine.start();
  }

  public void play() {
    try {
      synchronized (this) {
        run = true;
      }
      audioInputStream = AudioSystem.getAudioInputStream(new File(audioLocation));
      int count;
      byte tempBuff[] = new byte[1024];

      while ((count = audioInputStream.read(tempBuff, 0, tempBuff.length)) != -1) {
        synchronized (this) {
          while (!run) {
            wait();
          }
        }
        floatControl = (FloatControl) audioDataLine.getControl(FloatControl.Type.MASTER_GAIN);
        floatControl.setValue(-20f);
        audioDataLine.write(tempBuff, 0, count);

      }

    } catch (UnsupportedAudioFileException ex) {
      ex.printStackTrace();
    } catch (IOException ex) {
      ex.printStackTrace();
    } catch (InterruptedException ex) {
      ex.printStackTrace();
    }
  }

  public void pause() {

  }

  public void stop() {
    run = false;
    synchronized (this) {
      run = false;
      notifyAll();
    }
  }

  @Override
  public void run() {
    prefetch();
    do {
      play();
    } while (run && status != Status.CLICKED);
  }

  @Override
  protected void finalize() throws Throwable {
    super.finalize();
    audioDataLine.drain();
    audioDataLine.close();
    audioInputStream.close();
  }

  enum Status {
    GAMING, VICTORY, DEFEAT, MAIN_MENU, CLICKED
  }
}
