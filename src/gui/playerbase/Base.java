package gui.playerbase;

/**
 * DO NOT use this class. Use BasePanel instead.
 */
final class Base {
  private final int apronQuantity = 5;
  private final Apron[] aprons = new Apron[apronQuantity];
  private final Color color;

  Base(Color color) {
    this.color = color;
    for(int i = 0; i < apronQuantity; i++) {
      aprons[i] = new Apron();
    }
    aprons[0].setStatus(true);
  }

  Color getColor() {
    return color;
  }

  Apron getApron(int index) {
    return aprons[index];
  }

  int getApronQuantity() {
    return apronQuantity;
  }
}
