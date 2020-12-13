package gui.playerbase;

/**
 * DO NOT use this class. Use BasePanel instead.
 */
final class Apron {
  private boolean isEmpty = false;
  protected boolean getStatus() {
    return isEmpty;
  }
  void setStatus(boolean isEmpty) {
    if(isEmpty != getStatus()) {
      this.isEmpty = isEmpty;
    }
  }
}
