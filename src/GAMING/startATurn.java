package GAMING;

public class startATurn implements Runnable {
  private Thread t;
  private Player player = Main.getNowPlayer();
  private int n;
  private boolean able = true;
  
  startATurn(Color nowPlayer, int n) {
    Main.finishOneTurn = false;
    Main.nowPlayer = nowPlayer;
    this.n = n;
  }
  
  @Override
  public void run() {
    try {
      Main.roll();
      System.out.println(player.getColor() + " rolled " + Main.getRoll1() + " " + Main.getRoll2());
      Main.setOffInTurn = false;
      Main.setHasGotOpe(false);
      Main.setHasGotPlane(false);
      if (Main.getSum() >= 10 && n == 3) {
        System.out.println(player.getColor() + "is too OuZhouRen, your operated planes are inSetOff!!!");
        for (Plane p : player.getPlanes()) {
          if (p.beOpeInTurn) {
            p.inSetOff();
            able = false;
          }
        }
      }
      if (able && player.isHuman()) {
        new waitOpeThread(player.getColor().toString()).start();
        while (!Main.hasGotOpe) {
          Thread.sleep(20);
        }
        if (!Main.setOffInTurn && player.getToBeArrived() > 0) {
          new waitPlaneThread(player.getColor().toString()).start();
          while(!Main.hasGotPlane){
            Thread.sleep(20);
          }
        }
      } else if (able) {
        if ((Main.roll1 == 6 || Main.roll2 == 6) && player.getToBeSetOff() > 0) {
          player.setOffOnePlane();
        } else if (player.toBeArrived > 0) {
          Plane p = player.tryGetOnePlane();
          if (p != null) {
            Main.chosenStep = Math.max(Main.sum, Main.product);
            if (p.getPosition().getNextNBlock(Main.getSum(), p).getType() == Block.Type.FINAL) {
              Main.chosenStep = Main.sum;
            } else if (p.getPosition().getNextNBlock(Main.getProduct(), p).getType() == Block.Type.FINAL) {
              Main.chosenStep = Main.product;
            } else if (p.getPosition().getNextNBlock(Main.getQuotient(), p).getType() == Block.Type.FINAL) {
              Main.chosenStep = Main.quotient;
            } else if (p.getPosition().getNextNBlock(Main.getSub(), p).getType() == Block.Type.FINAL) {
              Main.chosenStep = Main.sub;
            }
            p.run(Main.getChosenStep());
          }
        }
      }
      Main.finishOneTurn = true;
    } catch (InterruptedException e) {
      System.out.println("Thread interrupted");
    }
    
  }
  
  public void start() {
    if (t == null) {
      t = new Thread(this);
      t.start();
    }
  }
}
